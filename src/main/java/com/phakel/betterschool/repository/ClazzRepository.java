package com.phakel.betterschool.repository;

import com.phakel.betterschool.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 11:18 AM
 */
public interface ClazzRepository extends JpaRepository<Clazz, Long>, JpaSpecificationExecutor<Clazz> {
    Optional<Clazz> findClazzByClassId(Long classId);
}
