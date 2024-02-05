package com.bid.auction.domain.product.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bid.auction.domain.product.enums.ProductCondition;
import com.bid.auction.domain.product.validation.annotation.ImageFileSize;
import com.bid.auction.domain.product.validation.annotation.ValidBuyoutPrice;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class AuctionPostRequestDTO {

	@Getter
	@Setter
	@ValidBuyoutPrice
	public static class CreateAuctionPostDTO {
		@NotBlank
		@Size(max = 100, message = "제목은 100자 이내여야 합니다.")
		private String title;

		@NotBlank
		@Size(max = 3000, message = "설명은 최대 3000자 이내여야 합니다.")
		private String description;

		@NotNull
		@Min(value = 1, message = "최소 경매 기간은 1일 입니다.")
		@Max(value = 7, message = "최대 경매 기간은 7일 입니다.")
		private Integer auctionDuration;

		@NotNull
		@Max(value = 1000000000, message = "경매 시작 금액은 최대 10억 입니다.")
		private Long initialBid;

		@Max(value = 1000000000, message = "즉시 구매 가격은 최대 10억 입니다.")
		private Long buyoutPrice;

		@NotNull
		private Long bidIncrement;

		@NotNull
		// @EnumValue(enumClass = ProductCondition.class)
		private ProductCondition condition;

		@ImageFileSize
		private List<MultipartFile> images;

		@Override
		public String toString() {
			return "CreateAuctionPostDTO{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", auctionDuration=" + auctionDuration +
				", initialBid=" + initialBid +
				", buyoutPrice=" + buyoutPrice +
				", bidIncrement=" + bidIncrement +
				", condition=" + condition +
				", images=" + images +
				'}';
		}
	}

}
