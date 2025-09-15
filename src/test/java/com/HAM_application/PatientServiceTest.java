package com.HAM_application;

import com.HAM_application.dao.PatientDao;
import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.exception.DuplicateResourceException;
import com.HAM_application.exception.PatientNotFoundException;
import com.HAM_application.service.PatientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock private PatientDao patientDao;
    @InjectMocks private PatientService patientService;

    private PatientEntity patient;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        patient = new PatientEntity();
        patient.setPatientId(1);
        patient.setPatientName("John Doe");
        patient.setPhoneNumber("1234567890");
        patient.setAadhaarNumber("AAD12345");
    }

    @Test
    void testRegisterHappyPath() {
        when(patientDao.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(patientDao.findByAadhaarNumber(anyString())).thenReturn(Optional.empty());
        when(patientDao.save(any())).thenReturn(patient);

        PatientEntity result = patientService.register(patient);
        assertNotNull(result);
        verify(patientDao).save(patient);
    }

    @Test
    void testRegisterDuplicatePhone() {
        when(patientDao.findByPhoneNumber(anyString())).thenReturn(Optional.of(patient));
        assertThrows(DuplicateResourceException.class, () -> patientService.register(patient));
    }

    @Test
    void testGetByIdNotFound() {
        when(patientDao.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class, () -> patientService.getById(1));
    }
}

