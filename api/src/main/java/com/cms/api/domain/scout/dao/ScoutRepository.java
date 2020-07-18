package com.cms.api.domain.scout.dao;

import com.cms.api.domain.scout.domain.Scout;
import com.cms.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoutRepository extends JpaRepository<Scout, Long> {

    List<Scout> findAllByTarget(User target);
}
