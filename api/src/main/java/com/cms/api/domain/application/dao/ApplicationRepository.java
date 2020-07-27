package com.cms.api.domain.application.dao;

import com.cms.api.domain.application.domain.Application;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserAndClub(User user, Club club);
}
