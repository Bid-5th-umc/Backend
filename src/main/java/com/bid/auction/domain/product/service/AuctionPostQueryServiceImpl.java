package com.bid.auction.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bid.auction.domain.product.converter.AuctionPostConverter;
import com.bid.auction.domain.product.entity.AuctionPost;
import com.bid.auction.domain.product.enums.AuctionStatus;
import com.bid.auction.domain.product.enums.ProductCategoryGender;
import com.bid.auction.domain.product.enums.ProductCategoryName;
import com.bid.auction.domain.product.enums.SortStatus;
import com.bid.auction.domain.product.repository.AuctionPostRepository;
import com.bid.auction.domain.product.web.dto.AuctionPostRequestDTO;
import com.bid.auction.domain.product.web.dto.AuctionPostResponseDTO;
import com.bid.auction.global.enums.statuscode.ErrorStatus;
import com.bid.auction.global.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionPostQueryServiceImpl implements AuctionPostQueryService{

	private final AuctionPostRepository auctionPostRepository;

	@Override
	public AuctionPostResponseDTO.AuctionPostPreviewListDTO getAuctionPostList(
		AuctionPostRequestDTO.getAuctionPostListDTO request) {
		Sort sort;

		if(request.getSort() == SortStatus._RECOMMAND){
			sort = Sort.by(Sort.Direction.DESC, "viewCount"); // 추천 순 -> 조회수가 많은 순서로
		}
		else if(request.getSort() == SortStatus._DEADLINE){
			sort = Sort.by(Sort.Direction.ASC, "expirationDate"); // 마감 임박 순
		}
		// else if(sortStatus == SortStatus._POPULAR){
		//
		// }
		else {
			log.error("알 수 없는 정렬 기준입니다, SortStatus = {}", request.getSort());
			throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
		}

		Pageable pageable = PageRequest.of(request.getPage(), request.getPostCount(), sort);

		Page<AuctionPost> result;

		if (request.getGender() == null) {
			 result = auctionPostRepository.findByAuctionStatus(AuctionStatus._ACTIVE,
				pageable);
		}else {
			if(request.getCategoryName() == null) {
				log.error("CategoryName 값이 없습니다.");
				throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
			}
			result = auctionPostRepository.findByProductCategoryProductCategoryGenderAndProductCategoryProductCategoryNameAndAuctionStatus(
				request.getGender(), request.getCategoryName(), AuctionStatus._ACTIVE, pageable);
		}
		return AuctionPostConverter.toAuctionPostPreviewListDTO(result);
	}

	@Override
	public AuctionPostResponseDTO.MainAuctionPostDTO getAuctionPost(Long auctionPostId) {
		AuctionPost auctionPost = auctionPostRepository.findById(auctionPostId).orElseThrow(() -> new GeneralException(ErrorStatus._AUCTION_POST_NOT_FOUND));
		return AuctionPostConverter.toMainAuctionPostDTO(auctionPost);
	}
}
