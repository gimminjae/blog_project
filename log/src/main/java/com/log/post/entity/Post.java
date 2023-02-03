package com.log.post.entity;

import com.log.base.entity.BaseEntity;
import com.log.post.dto.PostDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post extends BaseEntity {
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String creatorNickname;
    private Long creatorId;

    public static Post from(PostDto postDto) {
         Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .creatorId(postDto.getCreatorId())
                .creatorNickname(postDto.getCreatorNickname())
                .build();
        post.setCreateDateTime(LocalDateTime.now());

        return post;
    }

    public PostDto toDto() {
        return PostDto.builder()
                .id(this.getId())
                .createDateTime(this.getCreateDateTime())
                .updateDateTime(this.getUpdateDateTime())
                .title(this.getTitle())
                .content(this.getContent())
                .creatorId(this.creatorId)
                .creatorNickname(this.creatorNickname)
                .build();
    }

    public Post update(String title, String content) {
        this.setUpdateDateTime(LocalDateTime.now());
        this.setTitle(title);
        this.setContent(content);

        return this;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setTitle(String title) {
        this.title = title;
    }
}
