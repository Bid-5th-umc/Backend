package com.bid.auction.domain.bid.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.socket.WebSocketSession;

import com.bid.auction.domain.bid.converter.BidConverter;
import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.bid.web.dto.BidRequestDTO;
import com.bid.auction.domain.bid.web.dto.BidResponseDTO;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.repository.AuctionPostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Validated
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
	public Bid placeBid(BidRequestDTO.AuctionBidRequestDTO request, WebSocketSession session){

		Bid bid = BidConverter.toBid(request);

		String bidData = redisService.getData(String.valueOf(request.getAuctionPostId()));
		if(bidData == null){
			messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
			return null;
		}

		Long curMax = Long.parseLong(bidData);
		if(request.getBidPrice() <= curMax + BID_INCREMENT){
			messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "입찰가는 현재 최고 입찰금액 + 입찰단가 이상이여야 합니다.");
			return null;
		}
		if(request.getBidPrice() > 1000000000) {
			messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "입찰가는 최대 10억원입니다.");
			return null;
		}

		// rabbit MQ로 비동기 처리로 변경하기
		AuctionPost auctionPost = auctionPostRepository.findByIdAndAuctionStatus(request.getAuctionPostId(), AuctionStatus._ACTIVE);
		if(auctionPost == null){
			messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "경매가 종료되었거나, 존재하지 않는 경매 상품 입니다.");
			return null;
		}

		redisService.saveData(redisService.getAuctionPostKey(request.getAuctionPostId()), request.getBidPrice().toString());
		auctionPost.getBidList().add(bid);
		AuctionPost save = auctionPostRepository.save(auctionPost);
		Bid savedBid = save.getBidList().get(save.getBidList().size() - 1);
		return savedBid;
	}

}
