package com.bid.auction.domain.bid.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private final RedisTemplate<String, String> redisTemplate;

	public RedisService(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void saveData(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public String getData(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public String getAuctionPostKey(Long key) {
		return "auctionPost" + key.toString();
	}
}
