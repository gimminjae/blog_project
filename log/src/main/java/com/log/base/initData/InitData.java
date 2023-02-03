package com.log.base.initData;

import com.log.member.dto.MemberDto;
import com.log.member.service.MemberService;
import com.log.post.dto.PostDto;
import com.log.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile({"base-addi"})
public class InitData {
    @Bean
    CommandLineRunner init(
            PostService postService,
            MemberService memberService
    ) {
        return args -> {
            memberService.create("user1", "user1", "user1", "user@test.com", "haha");
            memberService.create("user2", "user2", "user2", "user2@test.com", "keke");

            MemberDto memberDto = memberService.getById(1);

            postService.create(memberDto, new PostDto(1, LocalDateTime.now(), null, "title1", "content1", memberDto.getNickname(), memberDto.getId()));
            postService.create(memberDto, new PostDto(2, LocalDateTime.now(), null, "title2", "content2", memberDto.getNickname(), memberDto.getId()));
        };
    }
}
