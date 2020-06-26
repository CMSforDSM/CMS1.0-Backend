package com.cms.api.domain.comment.dao;

import com.cms.api.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentCommentRepository extends JpaRepository<Recruitment, Long> {
}
