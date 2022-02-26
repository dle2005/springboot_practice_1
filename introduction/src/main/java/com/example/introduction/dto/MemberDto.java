package com.example.introduction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MemberDto {

    @Data
    @AllArgsConstructor
    public static class ReqMember {
        private String name;
    }
}
