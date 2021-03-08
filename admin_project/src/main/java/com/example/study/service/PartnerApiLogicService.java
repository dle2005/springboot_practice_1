package com.example.study.service;

import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.BaseService;
import com.example.study.model.network.response.PartnerApiResponse;
import org.springframework.stereotype.Service;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> req) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
