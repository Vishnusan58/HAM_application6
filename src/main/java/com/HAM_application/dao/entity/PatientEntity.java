package com.HAM_application.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "NNA_PATIENT")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @NotBlank(message = "Patient name is mandatory")
    @Column(nullable = false)
    private String patientName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(length = 10)
    private String phoneNumber;

    private String address;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar number must be 12 digits")
    @Column(unique = true, length = 12)
    private String aadhaarNumber;


    public PatientEntity() {}
    public PatientEntity(String patientName, String phoneNumber, String address, String email, String aadhaarNumber) {
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.aadhaarNumber = aadhaarNumber;
    }


    public Integer getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getAadhaarNumber() { return aadhaarNumber; }

    public Integer setPatientId(Integer PatientId) { return patientId; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }

public void setPatientName(String patientName) { 
    this.patientName = patientName; 
}

public void setAadhaarNumber(String aadhaarNumber) { 
    this.aadhaarNumber = aadhaarNumber; 
}


}

