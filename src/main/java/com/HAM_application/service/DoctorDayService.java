package com.HAM_application.service;

import com.HAM_application.dao.DoctorDayDao;
import com.HAM_application.dao.entity.DayEntity;
import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorDayService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorDayService.class);

    private final DoctorDayDao doctorDayDao;

    public DoctorDayService(DoctorDayDao doctorDayDao) {
        this.doctorDayDao = doctorDayDao;
    }

    
    public DoctorDayEntity assignDoctorToDay(DoctorEntity doctor, DayEntity day) {
        logger.info("Assigning doctor ID {} to day ID {}", doctor.getDoctorId(), day.getDayId());
        DoctorDayEntity doctorDay = new DoctorDayEntity(doctor, day);
        return doctorDayDao.save(doctorDay);
    }

    
    public List<DoctorDayEntity> getDoctorDays(DoctorEntity doctor) {
        logger.info("Fetching days assigned for doctor ID {}", doctor.getDoctorId());
        return doctorDayDao.findByDoctor(doctor);
    }

    
    public List<DayEntity> getAvailableDaysBySpecialization(String specialization) {
        logger.info("Fetching available days for specialization: {}", specialization);
        return (List<DayEntity>) doctorDayDao.findAvailableDaysBySpecialization(specialization);
    }
}


