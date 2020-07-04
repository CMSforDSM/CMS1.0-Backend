package com.cms.api.domain.achievement.dao;

import com.cms.api.domain.achievement.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
