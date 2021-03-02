package com.example.springboot_practice.controller;

import com.example.springboot_practice.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // class 단위에 mapping 주소가 같아도 무관하다
public class PostController {

    // Rest의 개념
    // http 프로토콜에 있는 method를 활용한 아키텍처 스타일
    // http method를 통해서 resource를 처리
    // CRUD를 통한 Resource 조작 할 때 사용

    // HTTP Method  동작                  URL 형태
    // GET          조회(SELECT * READ)   /user/{id}
    // POST         생성(CREATE)          /user
    // PUT / PATCH  수정(UPDATE) *CREATE  /user
    // DELETE       삭제(DELETE)          /user/{1}

    // Post는 아래와 같은 경우에 발생한다.
    // HTML <Form>
    // ajax(비동기화) 검색
    // http 통신을 할때 post body에 data를 넣어 보내겠다
    // request할 때 form 형태 : json, xmL, multipart-form, text-plain 등
    // chrome -> 도구 더보기 -> 확장 프로그램 -> chrome 웹 스토어 열기 -> rest client 검색 -> Talend API Tester 설치

    // @RequestMapping(method = RequestMethod.POST, path = "/postMethod") // 아래와 동일
    // @PostMapping(value = "/postMethod", produces = {"application.json"}) // produces 에 지원할 형태를 지정 가능
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

    // http - put/patch method : post와 마찮가지로 BODY에 데이터가 들어있으며, 주로 업데이트에 사용
    // 하지만 이런 용도로 주수를 할당하여 사용하지는 않음

    @PutMapping("/putMethod")
    public void put() {

    }

    @PatchMapping("/patchMethod")
    public void patch() {

    }

    // http - delete method : get과 마찮가지로 주소에 파라미터가 들어가며, 데이터를 삭제 할 때 사용용
}
