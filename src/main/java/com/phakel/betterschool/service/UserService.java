package com.phakel.betterschool.service;

import com.phakel.betterschool.dto.UserInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:35 PM
 */
public interface UserService {
    Optional<UserInfo> findUserByUserId(Long userId);

    Optional<UserInfo> findUserByUserName(String userName);

    List<UserInfo> findUsersBySchoolName(String schoolName);

    List<UserInfo> findUsersBySchoolId(Long schoolId);

    List<UserInfo> findUsersByClassId(Long classId);
}
