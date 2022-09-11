package com.miTienda.app.controller;

import com.miTienda.app.model.request.ProveedorRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.ProveedorResponse;
import com.miTienda.app.repository.ProveedorRepository;
import com.miTienda.app.service.ProveedorService;
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
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@Api(description = "CRUD Proveedores", tags = "Proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos Proveedores", notes = "Por request llegan toda la info necesaria para cear un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "creado"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PostMapping()
    public ResponseEntity<ProveedorResponse> create(@Valid @RequestBody ProveedorRequest request) throws Exception {

        ProveedorResponse response = proveedorService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Proveedores", notes = "Por request llegan toda la info necesaria para actualizar un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "OK"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> update(@Valid @RequestBody ProveedorRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        ProveedorResponse response =  proveedorService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los Proveedores por Pagina ", notes = "Obtiene todos los Proveedores")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro Proveedores"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "No encontro el Proveedor por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                             @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = proveedorService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Proveedor", notes = "Por request llegan toda la info necesaria para cear un Proveedor")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "eliminado"),
                            @ApiResponse( code = 400, message = "bad request"),
                            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        proveedorService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

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
