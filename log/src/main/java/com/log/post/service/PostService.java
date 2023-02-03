package com.log.post.service;


import com.log.member.dto.MemberDto;
import com.log.post.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    PostDto getById(long postId);

    PostDto create(MemberDto memberDto, PostDto postDto);

    PostDto update(MemberDto memberDto, PostDto postDto);

    void delete(MemberDto memberDto, PostDto postDto);

    List<PostDto> getByCreatorId(Long id);
}
