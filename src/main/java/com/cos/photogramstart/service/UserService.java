package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomvalidationApiException;
import com.cos.photogramstart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //패스워드 암호화


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
