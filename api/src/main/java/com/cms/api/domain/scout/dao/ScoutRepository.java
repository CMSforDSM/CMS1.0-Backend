package com.cms.api.domain.scout.dao;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.scout.domain.Scout;
import com.cms.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout, Long> {

    List<Scout> findAllByTarget(User target);
    Optional<Scout> findByClubAndTarget(Club club, User target);
}
