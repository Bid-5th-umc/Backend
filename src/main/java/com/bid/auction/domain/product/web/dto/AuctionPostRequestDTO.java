package com.bid.auction.domain.product.web.dto;

import java.util.List;

import com.bid.auction.domain.product.enums.ProductCondition;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class AuctionPostRequestDTO {

	@Getter
	public static class CreateAuctionPostDTO {
		@NotBlank
		@Size(max = 100)
		private String title;

		@NotBlank
		@Size(max = 3000)
		private String description;

		@NotNull
		@Min(1)
		@Max(7)
		private Integer auctionDuration;

		@NotNull
		@Min(1000000000)
		private Long initialBid;

		// 즉시 결제 금액 검증 어노테이션 추가 TODO
		@Min(1000000000)
		private Long buyoutPrice;

		@NotNull
		private Long bidIncrement;

		@NotNull
		private ProductCondition condition;

		private List<byte[]> images;
	}

}
