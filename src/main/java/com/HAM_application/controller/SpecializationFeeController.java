package com.HAM_application.controller;


import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.dto.SpecializationFeeRequest;
import com.HAM_application.exception.IllegalArgumentException;
import com.HAM_application.service.SpecializationFeeService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody(required = false) SpecializationFeeRequest request,
            @RequestParam(value = "specialization", required = false) String specialization,
            @RequestParam(value = "fee", required = false) BigDecimal fee) {
        SpecializationFeeRequest payload = resolveRequestPayload(request, specialization, fee);
        return ResponseEntity.ok(service.addFee(payload.getSpecialization(), payload.getFee()));
    }


    @PutMapping("/update")
    public ResponseEntity<SpecializationFeeEntity> updateFee(
            @Valid @RequestBody(required = false) SpecializationFeeRequest request,
            @RequestParam(value = "specialization", required = false) String specialization,
            @RequestParam(value = "fee", required = false) BigDecimal fee) {
        SpecializationFeeRequest payload = resolveRequestPayload(request, specialization, fee);
        return ResponseEntity.ok(service.updateFee(payload.getSpecialization(), payload.getFee()));
    }


    @GetMapping("/{specialization}")
    public ResponseEntity<BigDecimal> getFee(@PathVariable String specialization) {
        return ResponseEntity.ok(service.getFeeBySpecialization(specialization));
    }

    @GetMapping
    public ResponseEntity<List<SpecializationFeeEntity>> getAllFees() {
        return ResponseEntity.ok(service.getAllFees());
    }

    private SpecializationFeeRequest resolveRequestPayload(SpecializationFeeRequest body,
                                                           String specialization,
                                                           BigDecimal fee) {
        if (body != null) {
            return body;
        }

        if (specialization != null && fee != null) {
            return new SpecializationFeeRequest(specialization, fee);
        }

        throw new IllegalArgumentException("Specialization and fee must be provided");
    }
}

