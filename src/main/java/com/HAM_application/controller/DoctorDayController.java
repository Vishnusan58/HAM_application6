package com.HAM_application.controller;


import com.HAM_application.dao.entity.DayEntity;
import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.service.DoctorDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-days")
public class DoctorDayController {

    private final DoctorDayService service;

    public DoctorDayController(DoctorDayService service) {
        this.service = service;
    }

  
    @PostMapping("/assign")
    public ResponseEntity<DoctorDayEntity> assignDoctorToDay(
            @RequestBody DoctorDayEntity doctorDay) {
        return ResponseEntity.ok(service.assignDoctorToDay(
                doctorDay.getDoctor(), doctorDay.getDay()
        ));
    }

 
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorDayEntity>> getDoctorDays(
            @PathVariable Integer doctorId) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDoctorId(doctorId);
        return ResponseEntity.ok(service.getDoctorDays(doctor));
    }

 
    @GetMapping("/available")
    public ResponseEntity<List<DayEntity>> getAvailableDaysBySpecialization(
            @RequestParam String specialization) {
        return ResponseEntity.ok(service.getAvailableDaysBySpecialization(specialization));
    }
}

