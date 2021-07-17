package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration //IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder(){ //패스워드를 해쉬값으로 저장하기 위해 사용
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); : 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
        http.csrf().disable(); //csrf 비활성화
        http.authorizeRequests()        // 리소스(URL)의 권한한 설정 특정 리소스의 접근 허용

                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated() // 그외 나머지 리소스들은 무조건 인증을 완료 해야 접근이 가능, 인증이 필요한 접속 리소스
                .anyRequest().permitAll() //모든 요청 허용
                .and()
                    .formLogin()// 폼로그인 설정, 일반적인 로그인 방식 즉 로그인 폼 페이지와 로그인 처리 성공 실패 등을 사용하겠다는 의미입니다.
                    .loginPage("/auth/signin") //사용자가 따로 만든 로그인 페이지를 사용하려고 할때 설정합니다.
                    .defaultSuccessUrl("/"); //로그인이 완료 되면 가는 경로, 정상적으로 인증성공 했을 경우 이동하는 페이지를 설정합니다.




    }
}
