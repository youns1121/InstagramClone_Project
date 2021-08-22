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
        (name = "subscribe_uk",
                columnNames = {"from_user_id", "to_user_id"}
        )
    }
)

public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser; // 구독할때

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser; //구독받을때

    private LocalDateTime createDate;


    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
