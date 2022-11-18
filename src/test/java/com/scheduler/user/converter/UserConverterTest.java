package com.scheduler.user.converter;

import com.scheduler.user.dto.UserDto;
import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    void testConvertDTOtoEntity_Success(){
        UserDto dto = new UserDto();
        dto.setUserName("test_name");
        dto.setPassword("testpassword");
        dto.setUserStatus(UserStatus.ACTIVE);
        dto.setFirstName("first");
        dto.setLastName("last");

        UserEntity entity = UserConverter.convertUserDtoToEntity(dto);

        assertEquals(dto.getUserName(), entity.getUserName());
        assertEquals(dto.getPassword(), entity.getPassword());
        assertNull(dto.getEmail());
        assertEquals(dto.getUserStatus(), entity.getUserStatus());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
    }

    @Test
    void testConvertEntityToDTO_Success(){
        UserEntity entity = new UserEntity();
        entity.setUserName("test_name");
        entity.setPassword("testpassword");
        entity.setUserStatus(UserStatus.ACTIVE);
        entity.setFirstName("first");
        entity.setLastName("last");
        UserDto dto = UserConverter.convertUserEntityToDto(entity);

        assertEquals(entity.getUserName(), dto.getUserName());
        assertEquals("********", dto.getPassword());
        assertNotEquals(entity.getPassword(), dto.getPassword());
        assertEquals(entity.getUserStatus(), dto.getUserStatus());
        assertEquals(entity.getFirstName(), dto.getFirstName());
        assertEquals(entity.getLastName(), dto.getLastName());
        assertNull(entity.getEmail());

    }
}


