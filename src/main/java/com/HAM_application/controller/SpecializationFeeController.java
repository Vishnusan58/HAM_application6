package com.HAM_application.controller;


import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.service.SpecializationFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/specialization-fees")
public class SpecializationFeeController {

    private final SpecializationFeeService service;

    public SpecializationFeeController(SpecializationFeeService service) {
        this.service = service;
    }

   
    @PostMapping("/add")
    public ResponseEntity<SpecializationFeeEntity> addFee(
            @RequestParam String specialization,
            @RequestParam BigDecimal fee) {
        return ResponseEntity.ok(service.addFee(specialization, fee));
    }

  
    @PutMapping("/update")
    public ResponseEntity<SpecializationFeeEntity> updateFee(
            @RequestParam String specialization,
            @RequestParam BigDecimal fee) {
        return ResponseEntity.ok(service.updateFee(specialization, fee));
    }


    @GetMapping("/{specialization}")
    public ResponseEntity<BigDecimal> getFee(@PathVariable String specialization) {
        return ResponseEntity.ok(service.getFeeBySpecialization(specialization));
    }

    @GetMapping
    public ResponseEntity<List<SpecializationFeeEntity>> getAllFees() {
        return ResponseEntity.ok(service.getAllFees());
    }
}

