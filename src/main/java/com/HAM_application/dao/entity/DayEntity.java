package com.HAM_application.dao.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "NNA_DAY")
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayId;

    @Column(unique = true, nullable = false)
    private String dayName;

    // Constructors
    public DayEntity() {}
    public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	public DayEntity(String dayName) {
        this.dayName = dayName;
    }

   
    public Integer getDayId() { return dayId; }
    public String getDayName() { return dayName; }
    public void setDayName(String dayName) { this.dayName = dayName; }
	
		
	
}

