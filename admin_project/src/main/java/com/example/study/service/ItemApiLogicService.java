package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Item item = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(itemApiRequest.getRegisteredAt())
                .unregisteredAt(itemApiRequest.getUnregisteredAt())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
                    item.setStatus(itemApiRequest.getStatus())
                            .setName(itemApiRequest.getName())
                            .setTitle(itemApiRequest.getTitle())
                            .setContent(itemApiRequest.getContent())
                            .setPrice(itemApiRequest.getPrice())
                            .setBrandName(itemApiRequest.getBrandName())
                            .setRegisteredAt(itemApiRequest.getRegisteredAt())
                            .setUnregisteredAt(itemApiRequest.getUnregisteredAt())
                            .setPartner(partnerRepository.getOne(itemApiRequest.getPartnerId()));

                    return item;
                })
                .map(item -> itemRepository.save(item))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse
                .builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
