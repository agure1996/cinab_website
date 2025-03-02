package com.gure.cinab.service.user;

import com.gure.cinab.model.User;
import com.gure.cinab.request.user.CreateUserRequest;
import com.gure.cinab.request.user.UpdateUserRequest;

public interface IUserService {

    User getUserById (Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);
}
