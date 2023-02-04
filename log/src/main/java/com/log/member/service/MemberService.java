package com.log.member.service;

import com.log.member.dto.MemberDto;
import com.log.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {
    void create(String username, String password1, String password2, String email, String nickname);
    MemberDto getById(long memberIdx);
    List<MemberDto> getAll();
    MemberDto getByUsername(String username);
    MemberDto getByNickname(String nickname);
    String genAccessToken(Member member);

    MemberDto login(String username, String password);
    void upMembersPostCount(MemberDto memberDto);
}
