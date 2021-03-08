package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminApiRequest;
import com.example.study.model.network.response.AdminApiResponse;
import com.example.study.service.AdminApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminUserApiController extends CrudController<AdminApiRequest, AdminApiResponse, AdminUser> {

}
