package com.bid.auction.domain.product.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bid.auction.domain.product.service.AuctionPostCommandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuctionPostRestController {

	private final AuctionPostCommandService auctionPostCommandService;

	@PostMapping("/products")


}
