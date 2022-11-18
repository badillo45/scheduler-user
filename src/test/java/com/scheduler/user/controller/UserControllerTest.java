package com.scheduler.user.controller;

import com.scheduler.user.converter.UserConverter;
import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import com.scheduler.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    List<UserEntity> userList;
    UserEntity userEntity;
    UserDto userDto;

    @BeforeEach
    void initObjects() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserName("test_username");
        userEntity.setPassword("testpassword");
        userEntity.setUserStatus(UserStatus.ACTIVE);
        userEntity.setFirstName("first");
        userEntity.setLastName("last");

        userDto = new UserDto();
        userDto.setUserName("test_username");
        userDto.setPassword("testpassword");
        userDto.setUserStatus(UserStatus.ACTIVE);
        userDto.setFirstName("first");
        userDto.setLastName("last");

        userList = new ArrayList<>();
        userList.add(userEntity);
    }

    @Test
    @DisplayName("Test Success Scenario for find All Users")
    void testFindAllUsers(){
        when(userService.findAllUsers()).thenReturn(userList);
        ResponseEntity<List<UserDto>> responseEntity = userController.findAllUsers();
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for find All Users without results retrieved")
    void testFindAllUsers_empty(){
        List<UserEntity> emptyUserList = new ArrayList<>();
        when(userService.findAllUsers()).thenReturn(emptyUserList);
        ResponseEntity<List<UserDto>> responseEntity = userController.findAllUsers();
        assertNull( responseEntity.getBody());
        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());
    }
    @Test
    @DisplayName("Test Exception Scenario for find All Users")
    void testFindAllUsers_exception(){
        when(userService.findAllUsers()).thenThrow(NullPointerException.class);
        ResponseEntity<List<UserDto>> responseEntity = userController.findAllUsers();
        assertNull( responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for find All Users By Status")
    void testFindAllUsersByStatus(){
        Integer userStatusInt = 1;
        UserStatus userStatus = UserStatus.fromInteger(userStatusInt);

        when(userService.findAllUsersByStatus(userStatus)).thenReturn(userList);

        ResponseEntity<List<UserDto>> responseEntity = userController.findAllUsersByStatus(userStatusInt);
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(userStatus, responseEntity.getBody().get(0).getUserStatus());
    }

    @Test
    @DisplayName("Test Success Scenario for find All Users By Status without results retrieved")
    void testFindAllUsersByStatus_empty(){
        List<UserEntity> emptyUserList = new ArrayList<>();
        Integer userStatusInt = 1;
        UserStatus userStatus = UserStatus.fromInteger(userStatusInt);

        when(userService.findAllUsersByStatus(userStatus)).thenReturn(emptyUserList);
        ResponseEntity<List<UserDto>> responseEntity = userController.findAllUsersByStatus(userStatusInt);
        assertNull( responseEntity.getBody());
        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for Saving User")
    void testSaveUser(){
        UserEntity userEntityInput = UserConverter.convertUserDtoToEntity(userDto);
        UserEntity savedEntity = userEntity;

        Mockito.when(userService.saveUser(userEntityInput)).thenReturn(savedEntity);

        ResponseEntity<UserDto> responseEntity = userController.saveUser(userDto);
        assertNotNull(responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for Updating User ")
    void testSaveUser_PUT(){
        UserEntity userEntityInput = UserConverter.convertUserDtoToEntity(userDto);
        UserEntity savedEntity = userEntity;

        when(userService.saveUser(userEntityInput)).thenReturn(savedEntity);

        ResponseEntity<UserDto> responseEntity = userController.saveUser(userDto, userDto.getId());
        assertNotNull(responseEntity.getBody().getId());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals(userDto.getUserName(), userEntity.getUserName());
        assertEquals(userDto.getPassword(), userEntity.getPassword());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getUserStatus(), userEntity.getUserStatus());
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
    }

    @Test
    @DisplayName("Test Success Scenario for Updating UserName")
    void testUpdatePropertyPrice(){

        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setUserName("new_userName");

        when(userService.findUserById(dto)).thenReturn(userEntity);
        userEntity.setUserName(dto.getUserName());
        when(userService.saveUser(userEntity)).thenReturn(userEntity);

        ResponseEntity<UserDto> responseEntity = userController.updateUserName(dto, 1L);

        assertEquals(dto.getUserName(), responseEntity.getBody().getUserName());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for deleting User")
    void testdeleteUser(){
        Long userId = 1L;

        ResponseEntity<HttpStatus> responseEntity = userController.deleteUser(userId);
        assertNull( responseEntity.getBody());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }
}
