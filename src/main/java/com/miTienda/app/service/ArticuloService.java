package com.miTienda.app.service;

import com.miTienda.app.model.request.ArticuloRequest;
import com.miTienda.app.model.response.ArticuloResponse;
import com.miTienda.app.model.response.PaginationResponse;

import java.util.Optional;

public interface ArticuloService {

    ArticuloResponse save(ArticuloRequest request) throws Exception;

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ArticuloResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ArticuloResponse update(Long id, ArticuloRequest request) throws Exception;
}
