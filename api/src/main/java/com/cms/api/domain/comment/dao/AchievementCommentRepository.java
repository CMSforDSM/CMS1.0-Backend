package com.cms.api.domain.comment.dao;

import com.cms.api.domain.comment.domain.AchievementComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementCommentRepository extends JpaRepository<AchievementComment, Long> {
}
