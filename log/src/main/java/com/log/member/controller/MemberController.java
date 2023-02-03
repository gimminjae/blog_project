package com.log.member.controller;

import com.log.base.exception.DataNotFoundException;
import com.log.base.util.Ut;
import com.log.member.dto.JoinDto;
import com.log.member.dto.MemberDto;
import com.log.member.entity.MemberContext;
import com.log.member.service.MemberService;
import com.log.post.dto.PostDto;
import com.log.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final PostService postService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberDto inputMemberDto) {
        MemberDto memberDto = memberService.login(inputMemberDto.getUsername(), inputMemberDto.getPassword());

        String accessToken = memberService.genAccessToken(memberDto.toEntity());

        Map<String, String> returnMap = new HashMap<>();

        returnMap.put("accessToken", accessToken);
        returnMap.put("nickname", memberDto.getNickname());

        return ResponseEntity.of(Optional.of(returnMap));

    }
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(@AuthenticationPrincipal MemberContext memberContext) {
        if (memberContext == null) {
//            throw new DataNotFoundException("로그인이 필요합니다.");
        }

        return ResponseEntity.of(Optional.of(Ut.mapOf("member", memberContext)));
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody JoinDto joinDto) {
        //회원가입
        memberService.create(joinDto.getUsername(), joinDto.getPassword1(), joinDto.getPassword2(), joinDto.getEmail(), joinDto.getNickname());

        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{nickname}")
    public ResponseEntity<Map<String, Object>> userPage(@PathVariable String nickname) {
        MemberDto memberDto = memberService.getByNickname(nickname);
        List<PostDto> postDtos = postService.getByCreatorId(memberDto.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("member", memberDto);
        map.put("postList", postDtos);

        return ResponseEntity.of(Optional.of(map));
    }

}
