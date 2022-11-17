package com.scheduler.user.controller;

import com.scheduler.user.converter.UserConverter;
import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import com.scheduler.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        try {
            List<UserDto> users = userService.findAllUsers()
                    .stream()
                    .map((userEntity -> UserConverter.convertUserEntityToDto(userEntity)))
                    .collect(Collectors.toList());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userStatus}")
    public ResponseEntity<List<UserDto>> findAllUsersByStatus(@PathVariable Integer userStatus){
        try {
            List<UserDto> users = userService.findAllUsersByStatus(UserStatus.fromInteger(userStatus))
                    .stream()
                    .map((userEntity -> UserConverter.convertUserEntityToDto(userEntity)))
                    .collect(Collectors.toList());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        try {
            UserEntity userEntity = UserConverter.convertUserDtoToEntity(userDto);
            UserDto savedUser = UserConverter.convertUserEntityToDto(userService.saveUser(userEntity));

            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto, @PathVariable Long userId){
        try {
            userDto.setId(userId);
            UserEntity userEntity = UserConverter.convertUserDtoToEntity(userDto);
            UserDto savedUser = UserConverter.convertUserEntityToDto(userService.saveUser(userEntity));

            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserName(@RequestBody UserDto userDto, @PathVariable Long userId){
        try {
            userDto.setId(userId);
            UserEntity userEntity = userService.findUserById(userDto);
            userEntity.setUserName(userDto.getUserName());
            UserDto updatedUser = UserConverter.convertUserEntityToDto(userService.saveUser(userEntity));

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
