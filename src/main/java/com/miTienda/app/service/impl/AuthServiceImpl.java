package com.miTienda.app.service.impl;

import com.miTienda.app.auth.service.JwtUtils;
import com.miTienda.app.auth.service.impl.UserDetailsCustomService;
import com.miTienda.app.auth.service.impl.UserDetailsServiceImpl;
import com.miTienda.app.auth.utility.RoleEnum;
import com.miTienda.app.model.entity.RoleEntity;
import com.miTienda.app.model.entity.UserEntity;
import com.miTienda.app.model.mapper.UserMapper;
import com.miTienda.app.model.request.AuthRequest;
import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.model.response.AuthResponse;
import com.miTienda.app.model.response.UserDetailsResponse;
import com.miTienda.app.model.response.UserResponse;
import com.miTienda.app.repository.RoleRepository;
import com.miTienda.app.repository.UserRepository;
import com.miTienda.app.service.AuthService;
import com.miTienda.app.service.EmailService;
import com.amazonaws.services.codestar.model.UserProfileAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new UserProfileAlreadyExistsException("Email already exists");

        Set<RoleEntity> roles = roleRepository.findByName(RoleEnum.USER.getSimpleRoleName());
        if (roles.isEmpty()) {
            RoleEntity rol = new RoleEntity();
            rol.setName(RoleEnum.USER.getSimpleRoleName());
            rol = roleRepository.save(rol);
            roles.add(rol);
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
        userRepository.save(userEntity);
        if (!userRequest.getEmail().contains("@test"))
            emailService.checkFromRequest(userEntity.getEmail(), "userRegistered");
        String token = generateToken(userRequest.getEmail());
        return userMapper.toUserResponse(userEntity);
    }

    public AuthResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            String token = generateToken(authRequest.getEmail());
            return AuthResponse.builder()
                    .email(authRequest.getEmail())
                    .token(token)
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder().ok(false).build();
        }
    }

    private String generateToken(String userRequest) {
        return jwtUtils.generateToken(userDetailsCustomService.loadUserByUsername(userRequest));
    }

    public UserDetailsResponse getPersonalInformation(String token) throws IOException {
        String email = jwtUtils.extractUsername(token.substring(7));
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: " + email));

        return userMapper.userToUserDetail(user);
    }

    public void registerAdmin(UserRequest userRequest) throws IOException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new UsernameNotFoundException("User already exists");
        Set<RoleEntity> roles = roleRepository.findByName(RoleEnum.ADMIN.getSimpleRoleName());
        if (roles.isEmpty()){
            RoleEntity rol = new RoleEntity();
            rol.setName(RoleEnum.ADMIN.getSimpleRoleName());
            rol = roleRepository.save(rol);
            roles.add(rol);
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
        userRepository.save(userEntity);
        String token = generateToken(userRequest.getEmail());
    }
}
