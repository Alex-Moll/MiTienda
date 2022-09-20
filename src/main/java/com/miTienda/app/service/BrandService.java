package com.miTienda.app.service;

import com.miTienda.app.model.request.BrandRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.BrandResponse;

import java.util.Optional;

public interface BrandService {

    BrandResponse save(BrandRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    BrandResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    BrandResponse update(Long id, BrandRequest request) throws Exception;
}
