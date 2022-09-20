package com.miTienda.app.service;

import com.miTienda.app.model.request.ClientRequest;
import com.miTienda.app.model.response.ClientResponse;
import com.miTienda.app.model.response.PaginationResponse;

import java.util.Optional;

public interface ClientService {

    ClientResponse save(ClientRequest request) throws Exception;

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ClientResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ClientResponse update(Long id, ClientRequest request) throws Exception;

    ClientResponse getByCodigo(String codigo) throws Exception;

    ClientResponse updateByCodigo(String codigo, ClientRequest request) throws Exception;

    void deleteByCodigo(String codigo) throws Exception;
}
