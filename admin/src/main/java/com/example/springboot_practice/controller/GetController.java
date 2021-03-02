package com.example.springboot_practice.controller;

import com.example.springboot_practice.model.SearchParam;
import com.example.springboot_practice.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController // spring에게 여기를 controller로 활용할 것이라고 알려주는 지시자
@RequestMapping("/api") // 이곳으로 들어오는 API 주소를 mapping, api로 받을 것이므로 /api 추가
// api 라고 지정하게 되면 Localhost:8080/api 까지 주소가 매칭된 것
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // Localhost:8080/api/getMethod
    public String getRequest() {

            return "Hi getMethod";
    }

    @GetMapping("/getParameter") // Localhost:8080/api/getParameter?id=1234&password=abcd
    // get mapping은 request mapping과 다르게 method를 지정 하지 않고 주소만 지정
    public String getParameter(@RequestParam String id, @RequestParam String password) {
        System.out.println("id : " + id);
        System.out.println("password : " + password);

        return id + password;
    }

    // Localhost:8080/api/getMultiparameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiparameter")
    public SearchParam getMultiparameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {

        // {"resultCode" : "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
