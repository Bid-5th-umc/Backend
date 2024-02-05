package com.bid.auction.domain.bid.entity;

import com.bid.auction.domain.product.common.BaseEntity;
import com.bid.auction.domain.product.entity.AuctionPost;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
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
public class Bid extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid_id")
	private Long id;

	@NotNull
	@Max(1000000000)
	private Long bidAmount;

	@OneToOne(mappedBy = "bid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private WinningBid winningBid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_post_id", nullable = false)
	private AuctionPost auctionPost;

}
