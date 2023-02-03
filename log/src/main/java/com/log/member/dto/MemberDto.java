package com.log.member.dto;

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
}
