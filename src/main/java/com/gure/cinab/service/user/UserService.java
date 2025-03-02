package com.gure.cinab.service.user;

import com.gure.cinab.exceptions.AlreadyExistsException;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.User;
import com.gure.cinab.repository.UserRepository;
import com.gure.cinab.request.user.CreateUserRequest;
import com.gure.cinab.request.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User newUser = new User();
                    newUser.setEmail(request.getEmail());
                    newUser.setPassword(req.getPassword());
                    newUser.setFirstName(req.getFirstName());
                    newUser.setLastName(request.getLastName());
                    return userRepository.save(newUser);
                })
                .orElseThrow(() -> new AlreadyExistsException(request.getEmail() + " already exists!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(request.getFirstName());
                    existingUser.setLastName(request.getLastName());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("User not found");
                        });
    }
}
