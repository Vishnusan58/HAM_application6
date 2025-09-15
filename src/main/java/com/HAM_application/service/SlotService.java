package com.HAM_application.service;

import com.HAM_application.dao.SlotDao;
import com.HAM_application.dao.entity.SlotEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlotService {

    private static final Logger logger = LoggerFactory.getLogger(SlotService.class);

    private final SlotDao slotDao;

    public SlotService(SlotDao slotDao) {
        this.slotDao = slotDao;
    }

    
    public List<SlotEntity> getAllSlots() {
        logger.info("Fetching all slots");
        List<SlotEntity> slots = slotDao.findAll();
        logger.info("Total slots found: {}", slots.size());
        return slots;
    }

    
    public List<SlotEntity> getAvailableSlotsByDayAndSpecialization(String dayName, String specialization) {
        logger.info("Fetching available slots for day: {} and specialization: {}", dayName, specialization);
        List<SlotEntity> slots = slotDao.findAvailableSlotsByDayAndSpecialization(dayName, specialization);
        logger.info("Available slots found: {}", slots.size());
        return slots;
    }
}


