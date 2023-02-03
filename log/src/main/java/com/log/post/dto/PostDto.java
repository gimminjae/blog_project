package com.log.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String title;
    private String content;
    private String creatorNickname;
    private long creatorId;
}
