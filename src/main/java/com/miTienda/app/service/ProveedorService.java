package com.miTienda.app.service;

import com.miTienda.app.model.request.ProveedorRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProveedorResponse;

import java.util.Optional;

public interface ProveedorService {

    ProveedorResponse save(ProveedorRequest categoryRequest);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ProveedorResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ProveedorResponse update(Long id, ProveedorRequest category) throws Exception;



}
