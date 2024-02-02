package com.bid.auction.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bid.auction.domain.product.converter.AuctionPostConverter;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.entity.AuctionPostImage;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionPostCommandServiceImpl implements AuctionPostCommandService{

	@Override
	@Transactional
	public AuctionPost addAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		AuctionPost auctionPost = AuctionPostConverter.toAuctionPost(request);

		return null;
	}
}
