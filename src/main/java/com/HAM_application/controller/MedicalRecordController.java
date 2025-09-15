package com.HAM_application.controller;

import com.HAM_application.dto.MedicalRecordDTO;
import com.HAM_application.dto.MedicalRecordRequest;
import com.HAM_application.dao.entity.MedicalRecordEntity;
import com.HAM_application.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }


    @PostMapping("/add")
    public ResponseEntity<MedicalRecordEntity> addMedicalRecord(
            @RequestBody MedicalRecordRequest request) {
        MedicalRecordEntity saved = service.addMedicalRecord(request);
        return ResponseEntity.ok(saved);
    }

   
    @GetMapping
    public ResponseEntity<List<MedicalRecordEntity>> getAllRecords() {
        return ResponseEntity.ok(service.getAllMedicalRecords());
    }

    
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<MedicalRecordEntity>> getRecordsByAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(service.getRecordsByAppointment(appointmentId));
    }

   
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordEntity>> getRecordsByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(service.getRecordsByPatient(patientId));
    }

    
    @PutMapping("/{recordId}")
    public ResponseEntity<MedicalRecordEntity> updateDiagnosis(
            @PathVariable Integer recordId,
            @RequestParam String diagnosisDetails) {
        return ResponseEntity.ok(service.updateDiagnosis(recordId, diagnosisDetails));
    }

   
}


