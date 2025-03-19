package com.gure.cinab.controller.user;

import com.gure.cinab.dto.UserDTO;
import com.gure.cinab.exceptions.AlreadyExistsException;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.User;
import com.gure.cinab.request.user.CreateUserRequest;
import com.gure.cinab.request.user.UpdateUserRequest;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController implements IUserController {

    private final IUserService userService;

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping("/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            UserDTO userDTO = userService.convertUserToDTO(user);
            return ResponseEntity.ok(new ApiResponse("Success!", userDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));

        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            User newUser = userService.createUser(request);
            UserDTO userDTO = userService.convertUserToDTO(newUser);
            return ResponseEntity
                    .ok(new ApiResponse("Created User Successfully!", userDTO));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request,
                                                  @PathVariable Long userId) {
        try {
            User updateUser = userService.updateUser(request, userId);
            UserDTO userDTO = userService.convertUserToDTO(updateUser);
            return ResponseEntity.ok(new ApiResponse("Updated User Successfully!", userDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("Deleted User Successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
