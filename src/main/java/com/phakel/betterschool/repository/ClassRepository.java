package com.phakel.betterschool.repository;

import com.phakel.betterschool.entity.Class;
import com.phakel.betterschool.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 11:18 AM
 */
public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor<Class> {
    Optional<Class> findClassByClassId(Long classId);

    boolean existsClassBySchool(School school);

    List<Class> findAll();
}
