package com.miTienda.app.service.impl;

import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.miTienda.app.auth.service.JwtUtils;
import com.miTienda.app.exception.OrgNotFoundException;
import com.miTienda.app.model.entity.UserEntity;
import com.miTienda.app.model.mapper.UserMapper;
import com.miTienda.app.model.request.AuthRequest;
import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.model.request.UserUpdateRequest;
import com.miTienda.app.model.response.*;
import com.miTienda.app.repository.UserRepository;
import com.miTienda.app.service.UserService;
import com.miTienda.app.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;


    public UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException {
        return null;
    }


    public AuthResponse login(AuthRequest authRequest) {
        return null;
    }


    public UserDetailsResponse getPersonalInformation(String token) {
        return null;
    }

    @Override
    public UserDetailsResponse updateBasicUser(UserUpdateRequest request, String token) throws IOException {
        String userToken = rebuildToken(token);
        UserEntity user = userRepository.findByEmail( jwtUtils.extractUsername(userToken)).get();

        if(request.getFirstName() != null && !request.getFirstName().isEmpty() && !request.getFirstName().isBlank() ){
            user.setFirstName(request.getFirstName());}
        if(request.getLastName() != null && !request.getLastName().isEmpty() && !request.getLastName().isBlank()){
            user.setLastName(request.getLastName());}
        if(request.getPassword() != null && !request.getPassword().isEmpty() && !request.getLastName().isBlank()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));}
        if(request.getPhoto() != null && !request.getPhoto().isEmpty() && !request.getPhoto().isBlank()){
            user.setPhoto(request.getPhoto());}
        userRepository.save(user);
        return userMapper.userToUserDetail(user);

    }

    public UserDetailsResponse updateUserForAdmin(Long id, UserUpdateRequest request) throws IOException {

        UserEntity user = getById(id);

        if(request.getFirstName() != null && !request.getFirstName().isEmpty() && !request.getFirstName().isBlank() ){
            user.setFirstName(request.getFirstName());}
        if(request.getLastName() != null && !request.getLastName().isEmpty() && !request.getLastName().isBlank()){
            user.setLastName(request.getLastName());}
        if(request.getPassword() != null && !request.getPassword().isEmpty() && !request.getLastName().isBlank()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));}
        if(request.getPhoto() != null && !request.getPhoto().isEmpty() && !request.getPhoto().isBlank()){
            user.setPhoto(request.getPhoto());}
        userRepository.save(user);
        return userMapper.userToUserDetail(user);

    }

    private UserEntity getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<UserDetailsResponse> getUsers() throws IOException {
        List<UserEntity> users = userRepository.findAll();
        List<UserDetailsResponse> Response = userMapper.usersToUserDetailsList(users);
        return Response;

    }

    public PaginationResponse getAllPage(Optional<Integer> pageNumber, Optional<Integer> size) {
        PaginationUtils pagination = new PaginationUtils(userRepository, pageNumber, size, "/categories/page=%d&size=%d");
        Page page = pagination.getPage();

        List<UserEntity> users = page.getContent();
        List <UserResponse> responses = userMapper.toUsersPaginationResponse(users);

        return PaginationResponse.builder()
                .entities(users)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }

    @Override
    public void deleteUserForAdmin(Long id) {
        UserEntity user = getById(id);
        userRepository.deleteById(user.getId());
    }

    public void deleteBasicUser(String token){
        String userToken = rebuildToken(token);
        UserEntity user = userRepository.findByEmail( jwtUtils.extractUsername(userToken)).get();
        userRepository.deleteById(user.getId());
    }

    @Override

    public PaginationResponse getUserPage(Optional<Integer> pageNumber, Optional<Integer> size) {
        PaginationUtils pagination = new PaginationUtils(userRepository, pageNumber, size, "/categories/page=%d&size=%d");
        Page page = pagination.getPage();

        List<UserEntity> users = page.getContent();
        List <UserResponse> responses = userMapper.toUsersPaginationResponse(users);

        return PaginationResponse.builder()
                .entities(users)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }

    public String rebuildToken(String token){
        String [] part = token.split(" ");
        String token2 = part[1];
        return token2;
    }

}
