package com.HAM_application.dto;

import java.util.List;

public class DoctorDTO {
    private Integer doctorId;
    private String doctorName;
    private String specialization;
    private Double consultationFee;
    private List<String> availableDays;   
    private List<String> availableSlots;  

    public DoctorDTO() {}

    public DoctorDTO(Integer doctorId, String doctorName, String specialization,
                     Double consultationFee, List<String> availableDays, List<String> availableSlots) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.availableDays = availableDays;
        this.availableSlots = availableSlots;
    }

   
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return specialization;
    }

    public void setDoctorSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }
}


