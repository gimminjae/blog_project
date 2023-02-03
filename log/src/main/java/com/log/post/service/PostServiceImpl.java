package com.log.post.service;

import com.log.base.exception.DataNotFoundException;
import com.log.member.entity.Member;
import com.log.post.dto.PostDto;
import com.log.post.entity.Post;
import com.log.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<PostDto> getAll() {
        return postRepository.findAll().stream().map(post -> post.toDto()).toList();
    }

    @Override
    public PostDto getById(long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        postIsNull(post);

        return post.toDto();
    }

    private void postIsNull(Post post) {
        if(post == null) {
            throw new DataNotFoundException("존재하지 않는 글입니다.");
        }
    }
}
