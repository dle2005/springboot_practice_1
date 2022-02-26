package com.example.introduction.service;

import com.example.introduction.domain.Member;
import com.example.introduction.dto.MemberDto;
import com.example.introduction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> { throw new IllegalStateException("Duplicate member name"); });
    }

    public String create(MemberDto.ReqMember reqMember) {
        Member member = Member.builder()
                .name(reqMember.getName())
                .build();

        return memberRepository.save(member).getName();
    }

    public List<MemberDto.ReqMember> list() {
        List<Member> list = memberRepository.findAll();

        return list.stream()
                .map(member -> new MemberDto.ReqMember(member.getName()))
                .collect(Collectors.toList());
    }
}
