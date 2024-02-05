package com.bid.auction.domain.product.entity;

import java.util.ArrayList;
import java.util.List;

import com.bid.auction.domain.product.common.BaseEntity;
import com.bid.auction.domain.product.entity.mapping.ProductBrandLike;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
public class ProductBrand extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_brand_id")
	private Long id;

	@NotBlank
	@Column(length = 30, nullable = false)
	private String productBrandName;

	@OneToMany(mappedBy = "productBrand", cascade = CascadeType.ALL)
	private List<AuctionPost> auctionPostList = new ArrayList<>();

	@OneToMany(mappedBy = "productBrand", cascade = CascadeType.ALL)
	private List<ProductBrandLike> productBrandLikeList = new ArrayList<>();
}
