package com.bid.auction.domain.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.enums.ProductCategoryGender;
import com.bid.auction.domain.product.enums.ProductCategoryName;

public interface AuctionPostRepository extends JpaRepository<AuctionPost, Long> {
	Page<AuctionPost> findByAuctionStatus(AuctionStatus auctionStatus, Pageable pageable);

	AuctionPost findByIdAndAuctionStatus(Long id, AuctionStatus auctionStatus);


	Page<AuctionPost> findByProductCategoryProductCategoryGenderAndProductCategoryProductCategoryNameAndAuctionStatus(
		ProductCategoryGender gender, ProductCategoryName categoryName, AuctionStatus auctionStatus, Pageable pageable);
}
