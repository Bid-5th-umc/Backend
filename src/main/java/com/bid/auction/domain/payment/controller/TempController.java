package com.bid.auction.domain.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
	@GetMapping("/health")
	public String health() {
		return "healthy!";
	}
}
