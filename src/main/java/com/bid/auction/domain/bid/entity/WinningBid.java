package com.bid.auction.domain.bid.entity;

import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.product.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WinningBid extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "winning_bid_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid_id", nullable = true)
	private Bid bid;

	// TODO
	/*
	@OneToOne(mappedBy = "winning_bid", cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	private PenaltyRequest penaltyRequest;

	@OneToOne(mappedBy = "winning_bid", cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	private Penalty penalty;
	*/

}
