package com.miTienda.app.service;

import com.miTienda.app.model.request.ProveedorRequest;
import com.miTienda.app.model.request.TipoArticuloRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProveedorResponse;
import com.miTienda.app.model.response.TipoArticuloResponse;

import java.util.Optional;

public interface TipoArticuloService {

    TipoArticuloResponse save(TipoArticuloRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    TipoArticuloResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    TipoArticuloResponse update(Long id, TipoArticuloRequest request) throws Exception;
}
