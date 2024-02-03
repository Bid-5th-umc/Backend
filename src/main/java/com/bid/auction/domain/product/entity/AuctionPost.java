package com.bid.auction.domain.product.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.bid.auction.domain.bid.entity.Bid;
import com.bid.auction.domain.product.common.BaseEntity;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.enums.ProductCondition;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "AuctionPost")
public class AuctionPost extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auction_post_id", nullable = false)
	private Long id;

	// TODO
	// @NotNull
	// private Long productId;

	@Column(name = "auction_post_title", nullable = false, length = 255)
	private String title;

	@Column(name = "auction_post_description", nullable = false)
	private String description;

	@NotNull
	private LocalDateTime expirationDate;

	@Column(name = "auction_initial_bid", nullable = false)
	private Long initialBid;

	@Column(name = "auction_buyout_price", nullable = false)
	@Max(1000000000)
	private Long buyoutPrice;

	@NotNull
	@Max(100000000)
	private Long bidIncrement;

	@ColumnDefault("0")
	@Column(name = "post_view_count", nullable = false)
	private Long viewCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "product_condition", nullable = false)
	private ProductCondition condition;

	@Enumerated(EnumType.STRING)
	@Column(name = "auction_status", nullable = false)
	private AuctionStatus status;

	@OneToMany(mappedBy = "auction_post", cascade = CascadeType.ALL)
	private List<AuctionPostImage> auctionPostImageList = new ArrayList<>();

	@OneToMany(mappedBy = "auction_post", cascade = CascadeType.ALL)
	private List<Bid> bidList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_brand_id")
	private ProductBrand productBrand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

	// User Entity 추가시 TODO
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User seller;
	 */
}
