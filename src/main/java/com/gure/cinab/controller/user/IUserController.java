package com.gure.cinab.controller.user;

import com.gure.cinab.request.user.CreateUserRequest;
import com.gure.cinab.request.user.UpdateUserRequest;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface IUserController {

    ResponseEntity<ApiResponse> getUserById(Long userId);

    ResponseEntity<ApiResponse> createUser(CreateUserRequest request);

    ResponseEntity<ApiResponse> updateUser(UpdateUserRequest request, Long userId);

    ResponseEntity<ApiResponse> deleteUser(Long userId);
}
