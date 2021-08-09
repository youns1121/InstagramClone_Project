package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.dto.auth.SignupDto;
import com.cos.photogramstart.handler.ex.CustomvalidationException;
import com.cos.photogramstart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){ //key=value(x-www-form-urlencoded)

        //전 처리 로직
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomvalidationException("유효성검사 실패함", errorMap);
        }else {

            //User <- SignupDto
            User user = signupDto.toEntity();
            User userEntity = authService.createUser(user);
        }
        return "auth/signin";
    }


}
