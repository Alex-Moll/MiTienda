package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.TipoArticuloEntity;
import com.miTienda.app.model.mapper.TipoArticuloMapper;
import com.miTienda.app.model.request.TipoArticuloRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.TipoArticuloResponse;
import com.miTienda.app.repository.TipoArticuloRepository;
import com.miTienda.app.service.TipoArticuloService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TipoArticuloServiceImpl implements TipoArticuloService {

    @Autowired
    private TipoArticuloMapper tipoArticuloMapper;
    @Autowired
    private TipoArticuloRepository tipoArticuloRepository;

    @Override
    public TipoArticuloResponse save(TipoArticuloRequest request) {

        TipoArticuloEntity entity = tipoArticuloMapper.requestToEntity(request);

        tipoArticuloRepository.save(entity);

        TipoArticuloResponse response = tipoArticuloMapper.entityToResponse(tipoArticuloRepository.save(entity));

        return response;
    }

    @Override
    public TipoArticuloResponse update(Long id, TipoArticuloRequest request) throws Exception {


        if(!tipoArticuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        TipoArticuloEntity entityFound = tipoArticuloRepository.findById(id).orElseThrow();

        TipoArticuloEntity entityUpdate = tipoArticuloMapper.entityAndRequestToResponse(entityFound, request);

        TipoArticuloEntity entitySave = tipoArticuloRepository.save(entityUpdate);

        TipoArticuloResponse response = tipoArticuloMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(tipoArticuloRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<TipoArticuloEntity> tipoArticulos = page.getContent();
        List<TipoArticuloResponse> responses = tipoArticuloMapper.listEntityToListResponse(tipoArticulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public TipoArticuloResponse getById(Long id) throws Exception {

        if(! tipoArticuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe TipoArticulo con el Id : " + id);

        }

        TipoArticuloEntity entity = tipoArticuloRepository.findById(id).get();

        TipoArticuloResponse response = tipoArticuloMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<TipoArticuloEntity> entity = tipoArticuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe TipoArticulo con el Id : " + id);
        }

        tipoArticuloRepository.delete(entity.get());

    }
}
