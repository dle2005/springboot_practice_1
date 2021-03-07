package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminApiRequest;
import com.example.study.model.network.response.AdminApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminUserApiController implements CrudInterface<AdminApiRequest, AdminApiResponse> {


    @Override
    public Header<AdminApiResponse> create(Header<AdminApiRequest> req) {
        return null;
    }

    @Override
    public Header<AdminApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AdminApiResponse> update(Header<AdminApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
