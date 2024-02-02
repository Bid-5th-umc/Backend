package com.bid.auction.domain.product.converter;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.entity.AuctionPostImage;

public class AuctionPostImageConverter {
	public static AuctionPostImage toAuctionPostImage(String url, AuctionPost auctionPost) {
		return AuctionPostImage.builder()
			.auctionPost(auctionPost)
			.imageUrl(url)
			.build();
	}
}
