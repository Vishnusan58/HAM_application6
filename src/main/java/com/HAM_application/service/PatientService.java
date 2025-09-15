package com.HAM_application.service;

import com.HAM_application.dao.PatientDao;
import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.exception.DuplicateResourceException;
import com.HAM_application.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientDao dao;

    public PatientService(PatientDao dao) {
        this.dao = dao;
    }

    
    public PatientEntity register(PatientEntity patient) {
        logger.info("Registering new patient with phone: {} and Aadhaar: {}", patient.getPhoneNumber(), patient.getAadhaarNumber());

        dao.findByPhoneNumber(patient.getPhoneNumber())
                .ifPresent(p -> {
                    logger.warn("Duplicate phone number detected: {}", patient.getPhoneNumber());
                    throw new DuplicateResourceException("Phone number already exists: " + patient.getPhoneNumber());
                });

        dao.findByAadhaarNumber(patient.getAadhaarNumber())
                .ifPresent(p -> {
                    logger.warn("Duplicate Aadhaar detected: {}", patient.getAadhaarNumber());
                    throw new DuplicateResourceException("Aadhaar already exists: " + patient.getAadhaarNumber());
                });

        PatientEntity savedPatient = dao.save(patient);
        logger.info("Patient registered successfully with ID: {}", savedPatient.getPatientId());
        return savedPatient;
    }

    
    public List<PatientEntity> getAll() {
        logger.info("Fetching all patients");
        return dao.findAll();
    }

    
    public PatientEntity getById(Integer id) {
        logger.info("Fetching patient with ID: {}", id);
        return dao.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Patient not found with ID: {}", id);
                    return new PatientNotFoundException("Patient not found with ID: " + id);
                });
    }

    
    public PatientEntity update(Integer id, PatientEntity updates) {
        logger.info("Updating patient with ID: {}", id);
        PatientEntity patient = getById(id);

        if (updates.getPhoneNumber() != null) {
            Optional<PatientEntity> existingPhone = dao.findByPhoneNumber(updates.getPhoneNumber());
            if (existingPhone.isPresent() && !existingPhone.get().getPatientId().equals(id)) {
                logger.warn("Duplicate phone number detected during update: {}", updates.getPhoneNumber());
                throw new DuplicateResourceException("Phone number already exists: " + updates.getPhoneNumber());
            }
            patient.setPhoneNumber(updates.getPhoneNumber());
        }

        if (updates.getAddress() != null) {
            patient.setAddress(updates.getAddress());
        }

        if (updates.getEmail() != null) {
            patient.setEmail(updates.getEmail());
        }

        PatientEntity updatedPatient = dao.save(patient);
        logger.info("Patient updated successfully with ID: {}", updatedPatient.getPatientId());
        return updatedPatient;
    }

    
    public void delete(Integer id) {
        logger.info("Deleting patient with ID: {}", id);
        PatientEntity patient = getById(id);
        dao.delete(patient);
        logger.info("Patient deleted successfully with ID: {}", id);
    }
}




