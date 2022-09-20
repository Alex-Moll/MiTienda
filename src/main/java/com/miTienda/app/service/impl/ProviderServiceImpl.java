package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ProviderEntity;
import com.miTienda.app.model.mapper.ProviderMapper;
import com.miTienda.app.model.request.ProviderRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProviderResponse;
import com.miTienda.app.repository.ProviderRepository;
import com.miTienda.app.service.ProviderService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper proveedorMapper;
    @Autowired
    private ProviderRepository proveedorRepository;

    @Override
    public ProviderResponse save(ProviderRequest request) {

        ProviderEntity entity = proveedorMapper.requestToEntity(request);

        proveedorRepository.save(entity);

        ProviderResponse response = proveedorMapper.entityToResponse(proveedorRepository.save(entity));

        return response;
    }

    @Override
    public ProviderResponse update(Long id, ProviderRequest request) throws Exception {

        if(!proveedorRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);
        }

        ProviderEntity entityFound = proveedorRepository.findById(id).orElseThrow();

        ProviderEntity entityUpdate = proveedorMapper.entityAndRequestToResponse(entityFound, request);

        ProviderEntity entitySave = proveedorRepository.save(entityUpdate);

        ProviderResponse response = proveedorMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(proveedorRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ProviderEntity> proveedores = page.getContent();
        List<ProviderResponse> responses = proveedorMapper.listEntityToListResponse(proveedores);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public ProviderResponse getById(Long id) throws Exception {

        if(! proveedorRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);

        }

        ProviderEntity entity = proveedorRepository.findById(id).get();

        ProviderResponse response = proveedorMapper.entityToResponse(entity);

        return response;


    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ProviderEntity> entity = proveedorRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Proveedor con el Id : " + id);
        }

        proveedorRepository.delete(entity.get());

    }

}
