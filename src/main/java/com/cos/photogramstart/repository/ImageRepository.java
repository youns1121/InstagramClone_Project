package com.cos.photogramstart.repository;

import com.cos.photogramstart.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    //nativeQuery
    @Query(value = "select * from image where user_id in (select to_user_id from subscribe where from_user_id = :principalId) order by id desc", nativeQuery = true)
    Page<Image> mStroy(Long principalId, Pageable pageable);
}
