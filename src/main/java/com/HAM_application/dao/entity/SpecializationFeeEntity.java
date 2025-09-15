package com.HAM_application.dao.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "NNA_SPECIALIZATION_FEES")
public class SpecializationFeeEntity {

    @Id
    @Column(name = "SPECIALIZATION")
    private String specialization;

    @Column(name = "FEE")
    private BigDecimal fee;


    public SpecializationFeeEntity() {}

    public SpecializationFeeEntity(String specialization, BigDecimal fee) {
        this.specialization = specialization;
        this.fee = fee;
    }

  
   
    public String getSpecialization() { return specialization; }
    public BigDecimal getFee() { return fee; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setFee(BigDecimal fee) { this.fee = fee; }
}

