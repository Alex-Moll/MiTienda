package com.miTienda.app.controller;

import com.miTienda.app.model.request.TipoArticuloRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.TipoArticuloResponse;
import com.miTienda.app.repository.TipoArticuloRepository;
import com.miTienda.app.service.TipoArticuloService;
import com.miTienda.app.service.TipoArticuloService;
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
@RequestMapping("/tipoArticulos")
@Api(description = "CRUD Tipo de Articulo", tags = "tipo de articulo")
public class TipoArticuloController {

    @Autowired
    private TipoArticuloService tipoArticuloService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos TipoArticuloes", notes = "Por request llegan toda la info necesaria para cear un TipoArticulo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<TipoArticuloResponse> create(@Valid @RequestBody TipoArticuloRequest request) throws Exception {

        TipoArticuloResponse response = tipoArticuloService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de TipoArticuloes", notes = "Por request llegan toda la info necesaria para actualizar un TipoArticulo")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<TipoArticuloResponse> update(@Valid @RequestBody TipoArticuloRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        TipoArticuloResponse response = tipoArticuloService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los TipoArticuloes por Pagina ", notes = "Obtiene todos los TipoArticuloes")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro TipoArticuloes"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "No encontro el TipoArticulo por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = tipoArticuloService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un TipoArticulo", notes = "Por request llegan toda la info necesaria para cear un TipoArticulo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "eliminado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        tipoArticuloService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @ApiOperation(value = "Obtiene un Tipo de Articulo por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna un Tipo de Articulo"),
                            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<TipoArticuloResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        TipoArticuloResponse response = tipoArticuloService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}