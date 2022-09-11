package com.miTienda.app.service;


import com.miTienda.app.model.request.UserUpdateRequest;
import com.miTienda.app.model.response.PaginationResponse;
import com.miTienda.app.model.response.UserDetailsResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsResponse updateBasicUser(UserUpdateRequest request, String token) throws IOException;
    UserDetailsResponse updateUserForAdmin(Long id, UserUpdateRequest request) throws IOException;
    List<UserDetailsResponse> getUsers() throws IOException;

    //UsersPaginationResponse getPaginationUsers(Integer page);
    void deleteUserForAdmin(Long id);

    void deleteBasicUser(String token);

    PaginationResponse getUserPage(Optional<Integer> pageNumber, Optional<Integer> size);


}

