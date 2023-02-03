package com.log.post.controller;

import com.log.base.util.Ut;
import com.log.member.dto.MemberDto;
import com.log.member.entity.MemberContext;
import com.log.member.service.MemberService;
import com.log.post.dto.PostDto;
import com.log.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return ResponseEntity.of(Optional.of(postService.getAll()));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable long postId) {
        return ResponseEntity.of(Optional.of(postService.getById(postId)));
    }
    @PostMapping("")
    public ResponseEntity<PostDto> writePost(@AuthenticationPrincipal MemberContext memberContext, @RequestBody PostDto postDto) {
        MemberDto memberDto = memberService.getById(memberContext == null ? 0 : memberContext.getId());

        PostDto resultPostDto = postService.create(memberDto, postDto);

        return ResponseEntity.of(Optional.of(resultPostDto));
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> modifyPost(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long postId, @RequestBody PostDto postDto) {
        MemberDto memberDto = memberService.getById(memberContext == null ? 0 : memberContext.getId());

        postDto.setId(postId);
        PostDto resultPostDto = postService.update(memberDto, postDto);

        return ResponseEntity.of(Optional.of(resultPostDto));
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long postId) {
        MemberDto memberDto = memberService.getById(memberContext == null ? 0 : memberContext.getId());

        PostDto postDto = postService.getById(postId);

        postService.delete(memberDto, postDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
