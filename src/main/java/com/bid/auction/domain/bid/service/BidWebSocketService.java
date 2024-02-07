package com.bid.auction.domain.bid.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bid.auction.domain.bid.converter.BidConverter;
import com.bid.auction.domain.bid.entity.Bid;
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
	private final SimpMessagingTemplate messagingTemplate;
	private final SimpUserRegistry userRegistry;
	private final RedisService redisService;

	// rabbit MQ 변경시 제거
	private final AuctionPostRepository auctionPostRepository;
	private final int BID_INCREMENT = 5000;

	@Transactional
	public BidResponseDTO.AuctionBidResponseDTO placeBid(BidRequestDTO.AuctionBidRequestDTO request) {

		log.info("placeBid 시작");

		Bid bid = BidConverter.toBid(request);

		String bidData = redisService.getData(String.valueOf(request.getAuctionPostId()));

		if (bidData == null) {
			return BidConverter.toAuctionBidResponseDTO(bid,
				request.getAuctionPostId(), "경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
		}

		Long curMax = Long.parseLong(bidData);
		if (request.getBidPrice() <= curMax + BID_INCREMENT) {
			return BidConverter.toAuctionBidResponseDTO(bid,
				request.getAuctionPostId(), "입찰가는 현재 최고 입찰금액 + 입찰단가 이상이여야 합니다.");
		}
		if (request.getBidPrice() > 1000000000) {
			return BidConverter.toAuctionBidResponseDTO(bid,
				request.getAuctionPostId(), "입찰가는 최대 10억원입니다.");
		}

		// rabbit MQ로 비동기 처리로 변경하기
		AuctionPost auctionPost = auctionPostRepository.findByIdAndAuctionStatus(request.getAuctionPostId(),
			AuctionStatus._ACTIVE);
		if (auctionPost == null) {
			log.info("경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
			return BidConverter.toAuctionBidResponseDTO(bid,
				request.getAuctionPostId(), "경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
		}

		redisService.saveData(redisService.getAuctionPostKey(request.getAuctionPostId()),
			request.getBidPrice().toString());
		auctionPost.getBidList().add(bid);
		AuctionPost save = auctionPostRepository.save(auctionPost);
		Bid savedBid = save.getBidList().get(save.getBidList().size() - 1);
		return BidConverter.toAuctionBidResponseDTO(savedBid, request.getAuctionPostId(), "입찰을 성공했습니다.");
	}

}
