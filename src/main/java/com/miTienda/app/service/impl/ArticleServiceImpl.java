package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ArticleEntity;
import com.miTienda.app.model.mapper.ArticleMapper;
import com.miTienda.app.model.request.ArticleRequest;
import com.miTienda.app.model.response.ArticleResponse;
import com.miTienda.app.model.response.PaginationResponse;

import com.miTienda.app.repository.ArticleRepository;
import com.miTienda.app.service.ArticleService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public ArticleResponse save(ArticleRequest request) throws Exception {

//        System.out.println("\n : " + request.getCode());
        ArticleEntity entityFound = articleRepository.findByCode(request.getCode());
//        System.out.println("\n articulo : " + entityFound);

        if(entityFound == null) {
            ArticleEntity entity = articleMapper.requestToEntity(request);
            articleRepository.save(entity);
            ArticleResponse response = articleMapper.entityToResponse(articleRepository.save(entity));
            return response;
        }

        throw new Exception("YA existe un Articulo con el codigo : " + request.getCode());


    }

    @Transactional
    @Override
    public ArticleResponse update(Long id, ArticleRequest request) throws Exception {

        if (!articleRepository.findById(id).isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        ArticleEntity entityFound = articleRepository.findById(id).orElseThrow();
        ArticleEntity entityUpdate = articleMapper.entityAndRequestToResponse(entityFound, request);
        ArticleEntity entitySave = articleRepository.save(entityUpdate);
        ArticleResponse response = articleMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(articleRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ArticleEntity> articulos = page.getContent();
        List<ArticleResponse> responses = articleMapper.listEntityToListResponse(articulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Transactional
    @Override
    public ArticleResponse getById(Long id) throws Exception {

        if (!articleRepository.findById(id).isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        ArticleEntity entity = articleRepository.findById(id).get();
        ArticleResponse response = articleMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ArticleEntity> entity = articleRepository.findById(id);

        if (!entity.isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        articleRepository.delete(entity.get());

    }

    /*  -------------------------- Crud con codigo -----------------------*/

    @Transactional
    @Override
    public ArticleResponse getByCode(String code) throws Exception {

        System.out.println("\n code : " + code) ;
        ArticleEntity entity = articleRepository.findByCode(code);
        System.out.println("\n entity : " + entity);

        if (entity == null) {
            throw new Exception("No existe Articulo con el codigo : " + code);
        }

        ArticleResponse response = articleMapper.entityToResponse(entity);
        return response;

    }

    @Transactional
    @Override
    public ArticleResponse updateByCode(String code, ArticleRequest request) throws Exception {

        ArticleEntity entityFound = articleRepository.findByCode(code);
        if (entityFound == null) {
            throw new Exception("No existe Articulo con el codigo : " + code);
        }

        ArticleEntity entityUpdate = articleMapper.entityAndRequestToResponseByCode(entityFound, request);

        ArticleEntity entitySave = articleRepository.save(entityUpdate);

        ArticleResponse response = articleMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
    @Override
    public void deleteByCode(String code) throws Exception {

        ArticleEntity entityFound = articleRepository.findByCode(code);

        if (entityFound == null) {
            throw new Exception("No existe Articulo con el codigo : " + code);
        }

        articleRepository.delete(entityFound);

    }

}

    /*
    @Override
    public ArticleResponse save(ArticleRequest request) throws Exception {

        ArticleEntity entityFound = articuloRepository.findByCodigo(request.getCodigo());
        System.out.println("entity : " + entityFound.getCodigo());
        
        if(entityFound == null){

            ArticleEntity entity = articleMapper.requestToEntity(request);
            articuloRepository.save(entity);
            ArticleResponse response = articleMapper.entityToResponse(articuloRepository.save(entity), false);
            return response;

        }

        throw new ConvertNotFoundException("\n\n ..... YA existe un Articulo con el codigo : " + request.getCodigo() + "..\n");

    }

//    @Override
//    public ArticleResponse update(Long id, String codigo, ArticleRequest request) throws Exception {
//
//        if(!articuloRepository.findById(id).isPresent()) {
//
//            throw new Exception("No existe Articulo con el Id : " + id);
//        }
//
//        ArticleEntity entityFound = articuloRepository.findById(id).orElseThrow();
//
//        ArticleEntity entityUpdate = articleMapper.entityAndRequestToResponse(entityFound, request);
//
//        ArticleEntity entitySave = articuloRepository.save(entityUpdate);
//
//        ArticleResponse response = articleMapper.entityToResponse(entitySave, false);
//
//        return response;
//
//    }

    @Override
    public ArticleResponse update(String codigo, ArticleRequest request) throws Exception {

        ArticleEntity entity = articuloRepository.findByCodigo(codigo);

        System.out.println("\n entity : " + entity);

        if(entity.toString().isEmpty()) {

            throw new Exception("No existe Articulo con el codigo : " + codigo);
        }

        ArticleEntity entityFound = articuloRepository.findByCodigo(codigo);

        ArticleEntity entityUpdate = articleMapper.entityAndRequestToResponse(entityFound, request);

        ArticleEntity entitySave = articuloRepository.save(entityUpdate);

        ArticleResponse response = articleMapper.entityToResponse(entitySave, false);

        return response;

    }
 
    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(articuloRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ArticleEntity> articulos = page.getContent();
        List<ArticleResponse> responses = articleMapper.listEntityToListResponse(articulos);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Override
    public ArticleResponse getById(Long id, String codigo) throws Exception {

        if(!articuloRepository.findById(id).isPresent()) {

            throw new Exception("No existe Articulo con el Id : " + id);

        }

        ArticleEntity entity = articuloRepository.findById(id).get();

        ArticleResponse response = articleMapper.entityToResponse(entity, false);

        return response;


    }

    @Transactional
    @Override
    public void delete(Long id, String codigo) throws Exception {

        Optional<ArticleEntity> entity = articuloRepository.findById(id);

        if(!entity.isPresent()) {
            throw new Exception("No existe Articulo con el Id : " + id);
        }

        articuloRepository.delete(entity.get());

    }

     */

