package com.HAM_application.dto;

import java.time.LocalDate;

public class AppointmentDTO {
    private Integer appointmentId;

    private Integer patientId;
    private String patientName;

    private Integer doctorId;
    private String doctorName;
    private String specialization;

    private Integer slotId;

    private LocalDate appointmentDate;
    private String status;


    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getDoctorSpecialization() { return specialization; }
    public void setDoctorSpecialization(String specialization) { this.specialization = specialization; }

    public Integer getSlotId() { return slotId; }
    public void setSlotId(Integer slotId) { this.slotId = slotId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
	@Override
	public String toString() {
		return "AppointmentDTO [appointmentId=" + appointmentId + ", patientId=" + patientId + ", patientName="
				+ patientName + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", specialization="
				+ specialization + ", slotId=" + slotId + ", appointmentDate=" + appointmentDate + ", status=" + status
				+ "]";
	}
}



