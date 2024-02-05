package com.bid.auction.domain.product.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bid.auction.domain.product.enums.ProductCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuctionPostResponseDTO {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateAuctionPostResultDTO {
		private Long auctionPostId;
		private LocalDateTime createdAt;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AuctionPostPreviewListDTO {
		List<AuctionPostPreviewDTO> auctionPostList;
		Integer listSize;
		Integer totalPage;
		Long totalElements;
		Boolean isFirst;
		Boolean isLast;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AuctionPostPreviewDTO {
		private Long auctionPostId;
		private String title;
		private LocalDateTime deadLine;
		// TODO -> user 연동 이후
		// private boolean isLiked
		private String mainImageUrl;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MainAuctionPostDTO{
		private String title;
		private String description;
		private Long currentBidPrice;
		private Long buyoutPrice;
		private Long initialBidPrice;
		private LocalDateTime expirationDate;
		private Long viewCount;
		private ProductCondition productCondition;
		private Long bidCount;
		private String sellerLoginId;
		private List<String> imageUrls;
	}
}
