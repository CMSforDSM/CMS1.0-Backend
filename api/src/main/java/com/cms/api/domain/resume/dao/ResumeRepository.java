package com.cms.api.domain.resume.dao;

import com.cms.api.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
