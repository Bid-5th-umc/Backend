package com.bid.auction.domain.bid.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BidResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AuctionBidResponseDTO{
		private Long id;
		private Long auctionPostId;
		private Long bidAmount;
		private LocalDateTime createdAt;
		private String result;

		@Override
		public String toString() {
			return "AuctionBidResponseDTO{" +
				"id=" + id +
				", auctionPostId=" + auctionPostId +
				", bidAmount=" + bidAmount +
				", createdAt=" + createdAt +
				", result='" + result + '\'' +
				'}';
		}
	}
}
