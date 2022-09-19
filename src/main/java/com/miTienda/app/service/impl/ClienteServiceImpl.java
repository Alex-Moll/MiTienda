package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ClienteEntity;
import com.miTienda.app.model.mapper.ClienteMapper;
import com.miTienda.app.model.request.ClienteRequest;
import com.miTienda.app.model.response.ClienteResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.repository.ClienteRepository;
import com.miTienda.app.service.ClienteService;
import com.miTienda.app.service.ClienteService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @Override
    public ClienteResponse save(ClienteRequest request) throws Exception {

        ClienteEntity entity = clienteMapper.requestToEntity(request);
        clienteRepository.save(entity);
        ClienteResponse response = clienteMapper.entityToResponse(clienteRepository.save(entity));

        return response;

    }

    @Transactional
    @Override
    public ClienteResponse update(Long id, ClienteRequest request) throws Exception {

//        ClienteEntity entityFound = clienteRepository.findById(id).orElseThrow();
//        System.out.println("\ncliente id : " + entityFound.getId());
//
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el Id : " + id);
//        }

        if(!clienteRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);
        }


        ClienteEntity entityFound = clienteRepository.findById(id).orElseThrow();
        ClienteEntity entityUpdate = clienteMapper.entityAndRequestToResponse(entityFound, request);
        ClienteEntity entitySave = clienteRepository.save(entityUpdate);
        ClienteResponse response = clienteMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(clienteRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ClienteEntity> Clientes = page.getContent();
        List<ClienteResponse> responses = clienteMapper.listEntityToListResponse(Clientes);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Transactional
    @Override
    public ClienteResponse getById(Long id) throws Exception {

        if (!clienteRepository.findById(id).isPresent()) {

            throw new Exception("No existe Cliente con el Id : " + id);

        }

        ClienteEntity entity = clienteRepository.findById(id).get();

        ClienteResponse response = clienteMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ClienteEntity> entity = clienteRepository.findById(id);

        if (!entity.isPresent()) {
            throw new Exception("No existe Cliente con el Id : " + id);
        }

        clienteRepository.delete(entity.get());

    }


    @Override
    public ClienteResponse getByCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public ClienteResponse updateByCodigo(String codigo, ClienteRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteByCodigo(String codigo) throws Exception {

    }



    /*  -------------------------- Crud con codigo -----------------------*/

//    @Transactional
//    @Override
//    public ClienteResponse getByCodigo(String codigo) throws Exception {
//
//        System.out.println("\n codigo : " + codigo) ;
//        ClienteEntity entity = ClienteRepository.findByCodigo(codigo);
//        System.out.println("\n entity : " + entity);
//
//        if (entity == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClienteResponse response = ClienteMapper.entityToResponse(entity);
//        return response;
//
//    }
//
//    @Transactional
//    @Override
//    public ClienteResponse updateByCodigo(String codigo, ClienteRequest request) throws Exception {
//
//        ClienteEntity entityFound = ClienteRepository.findByCodigo(codigo);
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClienteEntity entityUpdate = ClienteMapper.entityAndRequestToResponse(entityFound, request);
//        ClienteEntity entitySave = ClienteRepository.save(entityUpdate);
//        ClienteResponse response = ClienteMapper.entityToResponse(entitySave);
//
//        return response;
//
//    }
//
//    @Transactional
//    @Override
//    public void deleteByCodigo(String codigo) throws Exception {
//
//        ClienteEntity entityFound = ClienteRepository.findByCodigo(codigo);
//
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClienteRepository.delete(entityFound);
//
//    }


}