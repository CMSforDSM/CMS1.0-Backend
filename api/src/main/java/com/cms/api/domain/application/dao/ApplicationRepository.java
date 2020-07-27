package com.cms.api.domain.application.dao;

import com.cms.api.domain.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
