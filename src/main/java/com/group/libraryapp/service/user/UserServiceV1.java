package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUser() {
        List<UserResponse> result = userJdbcRepository.getAllUsers();
        return result;
    }

    public void updateUser(UserUpdateRequest request) {
        boolean isUserNotExist = userJdbcRepository.isUserNotExist(request.getId());
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUser(request.getId(), request.getName());
    }

    public void deleteUser(String name) {
        boolean isUserNotExist = userJdbcRepository.isUserNotExist(name);
        if (isUserNotExist)
            throw new IllegalArgumentException();

        userJdbcRepository.deleteUser(name);
    }
}
