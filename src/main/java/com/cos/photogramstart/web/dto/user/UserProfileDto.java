package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.User;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserProfileDto {


    private boolean pageOwnerState;
    private boolean subscribeState;

    private Long imageCount;
    private Long subscribeCount;
    private User user;
}
