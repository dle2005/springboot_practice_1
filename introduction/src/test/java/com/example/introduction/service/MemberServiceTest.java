package com.example.introduction.service;

import com.example.introduction.domain.Member;
import com.example.introduction.repository.MemberRepository;
import com.example.introduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = createMember("spring");

        // when
        Member saveMember = memberService.join(member);

        // then
        assertEquals(member, saveMember);
    }

    @Test
    void 회원가입_중복회원_예외() {
        Member member1 = createMember("spring");
        Member member2 = createMember("spring");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertEquals(e.getMessage(), "Duplicate member name");
//        }
    }

    private Member createMember(String name) {
        return Member.builder()
                .name(name)
                .build();
    }
}