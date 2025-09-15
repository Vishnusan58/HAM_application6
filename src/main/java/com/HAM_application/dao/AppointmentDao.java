package com.HAM_application.dao;


import com.HAM_application.dao.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentDao extends JpaRepository<AppointmentEntity, Integer> {

    List<AppointmentEntity> findByDoctorDaySlotDoctorDayDocDayIdAndAppointmentDate(
            Integer docDayId, LocalDate date);

    List<AppointmentEntity> findByPatientPatientId(Integer patientId);

    List<AppointmentEntity> findByDoctorDoctorIdAndStatus(Integer doctorId, String status);
}

