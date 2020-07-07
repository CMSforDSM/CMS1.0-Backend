package com.cms.api.domain.user.dao;

import com.cms.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByStudentNumber(String studentNo);
}
