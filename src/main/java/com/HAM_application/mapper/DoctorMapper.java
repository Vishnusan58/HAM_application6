package com.HAM_application.mapper;

import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.dto.DoctorDTO;

public class DoctorMapper {

    public static DoctorDTO toDTO(DoctorEntity entity) {
        if (entity == null) return null;

        return new DoctorDTO(
                entity.getDoctorId(),
                entity.getDoctorName(),
                entity.getDoctorSpecialization(),
                entity.getConsultationFee(), null, null
        );
    }

    public static DoctorEntity toEntity(DoctorDTO dto) {
        if (dto == null) return null;

        DoctorEntity entity = new DoctorEntity();
        entity.setDoctorId(dto.getDoctorId());
        entity.setDoctorName(dto.getDoctorName());
        entity.setDoctorSpecialization(dto.getDoctorSpecialization());
        entity.setConsultationFee(dto.getConsultationFee());
        return entity;
    }
}

