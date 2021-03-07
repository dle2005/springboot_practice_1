package com.example.study.ifs;

import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> req);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> req);

    Header delete(Long id);
}
