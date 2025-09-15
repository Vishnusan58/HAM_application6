package com.HAM_application.service;

import com.HAM_application.dao.AppointmentDao;
import com.HAM_application.dao.DoctorDao;
import com.HAM_application.dao.DoctorDaySlotDao;
import com.HAM_application.dao.PatientDao;
import com.HAM_application.dao.entity.AppointmentEntity;
import com.HAM_application.dao.entity.DoctorDaySlotEntity;
import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.dto.AppointmentDTO;
import com.HAM_application.dto.AppointmentBookingRequest;
import com.HAM_application.exception.*;
import com.HAM_application.mapper.AppointmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentDao appointmentDao;
    private final AppointmentMapper mapper;
    private final DoctorDao doctorDao;
    private final DoctorDaySlotDao doctorDaySlotDao;
    private final PatientDao patientDao;

    public AppointmentService(AppointmentDao appointmentDao, AppointmentMapper mapper,
                              DoctorDao doctorDao, DoctorDaySlotDao doctorDaySlotDao,
                              PatientDao patientDao) {
        this.appointmentDao = appointmentDao;
        this.mapper = mapper;
        this.doctorDao = doctorDao;
        this.doctorDaySlotDao = doctorDaySlotDao;
        this.patientDao = patientDao;
    }

    
    public AppointmentDTO bookAppointment(AppointmentBookingRequest request) {
        logger.info("Booking appointment for patient: {} with specialization: {} on {}",
                request.getPatientName(), request.getDoctorSpecialization(), request.getAppointmentDate());

        PatientEntity patient = patientDao.findByPhoneNumber(request.getPatientPhone())
                .orElseGet(() -> {
                    logger.info("Creating new patient: {}", request.getPatientName());
                    PatientEntity newPatient = new PatientEntity();
                    newPatient.setPatientName(request.getPatientName());
                    newPatient.setPhoneNumber(request.getPatientPhone());
                    return patientDao.save(newPatient);
                });

        DoctorEntity doctor = doctorDao.findByDoctorSpecialization(request.getDoctorSpecialization());
        if (doctor == null) {
            logger.error("Doctor not found for specialization: {}", request.getDoctorSpecialization());
            throw new DoctorNotFoundException("No doctor available for specialization: " + request.getDoctorSpecialization());
        }

        DoctorDaySlotEntity slot = doctorDaySlotDao
                .findAvailableSlotsByDayAndSpecialization(
                        request.getAppointmentDate().getDayOfWeek().name(),
                        request.getDoctorSpecialization()
                )
                .orElseThrow(() -> {
                    logger.error("No available slot for doctor on {}", request.getAppointmentDate());
                    return new SlotNotFoundException("No available slot for doctor on " + request.getAppointmentDate());
                });

        slot.setSlotStatus("BOOKED");

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDoctorDaySlot(slot);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("BOOKED");

        AppointmentEntity saved = appointmentDao.save(appointment);
        logger.info("Appointment booked successfully with ID: {}", saved.getAppointmentId());
        return mapper.toDTO(saved);
    }

    public List<AppointmentDTO> getAllAppointments() {
        logger.info("Fetching all appointments");
        return appointmentDao.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }


    public AppointmentDTO getAppointmentById(Integer id) {
        logger.info("Fetching appointment by ID: {}", id);
        AppointmentEntity appointment = appointmentDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });
        return mapper.toDTO(appointment);
    }

  
    public AppointmentDTO cancelByPatient(Integer id) {
        logger.info("Cancelling appointment by patient, ID: {}", id);
        AppointmentEntity appointment = appointmentDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });

        appointment.setStatus("CANCELLED");
        appointment.getDoctorDaySlot().setSlotStatus("AVAILABLE");

        AppointmentEntity saved = appointmentDao.save(appointment);
        logger.info("Appointment cancelled successfully, ID: {}", saved.getAppointmentId());
        return mapper.toDTO(saved);
    }


    public AppointmentDTO cancelByDoctor(Integer id) {
        logger.info("Cancelling appointment by doctor, ID: {}", id);
        AppointmentEntity appointment = appointmentDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });

        appointment.setStatus("WAITING");
        appointment.getDoctorDaySlot().setSlotStatus("DOCTOR_CANCELLED");

        AppointmentEntity saved = appointmentDao.save(appointment);
        logger.info("Appointment cancelled by doctor successfully, ID: {}", saved.getAppointmentId());
        return mapper.toDTO(saved);
    }

  
    public AppointmentDTO switchSlot(Integer appointmentId, Integer newSlotId, LocalDate newDate) {
        logger.info("Switching slot for appointment ID: {} to new slot ID: {} on date: {}", appointmentId, newSlotId, newDate);

        AppointmentEntity appointment = appointmentDao.findById(appointmentId)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", appointmentId);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId);
                });

        appointment.getDoctorDaySlot().setSlotStatus("AVAILABLE");

        DoctorDaySlotEntity newSlot = doctorDaySlotDao.findById(newSlotId)
                .orElseThrow(() -> {
                    logger.error("New slot not found with ID: {}", newSlotId);
                    return new SlotNotFoundException("New slot not found with ID: " + newSlotId);
                });

        newSlot.setSlotStatus("BOOKED");
        appointment.setDoctorDaySlot(newSlot);
        appointment.setAppointmentDate(newDate);
        appointment.setStatus("BOOKED");

        AppointmentEntity saved = appointmentDao.save(appointment);
        logger.info("Appointment slot switched successfully, appointment ID: {}", saved.getAppointmentId());
        return mapper.toDTO(saved);
    }

  
    public AppointmentDTO closeAppointment(Integer id) {
        logger.info("Closing appointment ID: {}", id);
        AppointmentEntity appointment = appointmentDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });

        appointment.setStatus("CLOSED");

        AppointmentEntity saved = appointmentDao.save(appointment);
        logger.info("Appointment closed successfully, ID: {}", saved.getAppointmentId());
        return mapper.toDTO(saved);
    }

    @Override
    public String toString() {
        return "AppointmentService [appointmentDao=" + appointmentDao +
                ", mapper=" + mapper +
                ", doctorDaySlotDao=" + doctorDaySlotDao + "]";
    }
}

