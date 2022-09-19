package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.MarcaEntity;
import com.miTienda.app.model.mapper.MarcaMapper;
import com.miTienda.app.model.request.MarcaRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.MarcaResponse;
import com.miTienda.app.repository.MarcaRepository;
import com.miTienda.app.service.MarcaService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaMapper marcaMapper;
    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public MarcaResponse save(MarcaRequest request) {

        MarcaEntity entity = marcaMapper.requestToEntity(request);

        marcaRepository.save(entity);

        MarcaResponse response = marcaMapper.entityToResponse(marcaRepository.save(entity));

        return response;
    }

    @Override
    public MarcaResponse update(Long id, MarcaRequest request) throws Exception {


        if(!marcaRepository.findById(id).isPresent()) {

            throw new Exception("No existe Marca con el Id : " + id);
        }

        MarcaEntity entityFound = marcaRepository.findById(id).orElseThrow();

        MarcaEntity entityUpdate = marcaMapper.entityAndRequestToResponse(entityFound, request);

        MarcaEntity entitySave = marcaRepository.save(entityUpdate);

        MarcaResponse response = marcaMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(marcaRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<MarcaEntity> marcas = page.getContent();
        List<MarcaResponse> responses = marcaMapper.listEntityToListResponse(marcas);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public MarcaResponse getById(Long id) throws Exception {

        if(! marcaRepository.findById(id).isPresent()) {

            throw new Exception("No existe Marca con el Id : " + id);

        }

        MarcaEntity entity = marcaRepository.findById(id).get();

        MarcaResponse response = marcaMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<MarcaEntity> entity = marcaRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Marca con el Id : " + id);
        }

        marcaRepository.delete(entity.get());

    }
}
