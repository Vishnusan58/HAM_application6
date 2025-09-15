package com.HAM_application.dao;

import com.HAM_application.dao.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Integer> {

   
    Optional<PatientEntity> findByPhoneNumber(String phoneNumber);
    Optional<PatientEntity> findByAadhaarNumber(String aadhaarNumber);
}

