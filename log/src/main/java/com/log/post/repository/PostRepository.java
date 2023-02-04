package com.log.post.repository;

import com.log.post.dto.PostDto;
import com.log.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatorId(Long id);

    List<Post> findByCreatorIdOrderByCreateDateTimeDesc(Long id);
}
