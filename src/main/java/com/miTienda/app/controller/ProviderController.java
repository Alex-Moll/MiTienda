package com.miTienda.app.controller;

import com.miTienda.app.model.request.ProviderRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProviderResponse;
import com.miTienda.app.service.ProviderService;
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
import java.util.Optional;

@RestController
@RequestMapping("/providers")
@Api(description = "CRUD Proveedores", tags = "Proveedor")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos Proveedores", notes = "Por request llegan toda la info necesaria para cear un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "creado"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PostMapping()
    public ResponseEntity<ProviderResponse> create(@Valid @RequestBody ProviderRequest request) throws Exception {

        ProviderResponse response = providerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Proveedores", notes = "Por request llegan toda la info necesaria para actualizar un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "OK"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PutMapping("/{id}")
    public ResponseEntity<ProviderResponse> update(@Valid @RequestBody ProviderRequest request, @PathVariable @Valid Long id) throws Exception {

        ProviderResponse response =  providerService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los Proveedores por Pagina ", notes = "Obtiene todos los Proveedores")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro Proveedores"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse( code = 403, message = "No tiene Permiso o Acceso"),
                    @ApiResponse(code = 404, message = "No encontro el Proveedor por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                             @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = providerService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Proveedor", notes = "Por request llegan toda la info necesaria para cear un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "eliminado"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Long id) throws Exception {

        providerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Obtiene un Proveedor por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna un Proveedor"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso"),
                            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<ProviderResponse> getById (@PathVariable @Valid Long id) throws Exception {

        ProviderResponse response = providerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}

/*

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> upDate(@Valid @RequestBody ActivityRequestUpDate request, @PathVariable ("id") @Valid @NotNull Long id){

        ActivityResponse response =  activityService.upDate(id, request);

        return ResponseEntity.ok(response);


    }

    @GetMapping
    @ApiOperation(value = "Get the Page number @page of activities with Size @size from database", code = 200)
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
    public ResponseEntity<PaginationResponse> getPage(
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "size", required = false) Optional<Integer> size) {
        PaginationResponse contacts = activityService.getPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }
 */
