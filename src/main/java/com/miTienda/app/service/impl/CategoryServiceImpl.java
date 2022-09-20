package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.CategoryEntity;
import com.miTienda.app.model.mapper.CategoryMapper;
import com.miTienda.app.model.request.CategoryRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.CategoryResponse;
import com.miTienda.app.repository.CategoryRepository;
import com.miTienda.app.service.CategoryService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper tipoArticuloMapper;
    @Autowired
    private CategoryRepository tipoArticuloRepository;

    @Override
    public CategoryResponse save(CategoryRequest request) {

        CategoryEntity entity = tipoArticuloMapper.requestToEntity(request);

        tipoArticuloRepository.save(entity);

        CategoryResponse response = tipoArticuloMapper.entityToResponse(tipoArticuloRepository.save(entity));

        return response;
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) throws Exception {

        if(!tipoArticuloRepository.findById(id).isPresent()) {
            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        CategoryEntity entityFound = tipoArticuloRepository.findById(id).orElseThrow();
        CategoryEntity entityUpdate = tipoArticuloMapper.entityAndRequestToResponse(entityFound, request);
        CategoryEntity entitySave = tipoArticuloRepository.save(entityUpdate);
        CategoryResponse response = tipoArticuloMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(tipoArticuloRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<CategoryEntity> tipoArticulos = page.getContent();
        List<CategoryResponse> responses = tipoArticuloMapper.listEntityToListResponse(tipoArticulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public CategoryResponse getById(Long id) throws Exception {

        if(! tipoArticuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe TipoArticulo con el Id : " + id);

        }

        CategoryEntity entity = tipoArticuloRepository.findById(id).get();

        CategoryResponse response = tipoArticuloMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<CategoryEntity> entity = tipoArticuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        tipoArticuloRepository.delete(entity.get());

    }
}
