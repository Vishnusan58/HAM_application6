package com.HAM_application.dto;

import java.time.LocalDateTime;

public class BillDTO {
    private Integer billId;
    private Integer appointmentId;
    private String patientName;
    private String doctorName;
    private String specialization;
    private Double consultationFee;
    private LocalDateTime billDate;

    public BillDTO() {}

    public BillDTO(Integer billId, Integer appointmentId, String patientName,
                   String doctorName, String specialization,
                   Double consultationFee, LocalDateTime billDate) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.billDate = billDate;
    }

    public Integer getBillId() { return billId; }
    public void setBillId(Integer billId) { this.billId = billId; }

    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public LocalDateTime getBillDate() { return billDate; }
    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }
}



