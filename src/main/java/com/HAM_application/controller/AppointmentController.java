package com.HAM_application.controller;

import com.HAM_application.dto.AppointmentDTO;
import com.HAM_application.dto.AppointmentBookingRequest;
import com.HAM_application.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody AppointmentBookingRequest request) {
        AppointmentDTO saved = service.bookAppointment(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getAppointmentById(id));
    }

    @DeleteMapping("/cancel/patient/{id}")
    public ResponseEntity<AppointmentDTO> cancelByPatient(@PathVariable Integer id) {
        return ResponseEntity.ok(service.cancelByPatient(id));
    }

    @PostMapping("/cancel/doctor/{id}")
    public ResponseEntity<AppointmentDTO> cancelByDoctor(@PathVariable Integer id) {
        return ResponseEntity.ok(service.cancelByDoctor(id));
    }

    @PostMapping("/switch/{id}")
    public ResponseEntity<AppointmentDTO> switchSlot(@PathVariable Integer id,
                                                     @RequestParam Integer newSlotId,
                                                     @RequestParam String newDate) {
        LocalDate date = LocalDate.parse(newDate);
        return ResponseEntity.ok(service.switchSlot(id, newSlotId, date));
    }

    @PostMapping("/close/{id}")
    public ResponseEntity<AppointmentDTO> closeAppointment(@PathVariable Integer id) {
        return ResponseEntity.ok(service.closeAppointment(id));
    }
}




