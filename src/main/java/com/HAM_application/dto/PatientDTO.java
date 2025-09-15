package com.HAM_application.dto;

public class PatientDTO {
    private Integer patientId;
    private String patientName;
    private String phoneNumber;
    private String address;
    private String email;

    

    public PatientDTO() {}

   
    public PatientDTO(Integer patientId, String patientName, String phoneNumber, 
                      String address, String email) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    
    public PatientDTO(String phoneNumber, String address, String email) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


