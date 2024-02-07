package com.bid.auction.domain.bid.socket;

import java.security.Principal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StompPrincipal implements Principal {
	private final String name;

	@Override
	public String getName() {
		return name;
	}
}
