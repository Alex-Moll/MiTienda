package com.miTienda.app.service;

import com.miTienda.app.model.request.MarcaRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.MarcaResponse;

import java.util.Optional;

public interface MarcaService {

    MarcaResponse save(MarcaRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    MarcaResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    MarcaResponse update(Long id, MarcaRequest request) throws Exception;
}
