package com.bid.auction.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bid.auction.global.enums.statuscode.ErrorStatus;
import com.bid.auction.global.exception.GeneralException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {
	private final UserRepository userRepository;

	public User findUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));
	}

	public boolean isUserExist(Long userId) {
		return userRepository.findById(userId).isPresent();
	}
}
