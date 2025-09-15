package com.HAM_application.dao.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "NNA_APPOINTMENT",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"doctor_day_slot_id", "appointment_date"})
    }
)
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer appointmentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_day_slot_id", nullable = false)
    private DoctorDaySlotEntity doctorDaySlot;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false, length = 20)
    private String status = "BOOKED"; // BOOKED, CANCELLED, WAITING, CLOSED

 
    public AppointmentEntity() {}

    public AppointmentEntity(PatientEntity patient, DoctorEntity doctor,
                             DoctorDaySlotEntity doctorDaySlot, LocalDate appointmentDate, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.doctorDaySlot = doctorDaySlot;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }


    public Integer getAppointmentId() {
        return appointmentId;
    }
    
    public void setAppointmentId(Integer appointmentId) { 
        this.appointmentId = appointmentId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public DoctorDaySlotEntity getDoctorDaySlot() {
        return doctorDaySlot;
    }

    public void setDoctorDaySlot(DoctorDaySlotEntity slot) {
        this.doctorDaySlot = slot;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 
    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "appointmentId=" + appointmentId +
                ", appointmentDate=" + appointmentDate +
                ", status='" + status + '\'' +
                '}';
    }
}



