package com.miTienda.app.controller;

import com.miTienda.app.model.request.ClientRequest;
import com.miTienda.app.model.response.ClientResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Api(description = "CRUD Clientes", tags = "Cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos Clientes", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request) throws Exception {

        ClientResponse response = clientService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Clientes", notes = "Por request llegan toda la info necesaria para actualizar un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@Valid @RequestBody ClientRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        ClientResponse response = clientService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los Clientes por Pagina ", notes = "Obtiene todos los Clientes")
    @ApiResponses({@ApiResponse(code = 200, message = "Encontro Clientes"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "No encontro el Cliente por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = clientService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Cliente", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "eliminado"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Obtiene un Cliente por id", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna un Cliente"),
            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        ClientResponse response = clientService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /*------------------------consultas por codigo -----------------------------------------------*/

    @ApiOperation(value = "Obtiene un Cliente por codigo", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna un Cliente"),
            @ApiResponse(code = 404, message = "EL codigo ingresado no existe")})
    @GetMapping("/getByCodigo/{codigo}")
    public ResponseEntity<ClientResponse> getByCodigo(@PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ClientResponse response = clientService.getByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Clientes", notes = "Por request llegan toda la info necesaria para actualizar un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/updateByCodigo/{codigo}")
    public ResponseEntity<ClientResponse> updateByCodigo(@Valid @RequestBody ClientRequest request, @PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ClientResponse response = clientService.updateByCodigo(codigo, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Cliente por Codigo", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "eliminado"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("/deleteByCodigo/{codigo}")
    public ResponseEntity<Void> deleteByCodigo(@Valid @PathVariable @NotNull String codigo) throws Exception {

        clientService.deleteByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
