package com.bid.auction.domain.product.converter;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;

public class AuctionPostConverter {

	public static AuctionPost toAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request){
		return AuctionPost.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.initialBid(request.getInitialBid())
			.buyoutPrice(request.getBuyoutPrice())
			.bidIncrement(request.getBidIncrement())
			.condition(request.getCondition())
			.status(AuctionStatus._ACTIVE)
			.build();
	}
}
