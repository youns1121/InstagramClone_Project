package com.cos.photogramstart.repository;

import com.cos.photogramstart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JPA naming query
    User findByUsername(String username);
}
