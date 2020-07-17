package com.cms.api.domain.post.dao;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.post.domain.Post;
import com.cms.api.domain.post.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPostType(PostType postType);
    List<Post> findAllByPostTypeAndClub(PostType postType, Club club);
    
}
