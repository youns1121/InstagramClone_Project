package com.cos.photogramstart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;

    private String caption; // ex)오늘 나 너무 피곤해
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서ㅏ버에 특정 폴더에 저장 -DB에 저장된 경로를 insert


    @JsonIgnoreProperties({"images"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //이미지 좋아요(추천)

    //댓글

    private LocalDateTime createDate;


    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


    //오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않게 함
//    @Override
//    public String toString() {
//        return "Image{" +
//                "id=" + id +
//                ", caption='" + caption + '\'' +
//                ", postImageUrl='" + postImageUrl + '\'' +
//                ", user=" + user +
//                ", createDate=" + createDate +
//                '}';
//    }
}
