package com.bid.auction.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auction.domain.product.entity.AuctionPost;

public interface AuctionPostRepository extends JpaRepository<AuctionPost, Long> {
}
