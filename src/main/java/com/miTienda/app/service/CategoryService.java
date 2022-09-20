package com.miTienda.app.service;

import com.miTienda.app.model.request.CategoryRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.CategoryResponse;

import java.util.Optional;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request);

    PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size);

    CategoryResponse getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    CategoryResponse update(Long id, CategoryRequest request) throws Exception;
}
