package com.bid.auction.domain.product.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bid.auction.domain.product.converter.AuctionPostConverter;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.service.AuctionPostCommandService;
import com.bid.auction.domain.product.service.AuctionPostQueryService;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;
import com.bid.auction.global.enums.statuscode.ErrorStatus;
import com.bid.auction.global.exception.GeneralException;
import com.bid.auction.global.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class AuctionPostRestController {

	private final AuctionPostCommandService auctionPostCommandService;
	private final AuctionPostQueryService auctionPostQueryService;

	@PostMapping("")
	public ApiResponse<AuctionPostResponseDTO.CreateAuctionPostResultDTO> addAuctionPost(
		@ModelAttribute @Valid AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		AuctionPost auctionPost = auctionPostCommandService.addAuctionPost(request);
		return ApiResponse.onSuccess(AuctionPostConverter.toCreateAuctionPostResultDTO(auctionPost));
	}

	@GetMapping("")
	public ApiResponse<AuctionPostResponseDTO.AuctionPostPreviewListDTO> getAuctionPostList(
		@Valid @ModelAttribute AuctionPostRequestDTO.getAuctionPostListDTO request
	) {
		if (request.getGender() != null && request.getCategoryName() == null
			|| request.getGender() == null && request.getCategoryName() != null) {
			throw new GeneralException(ErrorStatus._INVALID_CATEGORY);
		}

		AuctionPostResponseDTO.AuctionPostPreviewListDTO auctionPostList = auctionPostQueryService.getAuctionPostList(
			request);

		return ApiResponse.onSuccess(auctionPostList);
	}

	@GetMapping("/{AuctionPostId}")
	public ApiResponse<Object> getAuctionPost(@PathVariable(name = "AuctionPostId") Long AuctionPostId){
		AuctionPostResponseDTO.MainAuctionPostDTO auctionPostResult = auctionPostQueryService.getAuctionPost(AuctionPostId);
		return ApiResponse.onSuccess(auctionPostResult);
	}
}
