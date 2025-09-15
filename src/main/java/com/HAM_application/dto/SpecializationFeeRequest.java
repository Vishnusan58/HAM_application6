package com.HAM_application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SpecializationFeeRequest {

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotNull(message = "Fee is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than zero")
    private BigDecimal fee;

    public SpecializationFeeRequest() {
    }

    public SpecializationFeeRequest(String specialization, BigDecimal fee) {
        this.specialization = specialization;
        this.fee = fee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
