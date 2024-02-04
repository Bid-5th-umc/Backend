package com.bid.auction.domain.user.application;

import com.bid.auction.domain.user.User;
import com.bid.auction.domain.user.UserRepository;
import com.bid.auction.domain.user.presentation.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;

//    @Transactional(readOnly = true)
//    public Long isSignedUp(String token) {
//        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
//        User user = userRepository.findByKeyCode(userInfo.getId().toString()).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
//        return user.getId();
//    }
}
