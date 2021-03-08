package com.example.study.service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.BaseService;
import com.example.study.model.network.response.OrderDetailApiResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogiService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> req) {
        return null;
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
