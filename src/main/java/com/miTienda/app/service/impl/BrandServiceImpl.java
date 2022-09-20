package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.BrandEntity;
import com.miTienda.app.model.mapper.BrandMapper;
import com.miTienda.app.model.request.BrandRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.BrandResponse;
import com.miTienda.app.repository.BrandRepository;
import com.miTienda.app.service.BrandService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandResponse save(BrandRequest request) {

        BrandEntity entity = brandMapper.requestToEntity(request);

        brandRepository.save(entity);

        BrandResponse response = brandMapper.entityToResponse(brandRepository.save(entity));

        return response;
    }

    @Override
    public BrandResponse update(Long id, BrandRequest request) throws Exception {


        if(!brandRepository.findById(id).isPresent()) {

            throw new Exception("No existe Marca con el Id : " + id);
        }

        BrandEntity entityFound = brandRepository.findById(id).orElseThrow();

        BrandEntity entityUpdate = brandMapper.entityAndRequestToResponse(entityFound, request);

        BrandEntity entitySave = brandRepository.save(entityUpdate);

        BrandResponse response = brandMapper.entityToResponse(entitySave);

        return response;

    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(brandRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<BrandEntity> marcas = page.getContent();
        List<BrandResponse> responses = brandMapper.listEntityToListResponse(marcas);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public BrandResponse getById(Long id) throws Exception {

        if(! brandRepository.findById(id).isPresent()) {

            throw new Exception("No existe Marca con el Id : " + id);

        }

        BrandEntity entity = brandRepository.findById(id).get();

        BrandResponse response = brandMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<BrandEntity> entity = brandRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Marca con el Id : " + id);
        }

        brandRepository.delete(entity.get());

    }
}
