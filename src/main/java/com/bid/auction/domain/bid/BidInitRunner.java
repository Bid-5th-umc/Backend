package com.bid.auction.domain.bid;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bid.auction.domain.bid.service.RedisService;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.repository.AuctionPostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BidInitRunner implements ApplicationRunner {

	private final RedisService redisService;
	private final AuctionPostRepository auctionPostRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		syncRedisRepo();
	}

	private void syncRedisRepo() {
		for (AuctionPost auctionPost : auctionPostRepository.findAll()) {
			Long id = auctionPost.getId();
			if (!auctionPost.getBidList().isEmpty()) {
				Long bidAmount = auctionPost.getBidList().get(auctionPost.getBidList().size() - 1).getBidAmount();
				redisService.saveData(redisService.getAuctionPostKey(id), bidAmount.toString());
			} else {
				redisService.saveData(redisService.getAuctionPostKey(id), auctionPost.getInitialBid().toString());
			}
		}
	}
}
