package com.HAM_application.controller;


import com.HAM_application.dao.entity.AppointmentEntity;
import com.HAM_application.dao.entity.BillEntity;
import com.HAM_application.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }


    @PostMapping("/generate")
    public ResponseEntity<BillEntity> generateBill(@RequestBody AppointmentEntity appointment) {
        return ResponseEntity.ok(service.generateBill(appointment));
    }

  
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<BillEntity>> getBillByAppointment(@PathVariable Integer appointmentId) {
        return ResponseEntity.ok(service.getBillByAppointment(appointmentId));
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BillEntity>> getBillByPatient(@PathVariable Integer patientId) {
        return ResponseEntity.ok(service.getBillByPatient(patientId));
    }

   
    @GetMapping
    public ResponseEntity<List<BillEntity>> getAllBills() {
        return ResponseEntity.ok(service.getAllBills());
    }
}

