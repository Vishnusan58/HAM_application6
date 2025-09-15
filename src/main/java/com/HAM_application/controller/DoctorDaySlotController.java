package com.HAM_application.controller;

import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorDaySlotEntity;
import com.HAM_application.dao.entity.SlotEntity;
import com.HAM_application.service.DoctorDaySlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctor-day-slots")
public class DoctorDaySlotController {

    private final DoctorDaySlotService service;

    public DoctorDaySlotController(DoctorDaySlotService service) {
        this.service = service;
    }

  
    @PostMapping("/assign")
    public ResponseEntity<DoctorDaySlotEntity> assignSlot(
            @RequestBody DoctorDaySlotEntity doctorDaySlot) {
        return ResponseEntity.ok(service.assignSlotToDoctorDay(
                doctorDaySlot.getDoctorDay(),
                doctorDaySlot.getSlot()
        ));
    }

  
    @GetMapping("/doctor-day/{docDayId}")
    public ResponseEntity<Optional<DoctorDaySlotEntity>> getSlotsByDoctorDay(@PathVariable Integer docDayId) {
        return ResponseEntity.ok(service.getSlotsByDoctorDay(docDayId));
    }

  
    @GetMapping("/available")
    public ResponseEntity<DoctorDaySlotEntity> getAvailableSlots(
            @RequestParam String dayName,
            @RequestParam String specialization) {
        return ResponseEntity.ok(service.getAvailableSlots(dayName, specialization));
    }

 
    @PutMapping("/{id}/status")
    public ResponseEntity<DoctorDaySlotEntity> updateSlotStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateSlotStatus(id, status));
    }
}

