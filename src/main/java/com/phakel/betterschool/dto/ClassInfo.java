package com.phakel.betterschool.dto;

import com.phakel.betterschool.entity.Class;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:41 PM
 */
@Data
public class ClassInfo {
    private Long classId;
    private String className;
    private List<UserInfo> users;

    public ClassInfo(Class clazz) {
        this.classId = clazz.getClassId();
        this.className = clazz.getClassName();
        this.users = clazz.getUsers()
                .stream()
                .map(UserInfo::new)
                .collect(Collectors.toList());
    }
}
