package com.miTienda.app.controller;

import com.miTienda.app.model.request.ClienteRequest;
import com.miTienda.app.model.response.ClienteResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.service.ClienteService;
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
@RequestMapping("/clientes")
@Api(description = "CRUD Clientes", tags = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService ClienteService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos Clientes", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest request) throws Exception {

        ClienteResponse response = ClienteService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Clientes", notes = "Por request llegan toda la info necesaria para actualizar un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@Valid @RequestBody ClienteRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        ClienteResponse response = ClienteService.update(id, request);
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

        PaginationResponse responses = ClienteService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Cliente", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "eliminado"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        ClienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Obtiene un Cliente por id", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna un Cliente"),
            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<ClienteResponse> getById(@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        ClienteResponse response = ClienteService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /*------------------------consultas por codigo -----------------------------------------------*/

    @ApiOperation(value = "Obtiene un Cliente por codigo", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retorna un Cliente"),
            @ApiResponse(code = 404, message = "EL codigo ingresado no existe")})
    @GetMapping("/getByCodigo/{codigo}")
    public ResponseEntity<ClienteResponse> getByCodigo(@PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ClienteResponse response = ClienteService.getByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Clientes", notes = "Por request llegan toda la info necesaria para actualizar un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/updateByCodigo/{codigo}")
    public ResponseEntity<ClienteResponse> updateByCodigo(@Valid @RequestBody ClienteRequest request, @PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ClienteResponse response = ClienteService.updateByCodigo(codigo, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Cliente por Codigo", notes = "Por request llegan toda la info necesaria para cear un Cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "eliminado"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("/deleteByCodigo/{codigo}")
    public ResponseEntity<Void> deleteByCodigo(@Valid @PathVariable @NotNull String codigo) throws Exception {

        ClienteService.deleteByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
