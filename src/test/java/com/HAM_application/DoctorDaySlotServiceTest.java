package com.HAM_application;

import com.HAM_application.dao.DoctorDaySlotDao;
import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorDaySlotEntity;
import com.HAM_application.dao.entity.SlotEntity;
import com.HAM_application.exception.SlotNotFoundException;
import com.HAM_application.service.DoctorDaySlotService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorDaySlotServiceTest {

    @Mock
    private DoctorDaySlotDao doctorDaySlotDao;

    @InjectMocks
    private DoctorDaySlotService doctorDaySlotService;

    @Test
    void assignSlotToDoctorDay_ShouldSaveSlot() {
        DoctorDayEntity doctorDay = mock(DoctorDayEntity.class);
        SlotEntity slot = mock(SlotEntity.class);
        DoctorDaySlotEntity doctorDaySlot = new DoctorDaySlotEntity(doctorDay, slot);

        when(doctorDaySlotDao.save(any(DoctorDaySlotEntity.class))).thenReturn(doctorDaySlot);

        DoctorDaySlotEntity result = doctorDaySlotService.assignSlotToDoctorDay(doctorDay, slot);

        assertNotNull(result);
        verify(doctorDaySlotDao).save(any(DoctorDaySlotEntity.class));
    }

    @Test
    void getAvailableSlots_ShouldReturnSlot() {
        DoctorDaySlotEntity slot = mock(DoctorDaySlotEntity.class);
        when(doctorDaySlotDao.findAvailableSlotsByDayAndSpecialization("MONDAY", "Cardiologist"))
                .thenReturn(Optional.of(slot));

        DoctorDaySlotEntity result = doctorDaySlotService.getAvailableSlots("MONDAY", "Cardiologist");

        assertNotNull(result);
        verify(doctorDaySlotDao).findAvailableSlotsByDayAndSpecialization("MONDAY", "Cardiologist");
    }

    @Test
    void getAvailableSlots_ShouldThrowExceptionWhenNotFound() {
        when(doctorDaySlotDao.findAvailableSlotsByDayAndSpecialization("MONDAY", "Cardiologist"))
                .thenReturn(Optional.empty());

        assertThrows(SlotNotFoundException.class,
                () -> doctorDaySlotService.getAvailableSlots("MONDAY", "Cardiologist"));
    }

    @Test
    void updateSlotStatus_ShouldUpdateSlot() {
        DoctorDaySlotEntity slot = mock(DoctorDaySlotEntity.class);
        when(doctorDaySlotDao.findById(1)).thenReturn(Optional.of(slot));
        when(doctorDaySlotDao.save(slot)).thenReturn(slot);

        DoctorDaySlotEntity result = doctorDaySlotService.updateSlotStatus(1, "BOOKED");

        assertNotNull(result);
        verify(slot).setSlotStatus("BOOKED");
        verify(doctorDaySlotDao).save(slot);
    }

    @Test
    void updateSlotStatus_ShouldThrowExceptionWhenNotFound() {
        when(doctorDaySlotDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(SlotNotFoundException.class,
                () -> doctorDaySlotService.updateSlotStatus(1, "BOOKED"));
    }
}

