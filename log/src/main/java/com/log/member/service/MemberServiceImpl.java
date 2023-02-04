package com.log.member.service;

import com.log.base.config.jwt.provider.JwtProvider;
import com.log.base.exception.DataNotFoundException;
import com.log.member.dto.MemberDto;
import com.log.member.entity.Member;
import com.log.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(String username, String password1, String password2, String email, String nickname) {
        if (!password1.equals(password2)) {
            throw new AccessDeniedException("두 개의 비밀번호가 일치하지 않습니다.");
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password1))
                .nickname(nickname)
                .email(email)
                .build();
        member.setCreateDateTime(LocalDateTime.now());
        member.setUpdateDateTime(LocalDateTime.now());

        try {
            memberRepository.save(member);
        } catch(DataIntegrityViolationException e) {
            //정보가 중복될 경우
            throw new DataIntegrityViolationException("이미 존재하는 회원 정보입니다.");
        }
    }

    @Override
    public MemberDto getById(long id) {
        Member member = memberRepository.findById(id).orElse(null);

        notFoundMember(member);

        return member.toDto();
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(Member::toDto).toList();
    }

    @Override
    public MemberDto getByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        notFoundMember(member);

        return member.toDto();
    }

    @Override
    public MemberDto getByNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElse(null);

        notFoundMember(member);

        return member.toDto();
    }
    @Override
    public String genAccessToken(Member member) {
        return jwtProvider.generateAccessToken(member.getAccessTokenClaims(), 60 * 60 * 24 * 90);
    }

    @Override
    public MemberDto login(String username, String password) {
        MemberDto member = getByUsername(username);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new AccessDeniedException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    @Override
    public void upMembersPostCount(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getId()).orElse(null);

        notFoundMember(member);

        member.upPostCount();

        memberRepository.save(member);
    }

    //글 정보가 null 일 경우
    private void notFoundMember(Member member) {
        if (member == null) {
            throw new DataNotFoundException("회원정보가 존재하지 않습니다.");
        }
    }
}
