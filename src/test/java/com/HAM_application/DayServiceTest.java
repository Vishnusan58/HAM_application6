package com.HAM_application;

import com.HAM_application.dao.DayDao;
import com.HAM_application.dao.entity.DayEntity;
import com.HAM_application.service.DayService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DayServiceTest {

    @Mock
    private DayDao dayDao;

    @InjectMocks
    private DayService dayService;

    private DayEntity day;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        day = new DayEntity();
        day.setDayId(1);
        day.setDayName("Monday");
    }

    @Test
    void testGetAllDays() {
        when(dayDao.findAll()).thenReturn(List.of(day));

        List<DayEntity> result = dayService.getAllDays();

        assertEquals(1, result.size());
        assertEquals("Monday", result.get(0).getDayName());
        verify(dayDao, times(1)).findAll();
    }

    @Test
    void testGetAvailableDaysBySpecialization() {
        when(dayDao.findAvailableDaysBySpecialization("Cardiologist")).thenReturn(List.of(day));

        List<DayEntity> result = dayService.getAvailableDaysBySpecialization("Cardiologist");

        assertEquals(1, result.size());
        assertEquals("Monday", result.get(0).getDayName());
        verify(dayDao, times(1)).findAvailableDaysBySpecialization("Cardiologist");
    }
}

