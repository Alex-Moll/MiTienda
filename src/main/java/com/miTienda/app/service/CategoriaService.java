package com.miTienda.app.service;

import com.miTienda.app.model.request.CategoriaRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.CategoriaResponse;

import java.util.Optional;

public interface CategoriaService {

    CategoriaResponse save(CategoriaRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    CategoriaResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    CategoriaResponse update(Long id, CategoriaRequest request) throws Exception;
}
