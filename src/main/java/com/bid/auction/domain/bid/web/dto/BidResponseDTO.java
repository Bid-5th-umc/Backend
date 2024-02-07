package com.bid.auction.domain.bid.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class BidResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AuctionBidResponseDTO {
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

	@Getter
	@RequiredArgsConstructor
	public static class AuctionBidErrorResponseDTO extends AuctionBidResponseDTO {
		private String errorResult;

		public AuctionBidErrorResponseDTO(String errorResult) {
			this.errorResult = errorResult;
		}
	}
}
