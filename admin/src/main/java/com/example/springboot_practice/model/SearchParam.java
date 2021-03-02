package com.example.springboot_practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParam {
    private String account;
    private String email;
    private int page;

    // Lombok : annotation을 추가함으로, 생성자, get, set method를 자동으로 추가

    // File -> Settings -> Lombok 검색 -> 설치
    // build.gradle -> dependencies에 아래 코드 추가
    // compile 'org.projectlombok:lombok:1.18.10'
    // annotationProcessor 'org.projectlombok:lombok:1.18.10'

    // File -> Settings -> Annotation Processors -> Enable annotation processing 체크
}
