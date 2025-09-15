package com.HAM_application;

import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.IllegalArgumentException;
import com.HAM_application.exception.SpecializationNotFoundException;
import com.HAM_application.service.SpecializationFeeService;

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
class SpecializationFeeServiceTest {

    @Mock
    private SpecializationFeeDao feeDao;

    @InjectMocks
    private SpecializationFeeService feeService;

    @Test
    void addFee_ShouldSaveFee() {
        SpecializationFeeEntity fee = new SpecializationFeeEntity("Cardiologist", new BigDecimal("1500"));

        when(feeDao.save(any(SpecializationFeeEntity.class))).thenReturn(fee);

        SpecializationFeeEntity result = feeService.addFee("Cardiologist", new BigDecimal("1500"));

        assertNotNull(result);
        assertEquals("Cardiologist", result.getSpecialization());
        assertEquals(new BigDecimal("1500"), result.getFee());
        verify(feeDao).save(any(SpecializationFeeEntity.class));
    }

    @Test
    void addFee_ShouldTrimInputsBeforeSaving() {
        SpecializationFeeEntity persisted = new SpecializationFeeEntity("Cardiologist", new BigDecimal("1500"));

        when(feeDao.save(any(SpecializationFeeEntity.class))).thenReturn(persisted);

        feeService.addFee("  Cardiologist  ", new BigDecimal("1500"));

        verify(feeDao).save(argThat(entity ->
                "Cardiologist".equals(entity.getSpecialization()) &&
                        new BigDecimal("1500").equals(entity.getFee())));
    }

    @Test
    void addFee_ShouldRejectInvalidInputs() {
        assertThrows(IllegalArgumentException.class,
                () -> feeService.addFee("   ", new BigDecimal("1500")));

        assertThrows(IllegalArgumentException.class,
                () -> feeService.addFee("Cardiologist", BigDecimal.ZERO));

        assertThrows(IllegalArgumentException.class,
                () -> feeService.addFee("Cardiologist", null));
    }

    @Test
    void updateFee_ShouldUpdateExistingFee() {
        SpecializationFeeEntity fee = new SpecializationFeeEntity("Dermatologist", new BigDecimal("1000"));

        when(feeDao.findBySpecialization("Dermatologist")).thenReturn(Optional.of(fee));
        when(feeDao.save(fee)).thenReturn(fee);

        SpecializationFeeEntity result = feeService.updateFee("Dermatologist", new BigDecimal("1200"));

        assertEquals(new BigDecimal("1200"), result.getFee());
        verify(feeDao).save(fee);
    }

    @Test
    void updateFee_ShouldThrowIfNotFound() {
        when(feeDao.findBySpecialization("ENT")).thenReturn(Optional.empty());

        assertThrows(SpecializationNotFoundException.class,
                () -> feeService.updateFee("ENT", new BigDecimal("1300")));
    }

    @Test
    void updateFee_ShouldRejectInvalidAmount() {
        SpecializationFeeEntity fee = new SpecializationFeeEntity("ENT", new BigDecimal("900"));
        when(feeDao.findBySpecialization("ENT")).thenReturn(Optional.of(fee));

        assertThrows(IllegalArgumentException.class,
                () -> feeService.updateFee("ENT", new BigDecimal("-10")));
    }

    @Test
    void getFeeBySpecialization_ShouldReturnFee() {
        SpecializationFeeEntity fee = new SpecializationFeeEntity("General", new BigDecimal("800"));
        when(feeDao.findBySpecialization("General")).thenReturn(Optional.of(fee));

        BigDecimal result = feeService.getFeeBySpecialization("General");

        assertEquals(new BigDecimal("800"), result);
    }

    @Test
    void getFeeBySpecialization_ShouldThrowIfNotFound() {
        when(feeDao.findBySpecialization("Neurologist")).thenReturn(Optional.empty());

        assertThrows(SpecializationNotFoundException.class,
                () -> feeService.getFeeBySpecialization("Neurologist"));
    }

    @Test
    void getFeeBySpecialization_ShouldRejectBlankInput() {
        assertThrows(IllegalArgumentException.class,
                () -> feeService.getFeeBySpecialization("   "));
    }

    @Test
    void getAllFees_ShouldReturnAllFees() {
        List<SpecializationFeeEntity> list = List.of(
                new SpecializationFeeEntity("Cardiologist", new BigDecimal("1500")),
                new SpecializationFeeEntity("Dermatologist", new BigDecimal("1000"))
        );
        when(feeDao.findAll()).thenReturn(list);

        List<SpecializationFeeEntity> result = feeService.getAllFees();

        assertEquals(2, result.size());
        verify(feeDao).findAll();
    }
}


