package com.HAM_application;

import com.HAM_application.dao.BillDao;
import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.AppointmentEntity;
import com.HAM_application.dao.entity.BillEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.SpecializationNotFoundException;
import com.HAM_application.service.BillService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillDao billDao;

    @Mock
    private SpecializationFeeDao specializationFeeDao;

    @InjectMocks
    private BillService billService;

    @Test
    void generateBill_ShouldCreateBill_WhenSpecializationExists() {
        // Arrange
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDoctorSpecialization("Cardiologist");

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentId(1);
        appointment.setDoctor(doctor);

        SpecializationFeeEntity feeEntity = new SpecializationFeeEntity("Cardiologist", BigDecimal.valueOf(500));
        when(specializationFeeDao.findBySpecialization("Cardiologist")).thenReturn(Optional.of(feeEntity));

        BillEntity savedBill = new BillEntity();
        savedBill.setBillAmount(BigDecimal.valueOf(500));
        when(billDao.save(any(BillEntity.class))).thenReturn(savedBill);

        // Act
        BillEntity result = billService.generateBill(appointment);

        // Assert
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(500), result.getBillAmount());
        verify(specializationFeeDao).findBySpecialization("Cardiologist");
        verify(billDao).save(any(BillEntity.class));
    }

    @Test
    void generateBill_ShouldThrowException_WhenSpecializationNotFound() {
        // Arrange
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDoctorSpecialization("Dermatologist");

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setDoctor(doctor);

        when(specializationFeeDao.findBySpecialization("Dermatologist")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SpecializationNotFoundException.class,
                () -> billService.generateBill(appointment));

        verify(specializationFeeDao).findBySpecialization("Dermatologist");
        verifyNoInteractions(billDao);
    }

    @Test
    void getBillByAppointment_ShouldReturnBills() {
        // Arrange
        List<BillEntity> bills = List.of(new BillEntity(), new BillEntity());
        when(billDao.findByAppointmentAppointmentId(1)).thenReturn(bills);

        // Act
        List<BillEntity> result = billService.getBillByAppointment(1);

        // Assert
        assertEquals(2, result.size());
        verify(billDao).findByAppointmentAppointmentId(1);
    }

    @Test
    void getBillByPatient_ShouldReturnBills() {
        // Arrange
        List<BillEntity> bills = List.of(new BillEntity(), new BillEntity());
        when(billDao.findByPatientId(1)).thenReturn(bills);

        // Act
        List<BillEntity> result = billService.getBillByPatient(1);

        // Assert
        assertEquals(2, result.size());
        verify(billDao).findByPatientId(1);
    }

    @Test
    void getAllBills_ShouldReturnAllBills() {
        // Arrange
        List<BillEntity> bills = List.of(new BillEntity(), new BillEntity(), new BillEntity());
        when(billDao.findAll()).thenReturn(bills);

        // Act
        List<BillEntity> result = billService.getAllBills();

        // Assert
        assertEquals(3, result.size());
        verify(billDao).findAll();
    }
}





