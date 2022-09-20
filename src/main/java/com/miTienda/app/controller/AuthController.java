package com.miTienda.app.controller;

import com.miTienda.app.model.request.AuthRequest;
import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.model.response.ApiErrorResponse;
import com.miTienda.app.model.response.AuthResponse;
import com.miTienda.app.model.response.UserDetailsResponse;
import com.miTienda.app.model.response.UserResponse;
import com.miTienda.app.service.AuthService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
@Api(value = "Operations related to Authentication", tags = "Authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ApiOperation(value = "Registrar un Usuario", code = 201, response = UserResponse.class)
    @ApiResponses(value = {  @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
                             @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
                             @ApiResponse(code = 404, message = "Not Found", response = ApiErrorResponse.class) })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login de Usuario", response = AuthResponse.class)
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }


    @GetMapping("/me")
    @ApiOperation(value = "Obtener un Usuario", response = UserDetailsResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDetailsResponse.class),
                            @ApiResponse(code = 404, message = "Not Found", response = ApiErrorResponse.class) })
    public ResponseEntity<UserDetailsResponse> getPersonalInformation(@RequestHeader(name = "Authorization") String token) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getPersonalInformation(token));
    }

}