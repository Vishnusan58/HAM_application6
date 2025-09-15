package com.HAM_application.dao.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

	@Entity
	@Table(name = "NNA_DOCTOR")
	public class DoctorEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer doctorId;

	    @NotBlank(message = "Doctor name is mandatory")
	    @Column(nullable = false)
	    private String doctorName;

	    @NotBlank(message = "Specialization is mandatory")
	    @Column(nullable = false)
	    private String doctorSpecialization;
	    
	    @Column(nullable = false)
	    private Double consultationFee;

	 
	    public DoctorEntity() {}
	    public DoctorEntity(String doctorName, String doctorSpecialization, Double consultationFee) {
	        this.doctorName = doctorName;
	        this.doctorSpecialization = doctorSpecialization;
	        this.consultationFee = consultationFee;
	    }

	 
	    public Integer getDoctorId() { return doctorId; }
	    public void setDoctorId(Integer doctorId) {
			this.doctorId = doctorId;
		}
		public String getDoctorName() { return doctorName; }
	    public String getDoctorSpecialization() { return doctorSpecialization; }

	    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
	    public void setDoctorSpecialization(String doctorSpecialization) { this.doctorSpecialization = doctorSpecialization; }
		public Double getConsultationFee() { return consultationFee;	}
		public void setConsultationFee(Double consultationFee) {
			this.consultationFee = consultationFee;
		}
	}



