package com.HAM_application.mapper;

import com.HAM_application.dao.entity.MedicalRecordEntity;
import com.HAM_application.dto.MedicalRecordDTO;

public class MedicalRecordMapper {

    public static MedicalRecordDTO toDTO(MedicalRecordEntity entity) {
        if (entity == null) return null;
        return new MedicalRecordDTO(
        	    entity.getRecordId(),        	    
        	    entity.getDiagnosisDetails(),
        	    entity.getDiagnosisTimestamp()
        	);
    }

    public static MedicalRecordEntity toEntity(MedicalRecordDTO dto) {
        if (dto == null) return null;

        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setRecordId(dto.getRecordId());
        entity.setDiagnosisDetails(dto.getDiagnosisDetails());
        entity.setDiagnosisTimestamp(dto.getRecordTimestamp());

        
        return entity;
    }
}

