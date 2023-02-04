package com.log.member.service;

import com.log.member.dto.JoinDto;
import com.log.member.dto.MemberDto;
import com.log.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {
    void create(JoinDto joinDto);
    MemberDto getById(long memberIdx);
    List<MemberDto> getAll();
    MemberDto getByUsername(String username);
    MemberDto getByNickname(String nickname);
    String genAccessToken(Member member);

    MemberDto login(String username, String password);
    void upMembersPostCount(MemberDto memberDto);
}
