package com.HAM_application.mapper;

import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.dto.PatientDTO;

public class PatientMapper {

    public static PatientDTO toDTO(PatientEntity entity) {
        if (entity == null) return null;

        return new PatientDTO(
                entity.getPatientId(),
                entity.getPatientName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getEmail()
        );
    }

    public static PatientEntity toEntity(PatientDTO dto) {
        if (dto == null) return null;

        PatientEntity entity = new PatientEntity();
        entity.setPatientId(dto.getPatientId());
        entity.setPatientName(dto.getPatientName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}

