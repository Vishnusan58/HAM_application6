package com.HAM_application.dao;


import com.HAM_application.dao.entity.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotDao extends JpaRepository<SlotEntity, Integer> {

    
    @Query("""
        SELECT DISTINCT s
        FROM SlotEntity s
        JOIN DoctorDaySlotEntity dds ON s.slotId = dds.slot.slotId
        JOIN DoctorDayEntity dd ON dds.doctorDay.docDayId = dd.docDayId
        JOIN DoctorEntity d ON dd.doctor.doctorId = d.doctorId
        JOIN DayEntity dy ON dd.day.dayId = dy.dayId
        WHERE dds.slotStatus = 'AVAILABLE'
          AND dy.dayName = :dayName
          AND d.doctorSpecialization = :specialization
    """)
    List<SlotEntity> findAvailableSlotsByDayAndSpecialization(
            @Param("dayName") String dayName,
            @Param("specialization") String specialization);
}

