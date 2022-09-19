package com.miTienda.app.controller;

import com.miTienda.app.model.request.MarcaRequest;
import com.miTienda.app.model.response.MarcaResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.service.MarcaService;
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
@RequestMapping("/marcas")
@Api(description = "CRUD de marcas", tags = "marcas de Articulos")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevas marcas", notes = "Por request llegan toda la info necesaria para cear un marca")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<MarcaResponse> create(@Valid @RequestBody MarcaRequest request) throws Exception {

        MarcaResponse response = marcaService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de marcas", notes = "Por request llegan toda la info necesaria para actualizar un marca")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponse> update(@Valid @RequestBody MarcaRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        MarcaResponse response = marcaService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar las marcas por Pagina ", notes = "Obtiene todos los marcaes")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro marcaes"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "No encontro el marca por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = marcaService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar una marca", notes = "Por request llegan toda la info necesaria para cear un marca")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "eliminado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        marcaService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @ApiOperation(value = "Obtiene una marca por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna una marca"),
                            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<MarcaResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        MarcaResponse response = marcaService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}