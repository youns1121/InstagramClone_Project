package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
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
                columnNames = {"fromUser_id", "toUser_id"}
        )
    }
)

public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다
    private Long id;

    @ManyToOne
    private User fromUser; // 구독할때

    @ManyToOne
    private User toUser; //구독받을때

    private LocalDateTime createDate;


    @PrePersist // DB에 Insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
