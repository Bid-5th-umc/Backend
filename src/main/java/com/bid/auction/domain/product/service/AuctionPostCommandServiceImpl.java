package com.bid.auction.domain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bid.auction.domain.product.converter.AuctionPostConverter;
import com.bid.auction.domain.product.converter.AuctionPostImageConverter;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.entity.AuctionPostImage;
import com.bid.auction.domain.product.entity.Uuid;
import com.bid.auction.domain.product.repository.AuctionPostRepository;
import com.bid.auction.domain.product.repository.UuidRepository;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.global.aws.s3.AmazonS3Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionPostCommandServiceImpl implements AuctionPostCommandService {

	private final UuidRepository uuidRepository;
	private final AmazonS3Manager s3Manager;
	private final AuctionPostRepository auctionPostRepository;

	@Override
	@Transactional
	public AuctionPost addAuctionPost(AuctionPostRequestDTO.CreateAuctionPostDTO request) {
		AuctionPost auctionPost = AuctionPostConverter.toAuctionPost(request);
		List<MultipartFile> images = request.getImages();
		if (images != null) {
			List<String> imageUrlList = new ArrayList<>();
			for (MultipartFile file : images) {
				String uuid = UUID.randomUUID().toString();
				Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

				// S3에 이미지 저장
				String imageUrl = s3Manager.uploadFile(s3Manager.generatePostImageKeyName(savedUuid), file);
				log.info("S3 Saved Image URL = {}", imageUrl);
				AuctionPostImage auctionPostImage = AuctionPostImageConverter.toAuctionPostImage(imageUrl, auctionPost);
				auctionPost.getAuctionPostImageList().add(auctionPostImage);
			}
		}
		log.info("auctionPost = {}", auctionPost);
		return auctionPostRepository.save(auctionPost);
	}
}
