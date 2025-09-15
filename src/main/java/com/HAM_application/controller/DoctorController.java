package com.HAM_application.controller;


import com.HAM_application.dao.entity.DoctorEntity;
import com.HAM_application.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    
    @PostMapping
    public ResponseEntity<DoctorEntity> createDoctor(@RequestBody DoctorEntity doctor) {
        return ResponseEntity.ok(service.create(doctor));
    }

    @GetMapping
    public ResponseEntity<List<DoctorEntity>> getAllDoctors() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorEntity> updateDoctor(@PathVariable Integer id, @RequestBody DoctorEntity updates) {
        return ResponseEntity.ok(service.update(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

  
    @GetMapping("/by-day-slot-specialization")
    public ResponseEntity<Optional<DoctorEntity>> getDoctorsByDaySlotSpecialization(
            @RequestParam String dayName,
            @RequestParam String slotTime,
            @RequestParam(required = false) String specialization) {
        return ResponseEntity.ok(service.getDoctorsByDaySlotSpecialization(dayName, slotTime, specialization));
    }

    @GetMapping("/{id}/fee")
    public ResponseEntity<BigDecimal> getFeeByDoctorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getFeeByDoctorId(id));
    }

    @GetMapping("/name/{name}/fee")
    public ResponseEntity<BigDecimal> getFeeByDoctorName(@PathVariable String name) {
        return ResponseEntity.ok(service.getFeeByDoctorName(name));
    }
}

