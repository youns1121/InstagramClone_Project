package com.cos.photogramstart.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity

@Table(uniqueConstraints = { @UniqueConstraint
        (name = "likes_uk",
                columnNames = {"image_id", "user_id"}
        )
}
)
public class Likes { // N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;


    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image; // 1

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


}
