package com.scheduler.user.service;

import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;

import java.util.List;

public interface UserService {
    List<UserEntity> findAllUsers();

    List<UserEntity> findAllUsersByStatus(UserStatus userStatus);

    UserEntity saveUser(UserEntity userEntity);

    UserEntity findUserById(UserDto userDto);

    void deleteUserById(Long userId);
}
