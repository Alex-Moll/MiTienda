package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ProveedorEntity;
import com.miTienda.app.model.mapper.ProveedorMapper;
import com.miTienda.app.model.request.ProveedorRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProveedorResponse;
import com.miTienda.app.repository.ProveedorRepository;
import com.miTienda.app.service.ProveedorService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorMapper proveedorMapper;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ProveedorResponse save(ProveedorRequest request) {

        ProveedorEntity entity = proveedorMapper.requestToEntity(request);

        proveedorRepository.save(entity);

        ProveedorResponse response = proveedorMapper.entityToResponse(proveedorRepository.save(entity));

        return response;
    }

    @Override
    public ProveedorResponse update(Long id, ProveedorRequest request) throws Exception {

        if(!proveedorRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);
        }

        ProveedorEntity entityFound = proveedorRepository.findById(id).orElseThrow();

        ProveedorEntity entityUpdate = proveedorMapper.entityAndRequestToResponse(entityFound, request);

        ProveedorEntity entitySave = proveedorRepository.save(entityUpdate);

        ProveedorResponse response = proveedorMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(proveedorRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ProveedorEntity> proveedores = page.getContent();
        List<ProveedorResponse> responses = proveedorMapper.listEntityToListResponse(proveedores);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public ProveedorResponse getById(Long id) throws Exception {

        if(! proveedorRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);

        }

        ProveedorEntity entity = proveedorRepository.findById(id).get();

        ProveedorResponse response = proveedorMapper.entityToResponse(entity);

        return response;


    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ProveedorEntity> entity = proveedorRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Proveedor con el Id : " + id);
        }

        proveedorRepository.delete(entity.get());

    }

}
