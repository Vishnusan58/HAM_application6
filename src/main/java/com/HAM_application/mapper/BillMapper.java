package com.HAM_application.mapper;

import com.HAM_application.dao.entity.BillEntity;
import com.HAM_application.dto.BillDTO;

public class BillMapper {

    public static BillDTO toDTO(BillEntity entity) {
        if (entity == null) return null;

        return new BillDTO(
                entity.getBillId(),
                entity.getAppointment() != null ? entity.getAppointment().getAppointmentId() : null,
                entity.getPatientName(),
                entity.getDoctorName(),
                entity.getSpecialization(),
                entity.getBillAmount() != null ? entity.getBillAmount().doubleValue() : null,
                entity.getBillDate()
        );
    }

    public static BillEntity toEntity(BillDTO dto) {
        if (dto == null) return null;

        BillEntity entity = new BillEntity();
        
        entity.setBillAmount(dto.getConsultationFee() != null 
                ? java.math.BigDecimal.valueOf(dto.getConsultationFee()) 
                : null);
        entity.setBillDate(dto.getBillDate());
        entity.setPatientName(dto.getPatientName());
        entity.setDoctorName(dto.getDoctorName());
        entity.setSpecialization(dto.getSpecialization());
       
        return entity;
    }
}


