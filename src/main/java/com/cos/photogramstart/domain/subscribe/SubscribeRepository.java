package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying // insert, delete, update를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!
    @Query(value="INSERT INTO subscribe(from_user_id, to_user_id, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId);

    @Modifying
    @Query(value="DELETE FROM subscribe WHERE from_user_id = :fromUserId AND to_user_id =:toUserId", nativeQuery = true) // 파라미터 변수 =: 컬럼
    void mUnSubscribe(Long fromUserId, Long toUserId);



}
