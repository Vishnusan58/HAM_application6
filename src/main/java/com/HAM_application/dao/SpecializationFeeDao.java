package com.HAM_application.dao;

import com.HAM_application.dao.entity.SpecializationFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationFeeDao extends JpaRepository<SpecializationFeeEntity, String> {


    Optional<SpecializationFeeEntity> findBySpecialization(String specialization);
}
