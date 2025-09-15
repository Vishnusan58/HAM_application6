package com.HAM_application.controller;

import com.HAM_application.dao.entity.PatientEntity;
import com.HAM_application.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<PatientEntity> register(@Valid @RequestBody PatientEntity patient) {
        return ResponseEntity.ok(service.register(patient));
    }


    @GetMapping
    public ResponseEntity<List<PatientEntity>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> update(
            @PathVariable Integer id,
            @Valid @RequestBody PatientEntity updates) {
        return ResponseEntity.ok(service.update(id, updates));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}

