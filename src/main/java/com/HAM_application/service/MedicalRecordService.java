package com.HAM_application.service;

import com.HAM_application.dao.MedicalRecordDao;
import com.HAM_application.dao.PatientDao;
import com.HAM_application.dao.entity.MedicalRecordEntity;
import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.dto.MedicalRecordRequest;
import com.HAM_application.exception.MedicalRecordNotFoundException;
import com.HAM_application.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MedicalRecordService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

    private final MedicalRecordDao medicalRecordDao;
    private final PatientDao patientDao;

    public MedicalRecordService(MedicalRecordDao medicalRecordDao, PatientDao patientDao) {
        this.medicalRecordDao = medicalRecordDao;
        this.patientDao = patientDao;
    }

    
    public MedicalRecordEntity addMedicalRecord(MedicalRecordRequest request) {
        logger.info("Adding medical record for patient ID: {}", request.getPatientId());

        
        PatientEntity patient = patientDao.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + request.getPatientId()));

        
        MedicalRecordEntity record = new MedicalRecordEntity();
        record.setPatient(patient);
        record.setDiagnosisDetails(request.getDiagnosisDetails());
        record.setDiagnosisTimestamp(LocalDateTime.now());

        logger.info("Medical record created successfully for patient ID: {}", request.getPatientId());
        return medicalRecordDao.save(record);
    }

    
    public List<MedicalRecordEntity> getAllMedicalRecords() {
        logger.info("Fetching all medical records");
        return medicalRecordDao.findAll();
    }

    
    public List<MedicalRecordEntity> getRecordsByAppointment(Integer appointmentId) {
        logger.info("Fetching medical records for appointment ID: {}", appointmentId);
        return medicalRecordDao.findByAppointmentAppointmentId(appointmentId);
    }

    
    public List<MedicalRecordEntity> getRecordsByPatient(Integer patientId) {
        logger.info("Fetching medical records for patient ID: {}", patientId);
        if (!patientDao.existsById(patientId)) {
            logger.warn("Patient not found with ID: {}", patientId);
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        return medicalRecordDao.findByPatientPatientId(patientId);
    }

    
    public MedicalRecordEntity updateDiagnosis(Integer recordId, String diagnosisDetails) {
        logger.info("Updating diagnosis for medical record ID: {}", recordId);
        MedicalRecordEntity record = medicalRecordDao.findById(recordId)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical record not found with ID: " + recordId));

        record.setDiagnosisDetails(diagnosisDetails);
        logger.info("Diagnosis updated for medical record ID: {}", recordId);
        return medicalRecordDao.save(record);
    }
}




