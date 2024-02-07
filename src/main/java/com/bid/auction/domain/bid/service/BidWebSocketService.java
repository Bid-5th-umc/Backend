package com.bid.auction.domain.bid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bid.auction.domain.bid.converter.BidConverter;
import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.bid.repository.BidRepository;
import com.bid.auction.domain.bid.web.dto.BidRequestDTO;
import com.bid.auction.domain.bid.web.dto.BidResponseDTO;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.repository.AuctionPostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BidWebSocketService {
	private final RedisService redisService;

	// rabbit MQ 변경시 제거
	private final AuctionPostRepository auctionPostRepository;
	private final BidRepository bidRepository;
	private final int BID_INCREMENT = 5000;

	@Transactional
	public BidResponseDTO.AuctionBidResponseDTO placeBid(BidRequestDTO.AuctionBidRequestDTO request) {

		String bidData = redisService.getData(redisService.getAuctionPostKey(request.getAuctionPostId()));

		if (bidData == null) {
			log.info("bidData == null");
			return BidConverter.toAuctionBidErrorResponseDTO("경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
		}

		Long curMax = Long.parseLong(bidData);
		log.info("curMax = {}", curMax);
		if (request.getBidPrice() < curMax + BID_INCREMENT) {
			log.info("request.getBidPrice() <= curMax + BID_INCREMENT");
			return BidConverter.toAuctionBidErrorResponseDTO("입찰가는 현재 최고 입찰금액 + 입찰단가 이상이여야 합니다.");
		}
		if (request.getBidPrice() > 1000000000) {
			log.info("request.getBidPrice() > 1000000000");
			return BidConverter.toAuctionBidErrorResponseDTO("입찰가는 최대 10억원입니다.");
		}

		redisService.saveData(redisService.getAuctionPostKey(request.getAuctionPostId()),
			request.getBidPrice().toString());

		// rabbit MQ로 비동기 처리로 변경하기
		AuctionPost auctionPost = auctionPostRepository.findByIdAndAuctionStatus(request.getAuctionPostId(),
			AuctionStatus._ACTIVE);

		if (auctionPost == null) {
			log.error("AUCTION_POST_NOT_FOUND_ERROR 발생 auctionPostId = {}", request.getAuctionPostId());
		}

		Bid bid = BidConverter.toBid(request, auctionPost);
		Bid savedBid = bidRepository.save(bid);
		return BidConverter.toAuctionBidResponseDTO(savedBid, request.getAuctionPostId(), "입찰을 성공했습니다.");
	}

}
