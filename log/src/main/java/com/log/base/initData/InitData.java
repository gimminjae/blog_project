package com.log.base.initData;

import com.log.member.service.MemberService;
import com.log.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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


        };
    }
}
