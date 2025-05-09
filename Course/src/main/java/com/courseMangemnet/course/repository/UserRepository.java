package com.courseMangemnet.course.repository;

import com.courseMangemnet.course.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserName(String username);
}
