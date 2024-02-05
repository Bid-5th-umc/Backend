package com.bid.auction.domain.product.web.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bid.auction.domain.product.converter.AuctionPostConverter;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.service.AuctionPostCommandService;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;
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

	@PostMapping("")
	public ApiResponse<AuctionPostResponseDTO.CreateAuctionPostResultDTO> addAuctionPost(
		@ModelAttribute @Valid AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		AuctionPost auctionPost = auctionPostCommandService.addAuctionPost(request);
		return ApiResponse.onSuccess(AuctionPostConverter.toCreateAuctionPostResultDTO(auctionPost));
	}
}
