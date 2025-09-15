package com.HAM_application.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NNA_SLOT")
public class SlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;

    @Column(nullable = false)
    private String slotTime;

    @Column(nullable = false)
    private String slotStatus = "AVAILABLE"; // default status

    public SlotEntity() {}

    public SlotEntity(String slotTime) {
        this.slotTime = slotTime;
        this.slotStatus = "AVAILABLE"; // default when new slot is created
    }

  
    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

   
    public void setSlotStatus(String slotStatus) {
        if (!slotStatus.equals("AVAILABLE") &&
            !slotStatus.equals("BOOKED") &&
            !slotStatus.equals("CANCELLED_BY_PATIENT") &&
            !slotStatus.equals("CANCELLED_BY_DOCTOR") &&
            !slotStatus.equals("CLOSED")) {
            throw new IllegalArgumentException("Invalid slot status: " + slotStatus);
        }
        this.slotStatus = slotStatus;
    }

    @Override
    public String toString() {
        return "SlotEntity{" +
                "slotId=" + slotId +
                ", slotTime='" + slotTime + '\'' +
                ", slotStatus='" + slotStatus + '\'' +
                '}';
    }
}


