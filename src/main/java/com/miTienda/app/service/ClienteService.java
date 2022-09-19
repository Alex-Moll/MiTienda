package com.miTienda.app.service;

import com.miTienda.app.model.request.ClienteRequest;
import com.miTienda.app.model.response.ClienteResponse;
import com.miTienda.app.model.response.PaginationResponse;

import java.util.Optional;

public interface ClienteService {

    ClienteResponse save(ClienteRequest request) throws Exception;

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ClienteResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ClienteResponse update(Long id, ClienteRequest request) throws Exception;

    ClienteResponse getByCodigo(String codigo) throws Exception;

    ClienteResponse updateByCodigo(String codigo, ClienteRequest request) throws Exception;

    void deleteByCodigo(String codigo) throws Exception;
}
