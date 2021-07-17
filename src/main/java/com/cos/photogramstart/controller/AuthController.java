package com.cos.photogramstart.controller;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.dto.auth.SignupDto;
import com.cos.photogramstart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final 필드를 의존성 주입할때 사용
@Controller// 파일을 전달 //@RestController //JSON return
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
        public String signinForm(){
            return "auth/signin";
        }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }


    // 회원가입버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto){ //key=value(x-www-form-urlencoded)

        //User <- SignupDto
        User user = signupDto.toEntity();
        User userEntity = authService.createUser(user);
        return "auth/signin";
    }


}
