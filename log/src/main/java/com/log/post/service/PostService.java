package com.log.post.service;


import com.log.post.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    PostDto getById(long postId);
}
