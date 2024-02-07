package com.bid.auction.domain.bid.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class BidRequestDTO {

	@Getter
	@Setter
	public static class AuctionBidRequestDTO {
		private Long auctionPostId;
		private Long bidPrice;

		@Override
		public String toString() {
			return "AuctionBidRequestDTO{" +
				"auctionPostId=" + auctionPostId +
				", bidPrice=" + bidPrice +
				'}';
		}
	}
}
