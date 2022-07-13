package com.phakel.betterschool.repository;

import com.phakel.betterschool.entity.Clazz;
import com.phakel.betterschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 11:11 AM
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findUserByUserId(Long userId);
    Optional<User> findUserByUserName(String userName);
}
