package com.HAM_application.dao;


import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDayDao extends JpaRepository<DoctorDayEntity, Integer> {

    
    List<DoctorDayEntity> findByDoctor(DoctorEntity doctor);

    
    @Query("""
        SELECT DISTINCT dd.day
        FROM DoctorDayEntity dd
        JOIN dd.doctor d
        JOIN DoctorDaySlotEntity dds ON dd.docDayId = dds.doctorDay.docDayId
        WHERE d.doctorSpecialization = :specialization
          AND dds.slotStatus = 'AVAILABLE'
    """)
    List<?> findAvailableDaysBySpecialization(@Param("specialization") String specialization);
}

