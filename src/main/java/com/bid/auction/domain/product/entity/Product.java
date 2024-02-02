package com.bid.auction.domain.product.entity;

import java.util.ArrayList;
import java.util.List;

import com.bid.auction.domain.product.common.BaseEntity;
import com.bid.auction.domain.product.entity.mapping.ProductLike;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Product extends BaseEntity {

	@Id
	@Column(name = "product_id", nullable = false)
	private Long id;

	@NotBlank
	@Column(name = "product_name", length = 30)
	private String productName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id", nullable = false)
	private ProductCategory productCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_brand_id", nullable = false)
	private ProductBrand productBrand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductLike> productLikeList = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<AuctionPost> auctionPostList = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductImage> productImageList = new ArrayList<>();

	public void setProductCategory(ProductCategory productCategory) {
		if (this.productCategory != null) {
			this.productCategory.getProductList().remove(this);
		}
		this.productCategory = productCategory;
		productCategory.getProductList().add(this);
	}

	public void setProductBrand(ProductBrand productBrand) {
		if (this.productBrand != null) {
			this.productBrand.getProductList().remove(this);
		}
		this.productBrand = productBrand;
		productBrand.getProductList().add(this);
	}

}
