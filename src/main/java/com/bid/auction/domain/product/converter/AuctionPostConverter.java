package com.bid.auction.domain.product.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuctionPostConverter {

	public static AuctionPost toAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		return AuctionPost.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.expirationDate(LocalDateTime.now().plusDays(request.getAuctionDuration()))
			.initialBid(request.getInitialBid())
			.buyoutPrice(request.getBuyoutPrice())
			.bidIncrement(request.getBidIncrement())
			.condition(request.getCondition())
			.status(AuctionStatus._ACTIVE)
			.auctionPostImageList(new ArrayList<>())
			.viewCount(0L)
			.build();
	}

	public static AuctionPostResponseDTO.CreateAuctionPostResultDTO toCreateAuctionPostResultDTO(
		AuctionPost auctionPost) {
		return AuctionPostResponseDTO.CreateAuctionPostResultDTO.builder()
			.auctionPostid(auctionPost.getId())
			.createdAt(LocalDateTime.now())
			.build();
	}

}
