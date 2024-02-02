package com.bid.auction.domain.product.service;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;

public interface AuctionPostCommandService {
	public AuctionPost addAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request);
}
