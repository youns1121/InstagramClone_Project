package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.User;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomvalidationApiException;
import com.cos.photogramstart.repository.SubscribeRepository;
import com.cos.photogramstart.repository.UserRepository;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //패스워드 암호화

    @Transactional(readOnly= true)
    public UserProfileDto 회원프로필(Long pageUserId, Long principalId) {
        //select * from image where userId = :userId

        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId); // 1은 페이지, -1은 주인이 아님
        dto.setImageCount((long) userEntity.getImages().size());

        Long subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        Long subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        return dto;

    }

    @Transactional
    public User updateUser(Long id, User user){
        //1.영속화
        //1. 무조건 찾았다, 걱정마 get() 2. 못찾았어 익셉션 발동시킬께 orElseThrow()
        User userEntity = userRepository.findById(id).orElseThrow(() ->  { return new CustomvalidationApiException("찾을 수 없는 아이디입니다.");
        });


        //2. 영속화된 오브젝트를 수정 -더티체킹(업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity; //더티체킹(변경감지) 일어나서 업데이트 완료됨됨
   }
}
