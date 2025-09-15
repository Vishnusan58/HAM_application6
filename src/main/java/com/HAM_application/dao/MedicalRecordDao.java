package com.HAM_application.dao;


import com.HAM_application.dao.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordDao extends JpaRepository<MedicalRecordEntity, Integer> {

    
    List<MedicalRecordEntity> findByAppointmentAppointmentId(Integer appointmentId);

    
    @Query("SELECT mr FROM MedicalRecordEntity mr WHERE mr.appointment.patient.patientId = :patientId")
    List<MedicalRecordEntity> findByPatientPatientId(@Param("patientId") Integer patientId);
}

