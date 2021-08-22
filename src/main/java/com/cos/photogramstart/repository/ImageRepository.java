package com.cos.photogramstart.repository;

import com.cos.photogramstart.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
