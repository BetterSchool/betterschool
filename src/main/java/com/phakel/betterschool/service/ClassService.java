package com.phakel.betterschool.service;


import com.phakel.betterschool.entity.Class;

/**
 * @author EvanLuo42
 * @date 7/19/22 10:03 AM
 */
public interface ClassService {
    boolean addUserToClassByUserIdAndClassId(Long userId, Long classId);

    boolean addUserToClassByUsernameAndClassId(String username, Long classId);

    boolean createClass(Class clazz);
}
