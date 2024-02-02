package com.bid.auction.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auction.domain.product.entity.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
