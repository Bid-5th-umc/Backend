package com.bid.auction.domain.product.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuctionPostResponseDTO {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateAuctionPostResultDTO{
		private Long auctionPostid;
		private LocalDateTime createdAt;
	}
}
