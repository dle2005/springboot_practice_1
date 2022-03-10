package com.example.introduction.config;

import com.example.introduction.aop.TimeTraceAop;
import com.example.introduction.repository.MemberRepository;
import com.example.introduction.repository.MemoryMemberRepository;
import com.example.introduction.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
