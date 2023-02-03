package com.log.member.entity;

import com.log.base.entity.BaseEntity;
import com.log.base.util.Ut;
import com.log.member.dto.MemberDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;

    public MemberDto toDto() {
        return MemberDto.builder()
                .id(this.getId())
                .username(this.getUsername())
                .password(this.getPassword())
                .nickname(this.getNickname())
                .email(this.getEmail())
                .createDateTime(this.getCreateDateTime())
                .updateDateTime(this.getUpdateDateTime())
                .build();
    }
    public Map<String, Object> getAccessTokenClaims() {
        return Ut.mapOf(
                "id", getId(),
                "username", getUsername(),
                "nickname", getNickname()
        );
    }
    // 현재 회원이 가지고 있는 권한들을 List<GrantedAuthority> 형태로 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        return authorities;
    }
}
