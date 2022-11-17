package com.scheduler.user.repository;

import com.scheduler.user.entity.UserEntity;
import com.scheduler.user.enums.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAllByUserStatus(UserStatus userStatus);
}
