package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ArticuloEntity;
import com.miTienda.app.model.mapper.ArticuloMapper;
import com.miTienda.app.model.request.ArticuloRequest;
import com.miTienda.app.model.response.ArticuloResponse;
import com.miTienda.app.model.response.PaginationResponse;

import com.miTienda.app.repository.ArticuloRepository;
import com.miTienda.app.service.ArticuloService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloMapper articuloMapper;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public ArticuloResponse save(ArticuloRequest request) throws Exception {

//        ArticuloEntity entityPresent = articuloRepository.findByCodigo(request.getCodigo());

        System.out.println("\n : " + request.getCodigo());
//        System.out.println("\n : " + articuloRepository.findByCodigo(request.getCodigo()));

//        if(!articuloRepository.findByCodigo(request.getCodigo())){
            ArticuloEntity entity = articuloMapper.requestToEntity(request);

            articuloRepository.save(entity);

            ArticuloResponse response = articuloMapper.entityToResponse(articuloRepository.save(entity), false);

            return response;
//        }
//
//        throw new Exception("YA existe un Articulo con el codigo : " + request.getCodigo());


    }

    @Override
    public ArticuloResponse update(Long id, ArticuloRequest request) throws Exception {

        if(!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);
        }

        ArticuloEntity entityFound = articuloRepository.findById(id).orElseThrow();

        ArticuloEntity entityUpdate = articuloMapper.entityAndRequestToResponse(entityFound, request);

        ArticuloEntity entitySave = articuloRepository.save(entityUpdate);

        ArticuloResponse response = articuloMapper.entityToResponse(entitySave, false);

        return response;

    }
 
    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(articuloRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ArticuloEntity> articulos = page.getContent();
        List<ArticuloResponse> responses = articuloMapper.listEntityToListResponse(articulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public ArticuloResponse getById(Long id) throws Exception {

        if(!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);

        }

        ArticuloEntity entity = articuloRepository.findById(id).get();

        ArticuloResponse response = articuloMapper.entityToResponse(entity, false);

        return response;


    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ArticuloEntity> entity = articuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        articuloRepository.delete(entity.get());

    }

}
