package com.bid.auction.domain.bid.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import com.bid.auction.domain.bid.converter.BidConverter;
import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.bid.service.BidWebSocketService;
import com.bid.auction.domain.bid.web.dto.BidRequestDTO;
import com.bid.auction.domain.bid.web.dto.BidResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BidWebSocketController {

	private final SimpMessagingTemplate messagingTemplate;
	private final BidWebSocketService bidWebSocketService;

	@MessageMapping("/bidRequest")
	public void bidRequest(BidRequestDTO.AuctionBidRequestDTO request, WebSocketSession session){
		log.info("요청 전송 성공 request = {}", request);
		// 유효성 검사
		if(request == null || request.getBidPrice() == null || request.getAuctionPostId() == null){
			messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "요청 값이 올바르지 않습니다.");
		}

		Bid bid = bidWebSocketService.placeBid(request, session);
		if(bid == null) return;
		BidResponseDTO.AuctionBidResponseDTO response = BidConverter.toAuctionBidResponseDTO(bid,
			request.getAuctionPostId());

		messagingTemplate.convertAndSendToUser(session.getId(), "/topic/bidRequest", "입찰에 성공하였습니다.");
		// 전체 전달
		messagingTemplate.convertAndSend("/topic/bidRequest", response);
	}
}
