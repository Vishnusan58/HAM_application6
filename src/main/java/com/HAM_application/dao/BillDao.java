package com.HAM_application.dao;


import com.HAM_application.dao.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BillDao extends JpaRepository<BillEntity, Integer> {

    List<BillEntity> findByAppointmentAppointmentId(Integer appointmentId);

    @Query("SELECT b FROM BillEntity b WHERE b.appointment.patient.patientId = :patientId")
    List<BillEntity> findByPatientId(@Param("patientId") Integer patientId);
}

