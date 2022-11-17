package com.scheduler.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.scheduler.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    Long id;
    @NotEmpty
    @Size(min = 5,max = 50, message = "The username must be 5-50 characters.")
    String userName;
    @Size(min = 8,max = 50, message = "The password must be 8-50 characters.")
    String password;
    String firstName;
    String lastName;
    @NotEmpty(message = "The email must not be empty.")
    String email;
    UserStatus userStatus;

}
