package com.bid.auction.domain.product.entity;

import java.util.ArrayList;
import java.util.List;

import com.bid.auction.domain.product.common.BaseEntity;
import com.bid.auction.domain.product.enums.ProductCategoryGender;
import com.bid.auction.domain.product.enums.ProductCategoryName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class ProductCategory extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_category_id", nullable = false)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ProductCategoryName productCategoryName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ProductCategoryGender productCategoryGender;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<AuctionPost> auctionPostList = new ArrayList<>();
}
