package com.phakel.betterschool.service;

import com.phakel.betterschool.dto.UserInfo;
import com.phakel.betterschool.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:35 PM
 */
public interface UserService {
    Optional<UserInfo> findUserByUserId(Long userId);

    Optional<UserInfo> findUserByUsername(String userName);

    List<UserInfo> findUsersBySchoolName(String schoolName);

    List<UserInfo> findUsersBySchoolId(Long schoolId);

    List<UserInfo> findUsersByClassId(Long classId);

    boolean checkUserIsInSchoolByUsername(String username, Long schoolId);

    boolean checkUserIsInSchoolByUsername(String username, String schoolName);

    boolean checkUserIsInClassByUsername(String username, Long classId);

    boolean validateLogin(String userName, String password);

    boolean createUser(User user);

    boolean deleteUser(Long userId);
}
