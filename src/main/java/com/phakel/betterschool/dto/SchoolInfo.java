package com.phakel.betterschool.dto;

import com.phakel.betterschool.entity.School;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author EvanLuo42
 * @date 7/13/22 7:00 PM
 */
@Data
public class SchoolInfo {
    private Long schoolId;
    private String schoolName;
    private List<ClassInfo> classes;
    private List<UserInfo> users;

    public SchoolInfo(School school) {
        this.schoolId = school.getSchoolId();
        this.schoolName = school.getSchoolName();
        this.classes = school.getClasses()
                .stream()
                .map(ClassInfo::new)
                .collect(Collectors.toList());
    }
}
