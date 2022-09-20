package com.miTienda.app.service.impl;

import com.miTienda.app.model.entity.ClientEntity;
import com.miTienda.app.model.mapper.ClientMapper;
import com.miTienda.app.model.request.ClientRequest;
import com.miTienda.app.model.response.ClientResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.repository.ClientRepository;
import com.miTienda.app.service.ClientService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clienteMapper;
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Override
    public ClientResponse save(ClientRequest request) throws Exception {

        ClientEntity entity = clienteMapper.requestToEntity(request);
        clientRepository.save(entity);
        ClientResponse response = clienteMapper.entityToResponse(clientRepository.save(entity));

        return response;

    }

    @Transactional
    @Override
    public ClientResponse update(Long id, ClientRequest request) throws Exception {

//        ClientEntity entityFound = clientRepository.findById(id).orElseThrow();
//        System.out.println("\ncliente id : " + entityFound.getId());
//
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el Id : " + id);
//        }

        if(!clientRepository.findById(id).isPresent()) {

            throw new Exception("No existe Proveedor con el Id : " + id);
        }


        ClientEntity entityFound = clientRepository.findById(id).orElseThrow();
        ClientEntity entityUpdate = clienteMapper.entityAndRequestToResponse(entityFound, request);
        ClientEntity entitySave = clientRepository.save(entityUpdate);
        ClientResponse response = clienteMapper.entityToResponse(entitySave);

        return response;

    }

    @Transactional
    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(clientRepository, pageNumber, size,
                "/contacts?page=%d&size=%d");
        Page page = pagination.getPage();

        List<ClientEntity> Clientes = page.getContent();
        List<ClientResponse> responses = clienteMapper.listEntityToListResponse(Clientes);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }

    @Transactional
    @Override
    public ClientResponse getById(Long id) throws Exception {

        if (!clientRepository.findById(id).isPresent()) {

            throw new Exception("No existe Cliente con el Id : " + id);

        }

        ClientEntity entity = clientRepository.findById(id).get();

        ClientResponse response = clienteMapper.entityToResponse(entity);

        return response;

    }

    @Transactional
    @Override
    public void delete(Long id) throws Exception {

        Optional<ClientEntity> entity = clientRepository.findById(id);

        if (!entity.isPresent()) {
            throw new Exception("No existe Cliente con el Id : " + id);
        }

        clientRepository.delete(entity.get());

    }


    @Override
    public ClientResponse getByCodigo(String codigo) throws Exception {
        return null;
    }

    @Override
    public ClientResponse updateByCodigo(String codigo, ClientRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteByCodigo(String codigo) throws Exception {

    }



    /*  -------------------------- Crud con codigo -----------------------*/

//    @Transactional
//    @Override
//    public ClientResponse getByCodigo(String codigo) throws Exception {
//
//        System.out.println("\n codigo : " + codigo) ;
//        ClientEntity entity = ClientRepository.findByCodigo(codigo);
//        System.out.println("\n entity : " + entity);
//
//        if (entity == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClientResponse response = ClientMapper.entityToResponse(entity);
//        return response;
//
//    }
//
//    @Transactional
//    @Override
//    public ClientResponse updateByCodigo(String codigo, ClientRequest request) throws Exception {
//
//        ClientEntity entityFound = ClientRepository.findByCodigo(codigo);
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClientEntity entityUpdate = ClientMapper.entityAndRequestToResponse(entityFound, request);
//        ClientEntity entitySave = ClientRepository.save(entityUpdate);
//        ClientResponse response = ClientMapper.entityToResponse(entitySave);
//
//        return response;
//
//    }
//
//    @Transactional
//    @Override
//    public void deleteByCodigo(String codigo) throws Exception {
//
//        ClientEntity entityFound = ClientRepository.findByCodigo(codigo);
//
//        if (entityFound == null) {
//            throw new Exception("No existe Cliente con el codigo : " + codigo);
//        }
//
//        ClientRepository.delete(entityFound);
//
//    }


}