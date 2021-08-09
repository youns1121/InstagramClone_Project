package com.cos.photogramstart.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class SignupDto {


    @Size(min=2, max=20)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();


    }
}
