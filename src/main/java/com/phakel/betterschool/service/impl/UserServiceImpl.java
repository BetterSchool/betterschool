package com.phakel.betterschool.service.impl;

import com.phakel.betterschool.dto.ClassInfo;
import com.phakel.betterschool.dto.SchoolInfo;
import com.phakel.betterschool.dto.UserInfo;
import com.phakel.betterschool.entity.User;
import com.phakel.betterschool.repository.ClassRepository;
import com.phakel.betterschool.repository.SchoolRepository;
import com.phakel.betterschool.repository.UserRepository;
import com.phakel.betterschool.service.UserService;
import com.phakel.betterschool.util.TokenUtil;
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
    private final ClassRepository classRepository;

    private final TokenUtil tokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SchoolRepository schoolRepository, ClassRepository classRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.classRepository = classRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public Optional<UserInfo> findUserByUserId(Long userId) {
        return userRepository.findUserByUserId(userId)
                .map(UserInfo::new);
    }

    @Override
    public Optional<UserInfo> findUserByUsername(String userName) {
        return userRepository.findUserByUsername(userName)
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
        return classRepository.findClassByClassId(classId)
                .map(ClassInfo::new)
                .map(ClassInfo::getUsers)
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean checkUserIsInSchoolByUsername(String username, Long schoolId) {
        Optional<SchoolInfo> schoolInfo = schoolRepository.findSchoolBySchoolId(schoolId)
                .map(SchoolInfo::new);

        Optional<UserInfo> userInfo = userRepository.findUserByUsername(username)
                .map(UserInfo::new);

        if (schoolInfo.isEmpty() || userInfo.isEmpty()) {
            return false;
        }

        System.out.println(schoolInfo.get().getUsers());

        return schoolInfo.get().getUsers().contains(userInfo.get());
    }

    @Override
    public boolean checkUserIsInSchoolByUsername(String username, String schoolName) {
        Optional<SchoolInfo> schoolInfo = schoolRepository.findSchoolBySchoolName(schoolName)
                .map(SchoolInfo::new);

        Optional<UserInfo> userInfo = userRepository.findUserByUsername(username)
                .map(UserInfo::new);

        if (schoolInfo.isEmpty() || userInfo.isEmpty()) {
            return false;
        }

        return schoolInfo.get().getUsers().contains(userInfo.get());
    }

    @Override
    public boolean checkUserIsInClassByUsername(String username, Long classId) {
        Optional<ClassInfo> classInfo = classRepository.findClassByClassId(classId)
                .map(ClassInfo::new);

        Optional<UserInfo> userInfo = userRepository.findUserByUsername(username)
                .map(UserInfo::new);

        if (classInfo.isEmpty() || userInfo.isEmpty()) {
            System.out.println("A");
            return false;
        }

        return classInfo.get().getUsers().contains(userInfo.get());
    }

    @Override
    public boolean validateLogin(String userName, String password) {
        return userRepository.findUserByUsername(userName)
                .map(it -> BCrypt.checkpw(password, it.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean createUser(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
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
