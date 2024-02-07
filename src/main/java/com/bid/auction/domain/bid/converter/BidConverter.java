package com.bid.auction.domain.bid.converter;

import java.time.LocalDateTime;

import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.bid.web.dto.BidRequestDTO;
import com.bid.auction.domain.bid.web.dto.BidResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BidConverter {
	public static Bid toBid(BidRequestDTO.AuctionBidRequestDTO request){
		return Bid.builder()
			.bidAmount(request.getBidPrice())
			.build();
	}

	public static BidResponseDTO.AuctionBidResponseDTO toAuctionBidResponseDTO(Bid bid, Long auctionPostId){
		return	BidResponseDTO.AuctionBidResponseDTO.builder()
			.id(bid.getId())
			.auctionPostId(auctionPostId)
			.bidAmount(bid.getBidAmount())
			.createdAt(LocalDateTime.now())
			.build();
	}
}
