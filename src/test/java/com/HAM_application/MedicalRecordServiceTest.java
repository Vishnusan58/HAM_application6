package com.HAM_application;

import com.HAM_application.dao.MedicalRecordDao;
import com.HAM_application.dao.PatientDao;
import com.HAM_application.dao.entity.MedicalRecordEntity;
import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.dto.MedicalRecordRequest;
import com.HAM_application.exception.MedicalRecordNotFoundException;
import com.HAM_application.exception.PatientNotFoundException;
import com.HAM_application.service.MedicalRecordService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordDao medicalRecordDao;

    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Test
    void addMedicalRecord_ShouldSaveRecord() {
        PatientEntity patient = new PatientEntity();
        patient.setPatientId(1);

        MedicalRecordRequest request = new MedicalRecordRequest();
        request.setPatientId(1);
        request.setDiagnosisDetails("Test diagnosis");

        when(patientDao.findById(1)).thenReturn(Optional.of(patient));

        MedicalRecordEntity savedRecord = new MedicalRecordEntity();
        savedRecord.setPatient(patient);
        savedRecord.setDiagnosisDetails("Test diagnosis");
        savedRecord.setDiagnosisTimestamp(LocalDateTime.now());

        when(medicalRecordDao.save(any(MedicalRecordEntity.class))).thenReturn(savedRecord);

        MedicalRecordEntity result = medicalRecordService.addMedicalRecord(request);

        assertNotNull(result);
        assertEquals("Test diagnosis", result.getDiagnosisDetails());
        verify(medicalRecordDao).save(any(MedicalRecordEntity.class));
    }

    @Test
    void addMedicalRecord_ShouldThrowPatientNotFound() {
        MedicalRecordRequest request = new MedicalRecordRequest();
        request.setPatientId(1);
        request.setDiagnosisDetails("Test");

        when(patientDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class,
                () -> medicalRecordService.addMedicalRecord(request));
    }

    @Test
    void getRecordsByPatient_ShouldReturnRecords() {
        when(patientDao.existsById(1)).thenReturn(true);
        when(medicalRecordDao.findByPatientPatientId(1)).thenReturn(List.of(new MedicalRecordEntity()));

        List<MedicalRecordEntity> result = medicalRecordService.getRecordsByPatient(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(medicalRecordDao).findByPatientPatientId(1);
    }

    @Test
    void getRecordsByPatient_ShouldThrowPatientNotFound() {
        when(patientDao.existsById(1)).thenReturn(false);

        assertThrows(PatientNotFoundException.class,
                () -> medicalRecordService.getRecordsByPatient(1));
    }

    @Test
    void updateDiagnosis_ShouldUpdateRecord() {
        MedicalRecordEntity record = new MedicalRecordEntity();
        record.setDiagnosisDetails("Old diagnosis");

        when(medicalRecordDao.findById(1)).thenReturn(Optional.of(record));
        when(medicalRecordDao.save(record)).thenReturn(record);

        MedicalRecordEntity result = medicalRecordService.updateDiagnosis(1, "New diagnosis");

        assertEquals("New diagnosis", result.getDiagnosisDetails());
        verify(medicalRecordDao).save(record);
    }

    @Test
    void updateDiagnosis_ShouldThrowMedicalRecordNotFound() {
        when(medicalRecordDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(MedicalRecordNotFoundException.class,
                () -> medicalRecordService.updateDiagnosis(1, "New"));
    }
}


