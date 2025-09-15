package com.HAM_application;

import com.HAM_application.dao.DoctorDayDao;
import com.HAM_application.dao.entity.DayEntity;
import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.service.DoctorDayService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorDayServiceTest {

    @Mock
    private DoctorDayDao doctorDayDao;

    @InjectMocks
    private DoctorDayService doctorDayService;

    @Test
    void assignDoctorToDay_ShouldSaveDoctorDay() {
        DoctorEntity doctor = mock(DoctorEntity.class);
        DayEntity day = mock(DayEntity.class);
        DoctorDayEntity doctorDay = new DoctorDayEntity(doctor, day);

        when(doctorDayDao.save(any(DoctorDayEntity.class))).thenReturn(doctorDay);

        DoctorDayEntity result = doctorDayService.assignDoctorToDay(doctor, day);

        assertNotNull(result);
        verify(doctorDayDao).save(any(DoctorDayEntity.class));
    }

    @Test
    void getDoctorDays_ShouldReturnList() {
        DoctorEntity doctor = mock(DoctorEntity.class);
        List<DoctorDayEntity> doctorDays = List.of(new DoctorDayEntity(doctor, mock(DayEntity.class)));
        when(doctorDayDao.findByDoctor(doctor)).thenReturn(doctorDays);

        List<DoctorDayEntity> result = doctorDayService.getDoctorDays(doctor);

        assertEquals(1, result.size());
        verify(doctorDayDao).findByDoctor(doctor);
    }

    @Test
    void getAvailableDaysBySpecialization_ShouldReturnList() {
 

        List<DayEntity> result = doctorDayService.getAvailableDaysBySpecialization("Cardiologist");

        assertEquals(1, result.size());
        verify(doctorDayDao).findAvailableDaysBySpecialization("Cardiologist");
    }
}

