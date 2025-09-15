package com.HAM_application.dao.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NNA_MEDICAL_RECORDS")
public class MedicalRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)  // FK column
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)   // FK column
    private DoctorEntity doctor;

	@Column(columnDefinition = "TEXT")
    private String diagnosisDetails;

    @Column(nullable = false)
    private LocalDateTime diagnosisTimestamp = LocalDateTime.now();

   
    public MedicalRecordEntity() {}

    public MedicalRecordEntity(AppointmentEntity appointment, String diagnosisDetails) {
        this.appointment = appointment;
        this.diagnosisDetails = diagnosisDetails;
    }

 
    public Integer getRecordId() { return recordId; }
    public AppointmentEntity getAppointment() { return appointment; }
    public String getDiagnosisDetails() { return diagnosisDetails; }
    public LocalDateTime getDiagnosisTimestamp() { return diagnosisTimestamp; }
    public PatientEntity getPatient() {return patient;}
    public void setPatient(PatientEntity patient) {this.patient = patient;}
    public DoctorEntity getDoctor() {return doctor;}

	
    public void setDoctor(DoctorEntity doctor) {this.doctor = doctor;}  
    
    public void setRecordId(Integer recordId) { this.recordId = recordId; }
    public void setAppointment(AppointmentEntity appointment) { this.appointment = appointment; }
    public void setDiagnosisDetails(String diagnosisDetails) { this.diagnosisDetails = diagnosisDetails; }
    public void setDiagnosisTimestamp(LocalDateTime diagnosisTimestamp) { this.diagnosisTimestamp = diagnosisTimestamp; }

	
}

