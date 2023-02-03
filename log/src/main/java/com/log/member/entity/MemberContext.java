package com.log.member.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.log.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@JsonIncludeProperties({"id", "username", "nickname"})
public class MemberContext extends User {
    private final Long id;
    private final String nickname;
    private final String email;
    private Map<String, Object> attributes;

    private String userNameAttributeName;

    public MemberContext(Member member, List<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
    }
    public MemberContext(Member member, List<GrantedAuthority> authorities, Map<String, Object> attributes, String userNameAttributeName) {
        this(member, authorities);
        this.attributes = attributes;
        this.userNameAttributeName = userNameAttributeName;
    }
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return super.getAuthorities().stream().collect(Collectors.toSet());
    }

    public MemberContext(Member member) {
        super(member.getUsername(), member.getPassword(), member.getAuthorities());

        id = member.getId();
        nickname = member.getNickname();
        email = member.getEmail();
    }
}
