package com.HAM_application.mapper;

import org.springframework.stereotype.Component;
import com.HAM_application.dao.entity.*;
import com.HAM_application.dto.AppointmentDTO;

@Component
public class AppointmentMapper {

    public AppointmentDTO toDTO(AppointmentEntity entity) {
        if (entity == null) return null;

        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentId(entity.getAppointmentId());

        if (entity.getPatient() != null) {
            dto.setPatientId(entity.getPatient().getPatientId());
            dto.setPatientName(entity.getPatient().getPatientName()); // ✅ fixed
        }

        if (entity.getDoctor() != null) {
            dto.setDoctorId(entity.getDoctor().getDoctorId());
            dto.setDoctorName(entity.getDoctor().getDoctorName());
            dto.setDoctorSpecialization(entity.getDoctor().getDoctorSpecialization());
        }

        if (entity.getDoctorDaySlot() != null) {
            dto.setSlotId(entity.getDoctorDaySlot().getDoctorDaySlotId());
        }

        dto.setAppointmentDate(entity.getAppointmentDate());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public AppointmentEntity toEntity(AppointmentDTO dto) {
        if (dto == null) return null;

        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentId(dto.getAppointmentId());
        entity.setAppointmentDate(dto.getAppointmentDate());
        entity.setStatus(dto.getStatus());

        if (dto.getPatientId() != null || dto.getPatientName() != null) {
            PatientEntity patient = new PatientEntity();
            patient.setPatientId(dto.getPatientId());
            
            patient.setPatientName(dto.getPatientName());
            entity.setPatient(patient);
        }

        if (dto.getDoctorId() != null) {
            DoctorEntity doctor = new DoctorEntity();
            doctor.setDoctorId(dto.getDoctorId());
            doctor.setDoctorName(dto.getDoctorName());
            doctor.setDoctorSpecialization(dto.getDoctorSpecialization());
            entity.setDoctor(doctor);
        }

        if (dto.getSlotId() != null) {
            DoctorDaySlotEntity slot = new DoctorDaySlotEntity();
            slot.setDoctorDaySlotId(dto.getSlotId());
            entity.setDoctorDaySlot(slot);
        }

        return entity;
    }
}



