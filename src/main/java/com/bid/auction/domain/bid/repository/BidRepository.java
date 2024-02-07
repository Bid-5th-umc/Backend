package com.bid.auction.domain.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bid.auction.domain.bid.entity.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}
