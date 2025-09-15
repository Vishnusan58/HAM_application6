package com.HAM_application.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NNA_DOCTOR_DAY")
public class DoctorDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docDayId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private DayEntity day;

  
    public DoctorDayEntity() {}
    public void setDocDayId(Integer docDayId) {
		this.docDayId = docDayId;
	}
	public DoctorDayEntity(DoctorEntity doctor, DayEntity day) {
        this.doctor = doctor;
        this.day = day;
    }


    public Integer getDocDayId() { return docDayId; }
    public DoctorEntity getDoctor() { return doctor; }
    public DayEntity getDay() { return day; }

    public void setDoctor(DoctorEntity doctor) { this.doctor = doctor; }
    public void setDay(DayEntity day) { this.day = day; }
	
}

