package com.HAM_application.dao;


import com.HAM_application.dao.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorDao extends JpaRepository<DoctorEntity, Integer> {

    Optional<DoctorEntity> findByDoctorName(String doctorName);
    
    DoctorEntity findByDoctorSpecialization(String specialization);
    
    @Query("""
    	    SELECT d FROM DoctorEntity d
    	    JOIN DoctorDayEntity dd ON d.doctorId = dd.doctor.doctorId
    	    JOIN DayEntity dy ON dd.day.dayId = dy.dayId
    	    JOIN DoctorDaySlotEntity dds ON dd.docDayId = dds.doctorDay.docDayId
    	    JOIN SlotEntity s ON dds.slot.slotId = s.slotId
    	    WHERE dy.dayName = :dayName
    	      AND s.slotTime = :slotTime
    	      AND dds.slotStatus = 'AVAILABLE'
    	      AND (:specialization IS NULL OR d.doctorSpecialization = :specialization)
    	""")
    	Optional<DoctorEntity> findAvailableDoctorsByDaySlotSpecialization(
    	        @Param("dayName") String dayName,
    	        @Param("slotTime") String slotTime,
    	        @Param("specialization") String specialization);


    
}

