package com.scheduler.user.entity;

import com.scheduler.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserEntity {
    @Id()
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 50)
    String userName;
    @Column(length = 50)
    String password;
    String firstName;
    String lastName;
    String email;
    @Column(nullable = false)
    UserStatus userStatus;
}
