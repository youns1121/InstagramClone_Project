package com.cos.photogramstart.domain;


import com.cos.photogramstart.domain.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website; //웹 사이트

    private String bio; //자기 소개

    @Column(nullable = false)
    private String email;

    private String phone;

    private String gender;

    private String profileImageUrl; // 사진

    private String role; //권한

    // 나는 연관관계의 주인이 아님, 테이블에 컬럼을 만들지마
    // User를 Select 할때 해당 User id로 등록된 image들을 다 가져와;
    // Lazy = User를 Select 할때 해당 User id로 등록된 Image들을 가져오지마 - 대신 getImages() 함수의 image 들이  호출될 때 가져와!!
    // Eager = User를 Select 할때 해당 User id로 등록된 Image들을 전부 Join 해서 가져와
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Image> images = new ArrayList<>(); //양방향 매핑




    private LocalDateTime createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


}
