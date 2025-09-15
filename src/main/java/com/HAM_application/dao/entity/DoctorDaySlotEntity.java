package com.HAM_application.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NNA_DOCTOR_DAY_SLOT")
public class DoctorDaySlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorDaySlotId;

    @ManyToOne
    @JoinColumn(name = "doc_day_id", nullable = false)
    private DoctorDayEntity doctorDay;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private SlotEntity slot;

    @Column(nullable = false)
    private String slotStatus = "AVAILABLE"; // default value

  
    public DoctorDaySlotEntity() {}
    public DoctorDaySlotEntity(DoctorDayEntity doctorDay, SlotEntity slot) {
        this.doctorDay = doctorDay;
        this.slot = slot;
    }

   
    public Integer getDoctorDaySlotId() { return doctorDaySlotId; }
    public DoctorDayEntity getDoctorDay() { return doctorDay; }
    public SlotEntity getSlot() { return slot; }
    public String getSlotStatus() { return slotStatus; }

    
    public void setDoctorDaySlotId(Integer doctorDaySlotId) {
		this.doctorDaySlotId = doctorDaySlotId;}
    public void setDoctorDay(DoctorDayEntity doctorDay) { this.doctorDay = doctorDay; }
    public void setSlot(SlotEntity slot) { this.slot = slot; }
    public void setSlotStatus(String slotStatus) { this.slotStatus = slotStatus; }
}

