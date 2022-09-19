package com.miTienda.app.controller;

import com.miTienda.app.model.request.ArticuloRequest;
import com.miTienda.app.model.response.ArticuloResponse;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.service.ArticuloService;
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
@RequestMapping("/articulos")
@Api(description = "CRUD Articulos", tags = "Articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @Transactional
    @ApiOperation(value = "Agregar Nuevos Articuloes", notes = "Por request llegan toda la info necesaria para cear un Articulo")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "creado"),
            @ApiResponse( code = 400, message = "bad request"),
            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PostMapping()
    public ResponseEntity<ArticuloResponse> create(@Valid @RequestBody ArticuloRequest request) throws Exception {

        ArticuloResponse response = articuloService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Articuloes", notes = "Por request llegan toda la info necesaria para actualizar un Articulo")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "OK"),
            @ApiResponse( code = 400, message = "bad request"),
            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PutMapping("/{id}")
    public ResponseEntity<ArticuloResponse> update(@Valid @RequestBody ArticuloRequest request, @PathVariable @Valid @NotNull Long id) throws Exception {

        ArticuloResponse response =  articuloService.update(id,  request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar los Articuloes por Pagina ", notes = "Obtiene todos los Articuloes")
    @ApiResponses({ @ApiResponse(code = 200, message = "Encontro Articuloes"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "No encontro el Articulo por Id"),})
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = articuloService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Articulo", notes = "Por request llegan toda la info necesaria para cear un Articulo")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "eliminado"),
            @ApiResponse( code = 400, message = "bad request"),
            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable @NotNull Long id) throws Exception {

        articuloService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Obtiene un Articulo por id", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna un Articulo"),
            @ApiResponse(code = 404, message = "EL Id ingresado no existe")})
    @GetMapping("{id}")
    public ResponseEntity<ArticuloResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws Exception {

        ArticuloResponse response = articuloService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /*------------------------consultas por codigo -----------------------------------------------*/

    @ApiOperation(value = "Obtiene un Articulo por codigo", notes = "")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna un Articulo"),
            @ApiResponse(code = 404, message = "EL codigo ingresado no existe")})
    @GetMapping("/getByCodigo/{codigo}")
    public ResponseEntity<ArticuloResponse> getByCodigo (@PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ArticuloResponse response = articuloService.getByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Actualizar Datos de Articuloes", notes = "Por request llegan toda la info necesaria para actualizar un Articulo")
    @ApiResponses(value = { @ApiResponse( code = 200, message = "OK"),
            @ApiResponse( code = 400, message = "bad request"),
            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @PutMapping("/updateByCodigo/{codigo}")
    public ResponseEntity<ArticuloResponse> updateByCodigo(@Valid @RequestBody ArticuloRequest request, @PathVariable @Valid @NotNull @NotBlank String codigo) throws Exception {

        ArticuloResponse response =  articuloService.updateByCodigo(codigo,  request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Transactional
    @ApiOperation(value = "Eliminar un Articulo por Codigo", notes = "Por request llegan toda la info necesaria para cear un Articulo")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "eliminado"),
            @ApiResponse( code = 400, message = "bad request"),
            @ApiResponse( code = 403, message = "No tiene Permiso o Acceso")   })
    @DeleteMapping("/deleteByCodigo/{codigo}")
    public ResponseEntity<Void> deleteByCodigo(@Valid @PathVariable @NotNull String codigo) throws Exception {

        articuloService.deleteByCodigo(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
