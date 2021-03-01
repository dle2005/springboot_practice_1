package com.example.springboot_practice.ifs;

import com.example.springboot_practice.model.network.Header;

public interface CrudInterface {

    Header create(); // todo request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
