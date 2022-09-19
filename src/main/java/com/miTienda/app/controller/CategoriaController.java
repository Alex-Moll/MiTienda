package com.miTienda.app.controller;

import com.miTienda.app.model.request.CategoriaRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.CategoriaResponse;
import com.miTienda.app.service.CategoriaService;
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
@RequestMapping("/categorias")
@Api(description = "CRUD Categoria", tags = "Categoria de Articulos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos categorias", notes = "Por request llegan toda la info necesaria para cear un categoria")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "creado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PostMapping()
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest request) throws Exception {

        CategoriaResponse response = categoriaService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de categoriaes", notes = "Por request llegan toda la info necesaria para actualizar un categoria")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(@Valid @RequestBody CategoriaRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        CategoriaResponse response = categoriaService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los categoriaes por Pagina ", notes = "Obtiene todos los categoriaes")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro categoriaes"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "No encontro el categoria por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = categoriaService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un categoria", notes = "Por request llegan toda la info necesaria para cear un categoria")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "eliminado"),
                            @ApiResponse(code = 400, message = "bad request"),
                            @ApiResponse(code = 403, message = "No tiene Permiso o Acceso")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        categoriaService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @ApiOperation(value = "Obtiene un Tipo de Articulo por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna un Tipo de Articulo"),
                            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        CategoriaResponse response = categoriaService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}