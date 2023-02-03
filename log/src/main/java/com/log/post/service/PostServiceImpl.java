package com.log.post.service;

import com.log.base.exception.DataNotFoundException;
import com.log.member.dto.MemberDto;
import com.log.member.entity.Member;
import com.log.post.dto.PostDto;
import com.log.post.entity.Post;
import com.log.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PostDto create(MemberDto memberDto, PostDto postDto) {
        postDto.setCreatorId(memberDto.getId());
        postDto.setCreatorNickname(memberDto.getNickname());

        Post post = Post.from(postDto);
        postRepository.save(post);

        return post.toDto();
    }

    @Override
    @Transactional
    public PostDto update(MemberDto memberDto, PostDto postDto) {
        Post oldPost = postRepository.findById(postDto.getId()).orElse(null);

        postIsNull(oldPost);

        if(oldPost.getCreatorId() != memberDto.getId()) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        Post post = oldPost.update(postDto.getTitle(), postDto.getContent());

        postRepository.save(post);

        return post.toDto();
    }

    @Override
    public void delete(MemberDto memberDto, PostDto postDto) {
        if(memberDto.getId() != postDto.getCreatorId()) {
            throw new AccessDeniedException("권한이 없습니다.");
        }
        Post post = postRepository.findById(postDto.getId()).orElse(null);

        postIsNull(post);

        postRepository.delete(post);
    }

    private void postIsNull(Post post) {
        if(post == null) {
            throw new DataNotFoundException("존재하지 않는 글입니다.");
        }
    }
}
