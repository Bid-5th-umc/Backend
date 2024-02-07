package com.bid.auction.domain.bid.web.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

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

	@MessageMapping("/bid/request")
	@SendToUser
	public void bidRequest(Principal principal, @Payload BidRequestDTO.AuctionBidRequestDTO request) {

		// TODO principal Security Principal로 변경

		log.info("요청 전송 성공 request = {}", request);

		// 유효성 검사
		if (request == null || request.getBidPrice() == null || request.getAuctionPostId() == null) {
			messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/bid/request", "요청 값이 올바르지 않습니다.");
			return;
		}

		BidResponseDTO.AuctionBidResponseDTO response = bidWebSocketService.placeBid(request);

		if (response instanceof BidResponseDTO.AuctionBidErrorResponseDTO) {
			log.info("입찰 실패, result = {}", ((BidResponseDTO.AuctionBidErrorResponseDTO)response).getErrorResult());
			messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/bid/request",
				((BidResponseDTO.AuctionBidErrorResponseDTO)response).getErrorResult());
			return;
		}
		messagingTemplate.convertAndSendToUser(principal.getName(), "/topic/bid/request", "입찰에 성공하였습니다.");
		// 전체 전달
		messagingTemplate.convertAndSend("/topic/bid/request", response);
	}
}
