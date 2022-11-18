package com.scheduler.user.service;

import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import com.scheduler.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    List<UserEntity> userList;
    UserEntity userEntity;

    @BeforeEach
    void initObjects() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserName("test_username");
        userEntity.setPassword("testpassword");
        userEntity.setUserStatus(UserStatus.ACTIVE);
        userEntity.setFirstName("first");
        userEntity.setLastName("last");

        userList = new ArrayList<>();
        userList.add(userEntity);
    }

    @Test
    @DisplayName("Test Success Scenario for find All Users")
    void testFindAllUsers(){
        when(userRepository.findAll()).thenReturn(userList);
        List<UserEntity> userEntityList = userService.findAllUsers();
        assertEquals(1, userEntityList.size());
    }

    @Test
    @DisplayName("Test Success Scenario for find All User By Status")
    void testFindAllUsersByStatus(){
        when(userRepository.findAllByUserStatus(UserStatus.ACTIVE)).thenReturn(userList);
        List<UserEntity> userEntityList = userService.findAllUsersByStatus(UserStatus.ACTIVE);
        assertEquals(1, userEntityList.size());
        assertEquals(UserStatus.ACTIVE, userEntityList.get(0).getUserStatus());
    }

    @Test
    @DisplayName("Test Success Scenario for find User By Id")
    void testFindUserById(){
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        UserEntity userEntity = userService.findUserById(userDto);
        assertEquals(userId, userEntity.getId());
    }

    @Test
    @DisplayName("Test Success Scenario for saving User without UserId")
    void testSaveUser_noId(){
        UserEntity newUser = new UserEntity();
        newUser.setUserName("test_username");
        newUser.setPassword("testpassword");
        newUser.setFirstName("first");
        newUser.setLastName("last");

        when(userRepository.save(newUser)).thenReturn(userEntity);
        UserEntity savedUserEntity = userService.saveUser(newUser);
        assertNotNull(savedUserEntity.getId());
        assertEquals(UserStatus.ACTIVE, savedUserEntity.getUserStatus());
    }

    @Test
    @DisplayName("Test Success Scenario for saving User without UserId")
    void testSaveUser_withId(){
        UserEntity newUser = new UserEntity();
        newUser.setId(1L);
        newUser.setUserName("test_username");
        newUser.setPassword("testpassword");
        newUser.setFirstName("first");
        newUser.setLastName("last");
        newUser.setUserStatus(UserStatus.ACTIVE);

        when(userRepository.save(newUser)).thenReturn(userEntity);
        UserEntity savedUserEntity = userService.saveUser(newUser);
        assertNotNull(savedUserEntity.getId());
        assertEquals(UserStatus.ACTIVE, savedUserEntity.getUserStatus());
    }

    @Test
    @DisplayName("Test Success Scenario for deleting User")
    void deleteUserById(){
        Long userId = 1L;
        userService.deleteUserById(userId);
        verify(userRepository).deleteById(userId);
    }
}
