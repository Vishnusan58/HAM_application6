package com.HAM_application;

import com.HAM_application.dao.*;
import com.HAM_application.dao.entity.*;
import com.HAM_application.dto.*;
import com.HAM_application.exception.*;
import com.HAM_application.mapper.AppointmentMapper;
import com.HAM_application.service.AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock private AppointmentDao appointmentDao;
    @Mock private DoctorDao doctorDao;
    @Mock private DoctorDaySlotDao doctorDaySlotDao;
    @Mock private PatientDao patientDao;
    @Mock private AppointmentMapper mapper;

    @InjectMocks private AppointmentService appointmentService;

    private PatientEntity patient;
    private DoctorEntity doctor;
    private DoctorDaySlotEntity slot;
    private AppointmentEntity appointment;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        patient = new PatientEntity();
        patient.setPatientId(1);
        patient.setPatientName("Test Patient");
        patient.setPhoneNumber("1234567890");

        doctor = new DoctorEntity();
        doctor.setDoctorId(1);
        doctor.setDoctorName("Dr Test");
        doctor.setDoctorSpecialization("Cardiologist");

        SlotEntity slotEntity = new SlotEntity("10:00 AM");
        slotEntity.setSlotId(1);

        DoctorDayEntity doctorDay = new DoctorDayEntity();
        doctorDay.setDocDayId(1);

        slot = new DoctorDaySlotEntity(doctorDay, slotEntity);
        slot.setDoctorDaySlotId(1);
        slot.setSlotStatus("AVAILABLE");

        appointment = new AppointmentEntity();
        appointment.setAppointmentId(1);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDoctorDaySlot(slot);
        appointment.setAppointmentDate(LocalDate.now());
        appointment.setStatus("BOOKED");
    }

    @Test
    void testBookAppointmentHappyPath() {
        AppointmentBookingRequest request = new AppointmentBookingRequest();
        request.setPatientName("Test Patient");
        request.setPatientPhone("1234567890");
        request.setDoctorSpecialization("Cardiologist");
        request.setAppointmentDate(LocalDate.now());

        when(patientDao.findByPhoneNumber(anyString())).thenReturn(Optional.of(patient));
        when(doctorDao.findByDoctorSpecialization(anyString())).thenReturn(doctor);
        when(doctorDaySlotDao.findAvailableSlotsByDayAndSpecialization(anyString(), anyString()))
                .thenReturn(Optional.of(slot));
        when(appointmentDao.save(any())).thenReturn(appointment);
        when(mapper.toDTO(any())).thenReturn(new AppointmentDTO());

        AppointmentDTO result = appointmentService.bookAppointment(request);

        assertNotNull(result);
        assertEquals("BOOKED", slot.getSlotStatus()); // ✅ assert state instead of verify()
        verify(appointmentDao).save(any());
    }

    @Test
    void testBookAppointmentNoDoctor() {
        AppointmentBookingRequest request = new AppointmentBookingRequest();
        when(patientDao.findByPhoneNumber(anyString())).thenReturn(Optional.of(patient));
        when(doctorDao.findByDoctorSpecialization(anyString())).thenReturn(null);

        assertThrows(DoctorNotFoundException.class,
                () -> appointmentService.bookAppointment(request));
    }

    @Test
    void testBookAppointmentNoSlot() {
        AppointmentBookingRequest request = new AppointmentBookingRequest();
        when(patientDao.findByPhoneNumber(anyString())).thenReturn(Optional.of(patient));
        when(doctorDao.findByDoctorSpecialization(anyString())).thenReturn(doctor);
        when(doctorDaySlotDao.findAvailableSlotsByDayAndSpecialization(anyString(), anyString()))
                .thenReturn(Optional.empty());

        assertThrows(SlotNotFoundException.class,
                () -> appointmentService.bookAppointment(request));
    }

    @Test
    void testGetAppointmentByIdNotFound() {
        when(appointmentDao.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.getAppointmentById(1));
    }
}


