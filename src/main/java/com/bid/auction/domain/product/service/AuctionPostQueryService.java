package com.bid.auction.domain.product.service;

import org.springframework.data.domain.Page;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.ProductCategoryGender;
import com.bid.auction.domain.product.enums.ProductCategoryName;
import com.bid.auction.domain.product.enums.SortStatus;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;

public interface AuctionPostQueryService {
	AuctionPostResponseDTO.AuctionPostPreviewListDTO getAuctionPostList(AuctionPostRequestDTO.getAuctionPostListDTO request);
}
