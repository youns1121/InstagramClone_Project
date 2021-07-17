package com.cos.photogramstart.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity

public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String website; //웹 사이트

    private String bio; //자기 소개

    private String email;

    private String phone;

    private String gender;

    private String profileImageUrl; // 사진

    private String role; //권한



    private LocalDateTime createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    public User() {
    }

    public User(Long id, String username, String password, String name, String website, String bio, String email, String phone, String gender, String profileImageUrl, String role, LocalDateTime createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
        this.createDate = createDate;
    }
}
