package com.HAM_application.controller;


import com.HAM_application.dao.entity.SlotEntity;
import com.HAM_application.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

   
    @GetMapping
    public ResponseEntity<List<SlotEntity>> getAllSlots() {
        return ResponseEntity.ok(slotService.getAllSlots());
    }

   
    @GetMapping("/available")
    public ResponseEntity<List<SlotEntity>> getAvailableSlotsByDayAndSpecialization(
            @RequestParam String dayName,
            @RequestParam String specialization) {
        return ResponseEntity.ok(slotService.getAvailableSlotsByDayAndSpecialization(dayName, specialization));
    }
}

