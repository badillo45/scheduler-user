package com.scheduler.user.converter;

import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;

public class UserConverter {
    public static UserDto convertUserEntityToDto(UserEntity userEntity){
        if(userEntity == null) return null;

        UserDto userDto = new UserDto();
        userDto.setUserName(userEntity.getUserName());
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword("********");
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUserStatus(userEntity.getUserStatus());
        return userDto;
    }

    public static UserEntity convertUserDtoToEntity(UserDto userDto){
        if(userDto == null) return null;

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserStatus(userDto.getUserStatus());
        return userEntity;
    }
}
