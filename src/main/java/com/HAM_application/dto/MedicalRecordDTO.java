package com.HAM_application.dto;

import java.time.LocalDateTime;

public class MedicalRecordDTO {
    private Integer recordId;
    private Integer patientId;
    private String patientName;
    private String doctorName;
    private String diagnosisDetails;
    private String treatmentDetails;       
    private Integer appointmentId;         
    private LocalDateTime recordTimestamp; 

    public MedicalRecordDTO(Integer integer, String string, LocalDateTime localDateTime) {}

    public MedicalRecordDTO(Integer recordId,Integer patientId, String patientName, String doctorName,
                            String diagnosisDetails, String treatmentDetails,
                            Integer appointmentId, LocalDateTime recordTimestamp) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.diagnosisDetails = diagnosisDetails;
        this.treatmentDetails = treatmentDetails;
        this.appointmentId = appointmentId;
        this.recordTimestamp = recordTimestamp;
    }


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosisDetails() {
        return diagnosisDetails;
    }

    public void setDiagnosisDetails(String diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(LocalDateTime recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

	public Integer getPatientId() {
		return patientId;
	}
	


	public void setPatientId(Integer patientId) {
        this.patientId = patientId;
}
}

