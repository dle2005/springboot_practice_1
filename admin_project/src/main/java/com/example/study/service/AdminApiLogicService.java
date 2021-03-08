package com.example.study.service;

import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminApiRequest;
import com.example.study.model.network.response.AdminApiResponse;
import com.example.study.model.network.response.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminApiLogicService extends BaseService<AdminApiRequest, AdminApiResponse, AdminUser> {

    @Override
    public Header<AdminApiResponse> create(Header<AdminApiRequest> request) {
        AdminApiRequest adminApiRequest = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(adminApiRequest.getAccount())
                .password(adminApiRequest.getPassword())
                .status(adminApiRequest.getStatus())
                .role(adminApiRequest.getRole())
                .lastLoginAt(adminApiRequest.getLastLoginAt())
                .passwordUpdatedAt(adminApiRequest.getPasswordUpdatedAt())
                .loginFailCount(adminApiRequest.getLoginFailCount())
                .registeredAt(adminApiRequest.getRegisteredAt())
                .unregisteredAt(adminApiRequest.getUnregisteredAt())
                .build();

        AdminUser newAdminUser = baseRepository.save(adminUser);

        return response(adminUser);
    }

    @Override
    public Header<AdminApiResponse> read(Long id) {
        Optional<AdminUser> optional = baseRepository.findById(id);

        return optional.map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminApiResponse> update(Header<AdminApiRequest> request) {
        AdminApiRequest adminApiRequest = request.getData();

        Optional<AdminUser> optional = baseRepository.findById(adminApiRequest.getId());

        return optional.map(adminUser -> {
                    adminUser.setAccount(adminApiRequest.getAccount())
                            .setPassword(adminApiRequest.getPassword())
                            .setStatus(adminApiRequest.getStatus())
                            .setRole(adminApiRequest.getRole())
                            .setLastLoginAt(adminApiRequest.getLastLoginAt())
                            .setPassword(adminApiRequest.getPassword())
                            .setLoginFailCount(adminApiRequest.getLoginFailCount())
                            .setRegisteredAt(adminApiRequest.getRegisteredAt())
                            .setUnregisteredAt(adminApiRequest.getUnregisteredAt());

                    return adminUser;
                })
                .map(adminUser -> baseRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<AdminUser> optional = baseRepository.findById(id);

        return optional.map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<AdminApiResponse> response(AdminUser adminUser) {
        AdminApiResponse adminApiResponse = AdminApiResponse
                .builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(adminApiResponse);
    }
}
