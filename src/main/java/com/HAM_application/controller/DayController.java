package com.HAM_application.controller;


import com.HAM_application.dao.entity.DayEntity;
import com.HAM_application.service.DayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/days")
public class DayController {

    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

  
    @GetMapping
    public ResponseEntity<List<DayEntity>> getAllDays() {
        return ResponseEntity.ok(dayService.getAllDays());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DayEntity>> getAvailableDaysBySpecialization(
            @RequestParam String specialization) {
        return ResponseEntity.ok(dayService.getAvailableDaysBySpecialization(specialization));
    }
}

