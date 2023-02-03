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
}
