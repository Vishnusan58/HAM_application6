package com.HAM_application.dao.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "NNA_BILL")
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;

    @Column(nullable = false)
    private BigDecimal billAmount;

    @Column(nullable = false)
    private LocalDateTime billDate = LocalDateTime.now();

    private String patientName;
    private String doctorName;
    private String specialization; // ✅ added
     
 
    public BillEntity() {}

    public BillEntity(AppointmentEntity appointment, BigDecimal billAmount, 
                      String patientName, String doctorName, String specialization) {
        this.appointment = appointment;
        this.billAmount = billAmount;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.specialization = specialization;
    }

 
    public Integer getBillId() {
        return billId;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientEntity) {
        this.patientName = patientEntity;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}


