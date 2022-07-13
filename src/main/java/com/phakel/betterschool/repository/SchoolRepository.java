package com.phakel.betterschool.repository;

import com.phakel.betterschool.entity.School;
import com.phakel.betterschool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 11:15 AM
 */
public interface SchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {
    Optional<School> findSchoolBySchoolId(Long schoolId);
    Optional<School> findSchoolBySchoolName(String schoolName);
}
