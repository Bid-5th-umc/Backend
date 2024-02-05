package com.bid.auction.domain.product.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuctionPostConverter {

	public static AuctionPost toAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		return AuctionPost.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.expirationDate(LocalDateTime.now().plusDays(request.getAuctionDuration()))
			.initialBid(request.getInitialBid())
			.buyoutPrice(request.getBuyoutPrice())
			.bidIncrement(request.getBidIncrement())
			.condition(request.getCondition())
			.auctionStatus(AuctionStatus._ACTIVE)
			.auctionPostImageList(new ArrayList<>())
			.viewCount(0L)
			.build();
	}

	public static AuctionPostResponseDTO.CreateAuctionPostResultDTO toCreateAuctionPostResultDTO(
		AuctionPost auctionPost) {
		return AuctionPostResponseDTO.CreateAuctionPostResultDTO.builder()
			.auctionPostId(auctionPost.getId())
			.createdAt(LocalDateTime.now())
			.build();
	}

	public static AuctionPostResponseDTO.AuctionPostPreviewDTO toAuctionPostPreviewDTO(AuctionPost auctionPost) {
		return AuctionPostResponseDTO.AuctionPostPreviewDTO.builder()
			.auctionPostId(auctionPost.getId())
			.title(auctionPost.getTitle())
			.deadLine(auctionPost.getExpirationDate())
			.mainImageUrl(auctionPost.getAuctionPostImageList().isEmpty() ? null :
				auctionPost.getAuctionPostImageList().get(0).getImageUrl())
			.build();
	}

	public static AuctionPostResponseDTO.AuctionPostPreviewListDTO toAuctionPostPreviewListDTO(
		Page<AuctionPost> auctionPostPage) {
		List<AuctionPostResponseDTO.AuctionPostPreviewDTO> auctionPostPreviewDTOList = auctionPostPage.stream()
			.map(AuctionPostConverter::toAuctionPostPreviewDTO).collect(Collectors.toList());

		return AuctionPostResponseDTO.AuctionPostPreviewListDTO.builder()
			.auctionPostList(auctionPostPreviewDTOList)
			.isFirst(auctionPostPage.isFirst())
			.isLast(auctionPostPage.isLast())
			.listSize(auctionPostPage.getSize())
			.totalElements(auctionPostPage.getTotalElements())
			.totalPage(auctionPostPage.getTotalPages())
			.build();
	}

}
