package com.bid.auction.domain.user.presentation.dto;

import lombok.Getter;

@Getter
public class KakaoAccount {

    private Boolean profile_nickname_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private KakaoProfile profile;
}
