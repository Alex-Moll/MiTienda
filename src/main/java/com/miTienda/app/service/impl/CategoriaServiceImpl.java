package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.CategoriaEntity;
import com.miTienda.app.model.mapper.CategoriaMapper;
import com.miTienda.app.model.request.CategoriaRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.CategoriaResponse;
import com.miTienda.app.repository.CategoriaRepository;
import com.miTienda.app.service.CategoriaService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaMapper tipoArticuloMapper;
    @Autowired
    private CategoriaRepository tipoArticuloRepository;

    @Override
    public CategoriaResponse save(CategoriaRequest request) {

        CategoriaEntity entity = tipoArticuloMapper.requestToEntity(request);

        tipoArticuloRepository.save(entity);

        CategoriaResponse response = tipoArticuloMapper.entityToResponse(tipoArticuloRepository.save(entity));

        return response;
    }

    @Override
    public CategoriaResponse update(Long id, CategoriaRequest request) throws Exception {


        if(!tipoArticuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        CategoriaEntity entityFound = tipoArticuloRepository.findById(id).orElseThrow();

        CategoriaEntity entityUpdate = tipoArticuloMapper.entityAndRequestToResponse(entityFound, request);

        CategoriaEntity entitySave = tipoArticuloRepository.save(entityUpdate);

        CategoriaResponse response = tipoArticuloMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(tipoArticuloRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<CategoriaEntity> tipoArticulos = page.getContent();
        List<CategoriaResponse> responses = tipoArticuloMapper.listEntityToListResponse(tipoArticulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public CategoriaResponse getById(Long id) throws Exception {

        if(! tipoArticuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe TipoArticulo con el Id : " + id);

        }

        CategoriaEntity entity = tipoArticuloRepository.findById(id).get();

        CategoriaResponse response = tipoArticuloMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<CategoriaEntity> entity = tipoArticuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        tipoArticuloRepository.delete(entity.get());

    }
}
