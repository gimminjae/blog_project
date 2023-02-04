package com.log.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinDto {
    @NotEmpty(message = "username 을(를) 입력해주세요.")
    private String username;
    @NotEmpty(message = "password 을(를) 입력해주세요.")
    private String password1;
    @NotEmpty(message = "password 확인을 입력해주세요.")
    private String password2;
    @NotEmpty(message = "email 을(를) 입력해주세요.")
    private String email;
    @NotEmpty(message = "nickname 을(를) 입력해주세요.")
    private String nickname;
    private String introduce;
}
