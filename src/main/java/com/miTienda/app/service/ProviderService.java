package com.miTienda.app.service;

import com.miTienda.app.model.request.ProviderRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProviderResponse;

import java.util.Optional;

public interface ProviderService {

    ProviderResponse save(ProviderRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ProviderResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ProviderResponse update(Long id, ProviderRequest request) throws Exception;

}
