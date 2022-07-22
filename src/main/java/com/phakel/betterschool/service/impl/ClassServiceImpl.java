package com.phakel.betterschool.service.impl;

import com.phakel.betterschool.entity.Class;
import com.phakel.betterschool.repository.ClassRepository;
import com.phakel.betterschool.repository.UserRepository;
import com.phakel.betterschool.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author EvanLuo42
 * @date 7/19/22 10:05 AM
 */
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    private final UserRepository userRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUserToClassByUserIdAndClassId(Long userId, Long classId) {
        System.out.println(classRepository.findAll().get(0).getClassId());
        return classRepository.findClassByClassId(classId)
                .map(it -> {
                    if (!userRepository.existsUserByUserId(userId)) {
                        System.out.println("A");
                        return false;
                    }

                    if (it.getUsers().contains(userRepository.findUserByUserId(userId).get())) {
                        return false;
                    }

                    it.getUsers().add(userRepository.findUserByUserId(userId).get());
                    classRepository.save(it);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean addUserToClassByUsernameAndClassId(String username, Long classId) {
        return classRepository.findClassByClassId(classId)
                .map(it -> {
                    if (!userRepository.existsUserByUsername(username)) {
                        return false;
                    }

                    it.getUsers().add(userRepository.findUserByUsername(username).get());
                    classRepository.save(it);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean createClass(Class clazz) {
        if (classRepository.existsClassBySchool(clazz.getSchool())) {
            return false;
        }

        classRepository.save(clazz);
        return true;
    }
}
