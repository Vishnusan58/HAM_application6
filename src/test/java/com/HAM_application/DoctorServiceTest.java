package com.HAM_application;

import com.HAM_application.dao.DoctorDao;
import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.DoctorNotFoundException;
import com.HAM_application.exception.SpecializationNotFoundException;
import com.HAM_application.service.DoctorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @Mock private DoctorDao doctorDao;
    @Mock private SpecializationFeeDao feeDao;
    @InjectMocks private DoctorService doctorService;

    private DoctorEntity doctor;
    private SpecializationFeeEntity fee;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        doctor = new DoctorEntity();
        doctor.setDoctorId(1);
        doctor.setDoctorName("Dr Test");
        doctor.setDoctorSpecialization("Cardiologist");

        fee = new SpecializationFeeEntity("Cardiologist", new BigDecimal("1500"));
    }

    @Test
    void testGetFeeByDoctorIdHappyPath() {
        when(doctorDao.findById(anyInt())).thenReturn(Optional.of(doctor));
        when(feeDao.findBySpecialization(anyString())).thenReturn(Optional.of(fee));

        BigDecimal result = doctorService.getFeeByDoctorId(1);
        assertEquals(new BigDecimal("1500"), result);
    }

    @Test
    void testGetFeeByDoctorIdDoctorNotFound() {
        when(doctorDao.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class, () -> doctorService.getFeeByDoctorId(1));
    }

    @Test
    void testGetFeeByDoctorIdFeeNotFound() {
        when(doctorDao.findById(anyInt())).thenReturn(Optional.of(doctor));
        when(feeDao.findBySpecialization(anyString())).thenReturn(Optional.empty());
        assertThrows(SpecializationNotFoundException.class, () -> doctorService.getFeeByDoctorId(1));
    }
}
