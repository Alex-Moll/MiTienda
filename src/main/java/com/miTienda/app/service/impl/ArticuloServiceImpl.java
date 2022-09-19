package com.miTienda.app.service.impl;

import com.miTienda.app.exception.ConvertNotFoundException;
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

    @Transactional
    @Override
    public ArticuloResponse save(ArticuloRequest request) throws Exception {

        System.out.println("\n : " + request.getCodigo());
        ArticuloEntity articulo = articuloRepository.findByCodigo(request.getCodigo());
        System.out.println("\n articulo : " + articulo);

        if(articulo == null) {
            ArticuloEntity entity = articuloMapper.requestToEntity(request);

            articuloRepository.save(entity);

            ArticuloResponse response = articuloMapper.entityToResponse(articuloRepository.save(entity));

            return response;
        }

        throw new Exception("YA existe un Articulo con el codigo : " + request.getCodigo());


    }

    @Transactional
    @Override
    public ArticuloResponse update(Long id, ArticuloRequest request) throws Exception {

        if (!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);
        }

        ArticuloEntity entityFound = articuloRepository.findById(id).orElseThrow();

        ArticuloEntity entityUpdate = articuloMapper.entityAndRequestToResponse(entityFound, request);

        ArticuloEntity entitySave = articuloRepository.save(entityUpdate);

        ArticuloResponse response = articuloMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
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

    @Transactional
    @Override
    public ArticuloResponse getById(Long id) throws Exception {

        if (!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);

        }

        ArticuloEntity entity = articuloRepository.findById(id).get();

        ArticuloResponse response = articuloMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ArticuloEntity> entity = articuloRepository.findById(id);

        if (!entity.isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        articuloRepository.delete(entity.get());

    }

    /*  -------------------------- Crud con codigo -----------------------*/

    @Transactional
    @Override
    public ArticuloResponse getByCodigo(String codigo) throws Exception {

        System.out.println("\n codigo : " + codigo) ;
        ArticuloEntity entity = articuloRepository.findByCodigo(codigo);
        System.out.println("\n entity : " + entity);

        if (entity == null) {
            throw new Exception("No existe Articulo con el codigo : " + codigo);
        }

        ArticuloResponse response = articuloMapper.entityToResponse(entity);
        return response;

    }

    @Transactional
    @Override
    public ArticuloResponse updateByCodigo(String codigo, ArticuloRequest request) throws Exception {

        ArticuloEntity entityFound = articuloRepository.findByCodigo(codigo);
        if (entityFound == null) {
            throw new Exception("No existe Articulo con el codigo : " + codigo);
        }

        ArticuloEntity entityUpdate = articuloMapper.entityAndRequestToResponseByCode(entityFound, request);
        ArticuloEntity entitySave = articuloRepository.save(entityUpdate);
        ArticuloResponse response = articuloMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
    @Override
    public void deleteByCodigo(String codigo) throws Exception {

       ArticuloEntity entityFound = articuloRepository.findByCodigo(codigo);

        if (entityFound == null) {
            throw new Exception("No existe Articulo con el codigo : " + codigo);
        }

        articuloRepository.delete(entityFound);

    }


}

    /*
    @Override
    public ArticuloResponse save(ArticuloRequest request) throws Exception {

        ArticuloEntity entityFound = articuloRepository.findByCodigo(request.getCodigo());
        System.out.println("entity : " + entityFound.getCodigo());
        
        if(entityFound == null){

            ArticuloEntity entity = articuloMapper.requestToEntity(request);
            articuloRepository.save(entity);
            ArticuloResponse response = articuloMapper.entityToResponse(articuloRepository.save(entity), false);
            return response;

        }

        throw new ConvertNotFoundException("\n\n ..... YA existe un Articulo con el codigo : " + request.getCodigo() + "..\n");

    }

//    @Override
//    public ArticuloResponse update(Long id, String codigo, ArticuloRequest request) throws Exception {
//
//        if(!articuloRepository.findById(id).isPresent()) {
//
//            throw new Exception("No existe Articulo con el Id : " + id);
//        }
//
//        ArticuloEntity entityFound = articuloRepository.findById(id).orElseThrow();
//
//        ArticuloEntity entityUpdate = articuloMapper.entityAndRequestToResponse(entityFound, request);
//
//        ArticuloEntity entitySave = articuloRepository.save(entityUpdate);
//
//        ArticuloResponse response = articuloMapper.entityToResponse(entitySave, false);
//
//        return response;
//
//    }

    @Override
    public ArticuloResponse update(String codigo, ArticuloRequest request) throws Exception {

        ArticuloEntity entity = articuloRepository.findByCodigo(codigo);

        System.out.println("\n entity : " + entity);

        if(entity.toString().isEmpty()) {

            throw new Exception("No existe Articulo con el codigo : " + codigo);
        }

        ArticuloEntity entityFound = articuloRepository.findByCodigo(codigo);

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
    public ArticuloResponse getById(Long id, String codigo) throws Exception {

        if(!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);

        }

        ArticuloEntity entity = articuloRepository.findById(id).get();

        ArticuloResponse response = articuloMapper.entityToResponse(entity, false);

        return response;


    }

    @Transactional
    @Override
    public void delete(Long id, String codigo) throws Exception {

        Optional<ArticuloEntity> entity = articuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        articuloRepository.delete(entity.get());

    }

     */

