package com.HAM_application;

import com.HAM_application.dao.SlotDao;
import com.HAM_application.dao.entity.SlotEntity;
import com.HAM_application.service.SlotService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SlotServiceTest {

    @Mock
    private SlotDao slotDao;

    @InjectMocks
    private SlotService slotService;

    private SlotEntity slot;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        slot = new SlotEntity();
        slot.setSlotId(1);
        slot.setSlotTime("10:00 AM");
        slot.setSlotStatus("AVAILABLE");
    }

    @Test
    void testGetAllSlots() {
        when(slotDao.findAll()).thenReturn(List.of(slot));

        List<SlotEntity> result = slotService.getAllSlots();

        assertEquals(1, result.size());
        assertEquals("10:00 AM", result.get(0).getSlotTime());
        verify(slotDao, times(1)).findAll();
    }

    @Test
    void testGetAvailableSlotsByDayAndSpecialization() {
        when(slotDao.findAvailableSlotsByDayAndSpecialization("Monday", "Cardiologist"))
                .thenReturn(List.of(slot));

        List<SlotEntity> result = slotService.getAvailableSlotsByDayAndSpecialization("Monday", "Cardiologist");

        assertEquals(1, result.size());
        assertEquals("AVAILABLE", result.get(0).getSlotStatus());
        verify(slotDao, times(1)).findAvailableSlotsByDayAndSpecialization("Monday", "Cardiologist");
    }
}

