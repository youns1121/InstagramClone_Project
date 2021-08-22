package com.cos.photogramstart.web.dto;

import lombok.*;

import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SubscribeDto {

    private BigInteger id;
    private String username;
    private String profileImageUrl;
    private BigInteger subscribeState;
    private BigInteger equalUserState;
}
