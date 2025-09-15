package com.HAM_application.dao;


import com.HAM_application.dao.entity.DoctorDaySlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorDaySlotDao extends JpaRepository<DoctorDaySlotEntity, Integer> {

    
    Optional<DoctorDaySlotEntity> findByDoctorDayDocDayId(Integer docDayId);

   
    @Query("""
        SELECT dds
        FROM DoctorDaySlotEntity dds
        JOIN dds.doctorDay dd
        JOIN dd.doctor d
        JOIN dd.day dy
        WHERE dds.slotStatus = 'AVAILABLE'
          AND dy.dayName = :dayName
          AND d.doctorSpecialization = :specialization
    """)
    Optional<DoctorDaySlotEntity> findAvailableSlotsByDayAndSpecialization(
            @Param("dayName") String dayName,
            @Param("specialization") String specialization
    );
}

