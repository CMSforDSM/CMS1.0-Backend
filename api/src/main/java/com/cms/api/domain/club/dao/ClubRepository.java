package com.cms.api.domain.club.dao;

import com.cms.api.domain.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, String> {
}
