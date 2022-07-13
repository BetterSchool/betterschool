package com.phakel.betterschool.service.impl;

import com.phakel.betterschool.dto.ClassInfo;
import com.phakel.betterschool.dto.SchoolInfo;
import com.phakel.betterschool.dto.UserInfo;
import com.phakel.betterschool.entity.User;
import com.phakel.betterschool.repository.ClazzRepository;
import com.phakel.betterschool.repository.SchoolRepository;
import com.phakel.betterschool.repository.UserRepository;
import com.phakel.betterschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:37 PM
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final ClazzRepository clazzRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SchoolRepository schoolRepository, ClazzRepository clazzRepository) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.clazzRepository = clazzRepository;
    }

    @Override
    public Optional<UserInfo> findUserByUserId(Long userId) {
        return userRepository.findUserByUserId(userId)
                .map(UserInfo::new);
    }

    @Override
    public Optional<UserInfo> findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName)
                .map(UserInfo::new);
    }

    @Override
    public List<UserInfo> findUsersBySchoolName(String schoolName) {
        return schoolRepository.findSchoolBySchoolName(schoolName)
                .map(SchoolInfo::new)
                .map(SchoolInfo::getUsers)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<UserInfo> findUsersBySchoolId(Long schoolId) {
        return schoolRepository.findSchoolBySchoolId(schoolId)
                .map(SchoolInfo::new)
                .map(SchoolInfo::getUsers)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<UserInfo> findUsersByClassId(Long classId) {
        return clazzRepository.findClazzByClassId(classId)
                .map(ClassInfo::new)
                .map(ClassInfo::getUsers)
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean validateLogin(String userName, String password) {
        return userRepository.findUserByUserName(userName)
                .map(it -> BCrypt.checkpw(password, it.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean createUser(User user) {
        if (userRepository.existsUserByUserName(user.getUserName())) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (!userRepository.existsUserByUserId(userId)) {
            return false;
        }

        userRepository.deleteById(userId);
        return true;
    }
}
