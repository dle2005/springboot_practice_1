package com.example.introduction.controller;

import com.example.introduction.dto.MemberDto;
import com.example.introduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/create")
    public String create(@RequestBody MemberDto.ReqMember reqMember) {
        return memberService.create(reqMember);
    }

    @GetMapping("members")
    public List<MemberDto.ReqMember> list() {
        return memberService.list();
    }
}
