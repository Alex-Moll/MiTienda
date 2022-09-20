package com.miTienda.app.controller;

import com.miTienda.app.model.request.BrandRequest;
import com.miTienda.app.model.response.BrandResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.service.BrandService;
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
@RequestMapping("/brands")
@Api(description = "CRUD de marcas", tags = "marcas de Articulos")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevas marcas", notes = "Por request llegan toda la info necesaria para cear un marca")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<BrandResponse> create(@Valid @RequestBody BrandRequest request) throws Exception {

        BrandResponse response = brandService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de marcas", notes = "Por request llegan toda la info necesaria para actualizar un marca")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@Valid @RequestBody BrandRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        BrandResponse response = brandService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar las marcas por Pagina ", notes = "Obtiene todos los marcaes")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Encontro marcaes"),
                            @ApiResponse(code = 400, message = "Bad Request"),
                            @ApiResponse(code = 404, message = "No encontro el marca por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = brandService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar una marca", notes = "Por request llegan toda la info necesaria para cear un marca")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "eliminado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @ApiOperation(value = "Obtiene una marca por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna una marca"),
                            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<BrandResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        BrandResponse response = brandService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}