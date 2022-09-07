package com.miTienda.app.service;

import com.miTienda.app.model.request.AuthRequest;
import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.model.response.AuthResponse;
import com.miTienda.app.model.response.UserDetailsResponse;
import com.miTienda.app.model.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public interface AuthService {
    UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException;
    AuthResponse login(AuthRequest authRequest);
    UserDetailsResponse getPersonalInformation(String token) throws IOException;
    void registerAdmin(UserRequest userRequest) throws IOException;
}


