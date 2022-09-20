package com.miTienda.app.service;

import com.miTienda.app.model.request.ArticleRequest;
import com.miTienda.app.model.response.ArticleResponse;
import com.miTienda.app.model.response.PaginationResponse;

import java.util.Optional;

public interface ArticleService {
    ArticleResponse save(ArticleRequest request) throws Exception;

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    ArticleResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    ArticleResponse update(Long id, ArticleRequest request) throws Exception;

    ArticleResponse getByCode(String code) throws Exception;

    ArticleResponse updateByCode(String code, ArticleRequest request) throws Exception;

    void deleteByCode(String code) throws Exception;

}
