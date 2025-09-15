package com.HAM_application.dao;


import com.HAM_application.dao.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayDao extends JpaRepository<DayEntity, Integer> {

    
    @Query("""
        SELECT DISTINCT dy 
        FROM DayEntity dy
        JOIN DoctorDayEntity dd ON dy.dayId = dd.day.dayId
        JOIN DoctorEntity d ON dd.doctor.doctorId = d.doctorId
        JOIN DoctorDaySlotEntity dds ON dd.docDayId = dds.doctorDay.docDayId
        WHERE dds.slotStatus = 'AVAILABLE'
          AND d.doctorSpecialization = :specialization
    """)
    List<DayEntity> findAvailableDaysBySpecialization(@Param("specialization") String specialization);
}

