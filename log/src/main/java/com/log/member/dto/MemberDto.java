package com.log.member.dto;

import com.log.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private long postCount;

    public Member toEntity() {
        Member member = Member.builder()
                .username(this.getUsername())
                .password(this.getPassword())
                .nickname(this.getNickname())
                .email(this.getEmail())
                .postCount(this.postCount)
                .build();
        member.setId(this.getId());
        member.setCreateDateTime(this.getCreateDateTime());
        member.setUpdateDateTime(this.getUpdateDateTime());

        return member;
    }
}
