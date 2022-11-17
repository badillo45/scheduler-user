package com.scheduler.user.service;

import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import com.scheduler.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers(){
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public List<UserEntity> findAllUsersByStatus(UserStatus userStatus) {
        return (List<UserEntity>) userRepository.findAllByUserStatus(userStatus);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        if(userEntity.getId() == null){
            userEntity.setUserStatus(UserStatus.ACTIVE);
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(UserDto userDto) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(userDto.getId());
        return userEntityOpt.isPresent() ? userEntityOpt.get() : null;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
