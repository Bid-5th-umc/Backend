package com.bid.auction.domain.bid.converter;

import java.time.LocalDateTime;

import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.bid.web.dto.BidRequestDTO;
import com.bid.auction.domain.bid.web.dto.BidResponseDTO;
import com.bid.auction.domain.product.entity.AuctionPost;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BidConverter {
	public static Bid toBid(BidRequestDTO.AuctionBidRequestDTO request, AuctionPost auctionPost) {
		return Bid.builder()
			.bidAmount(request.getBidPrice())
			.auctionPost(auctionPost)
			.build();
	}

	public static BidResponseDTO.AuctionBidResponseDTO toAuctionBidResponseDTO(Bid bid, Long auctionPostId,
		String result) {
		return BidResponseDTO.AuctionBidResponseDTO.builder()
			.id(bid.getId())
			.auctionPostId(auctionPostId)
			.bidAmount(bid.getBidAmount())
			.createdAt(LocalDateTime.now())
			.result(result)
			.build();
	}

	public static BidResponseDTO.AuctionBidErrorResponseDTO toAuctionBidErrorResponseDTO(String errorResult) {
		return new BidResponseDTO.AuctionBidErrorResponseDTO(errorResult);
	}
}
