package com.bid.auction.domain.product.entity;

import java.time.LocalDateTime;

import com.bid.auction.domain.product.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name = "bid_id", nullable = false)
	private Long id;

	@NotNull
	@Max(1000000000)
	private Long bidAmount;

	@OneToOne(mappedBy = "bid", cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	private WinningBid winningBid;

}
