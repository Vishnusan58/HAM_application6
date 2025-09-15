package com.HAM_application.service;

import com.HAM_application.dao.DoctorDao;
import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.DoctorNotFoundException;
import com.HAM_application.exception.SpecializationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorDao doctorDao;
    private final SpecializationFeeDao specializationFeeDao;

    public DoctorService(DoctorDao doctorDao, SpecializationFeeDao specializationFeeDao) {
        this.doctorDao = doctorDao;
        this.specializationFeeDao = specializationFeeDao;
    }

    
    public DoctorEntity create(DoctorEntity doctor) {
        logger.info("Creating doctor: {}", doctor.getDoctorName());
        return doctorDao.save(doctor);
    }

    public List<DoctorEntity> getAll() {
        logger.info("Fetching all doctors");
        return doctorDao.findAll();
    }

    public DoctorEntity getById(Integer id) {
        logger.info("Fetching doctor by ID: {}", id);
        return doctorDao.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with ID: " + id));
    }

    public DoctorEntity update(Integer id, DoctorEntity updates) {
        logger.info("Updating doctor ID: {}", id);
        DoctorEntity doctor = getById(id);
        if (updates.getDoctorName() != null) {
            logger.info("Updating name to: {}", updates.getDoctorName());
            doctor.setDoctorName(updates.getDoctorName());
        }
        if (updates.getDoctorSpecialization() != null) {
            logger.info("Updating specialization to: {}", updates.getDoctorSpecialization());
            doctor.setDoctorSpecialization(updates.getDoctorSpecialization());
        }
        return doctorDao.save(doctor);
    }

    public void delete(Integer id) {
        logger.info("Deleting doctor ID: {}", id);
        DoctorEntity doctor = getById(id);
        doctorDao.delete(doctor);
    }

    
    public Optional<DoctorEntity> getDoctorsByDaySlotSpecialization(String dayName, String slotTime, String specialization) {
        logger.info("Fetching doctor by day: {}, slot: {}, specialization: {}", dayName, slotTime, specialization);
        return doctorDao.findAvailableDoctorsByDaySlotSpecialization(dayName, slotTime, specialization);
    }

    
    public BigDecimal getFeeByDoctorId(Integer doctorId) {
        logger.info("Fetching fee for doctor ID: {}", doctorId);
        DoctorEntity doctor = getById(doctorId);
        return getFeeBySpecialization(doctor.getDoctorSpecialization());
    }

    public BigDecimal getFeeByDoctorName(String doctorName) {
        logger.info("Fetching fee for doctor name: {}", doctorName);
        DoctorEntity doctor = doctorDao.findByDoctorName(doctorName)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with name: " + doctorName));
        return getFeeBySpecialization(doctor.getDoctorSpecialization());
    }

    private BigDecimal getFeeBySpecialization(String specialization) {
        logger.info("Fetching fee for specialization: {}", specialization);
        SpecializationFeeEntity feeEntity = specializationFeeDao.findBySpecialization(specialization)
                .orElseThrow(() -> new SpecializationNotFoundException("Fee not found for specialization: " + specialization));
        return feeEntity.getFee();
    }
}




