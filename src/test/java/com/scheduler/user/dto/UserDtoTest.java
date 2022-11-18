package com.scheduler.user.dto;

import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDtoTest {
    @Test
    @DisplayName("Tests all UserDto's getters and setters")
    void testUserDtoGetterSetter(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserDto.class);
    }
}
