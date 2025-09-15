package com.HAM_application.service;

import com.HAM_application.dao.DoctorDaySlotDao;
import com.HAM_application.dao.entity.DoctorDayEntity;
import com.HAM_application.dao.entity.DoctorDaySlotEntity;
import com.HAM_application.dao.entity.SlotEntity;
import com.HAM_application.exception.SlotNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DoctorDaySlotService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorDaySlotService.class);

    private final DoctorDaySlotDao doctorDaySlotDao;

    public DoctorDaySlotService(DoctorDaySlotDao doctorDaySlotDao) {
        this.doctorDaySlotDao = doctorDaySlotDao;
    }

    
    public DoctorDaySlotEntity assignSlotToDoctorDay(DoctorDayEntity doctorDay, SlotEntity slot) {
        logger.info("Assigning slot ID {} to doctor-day ID {}", slot.getSlotId(), doctorDay.getDocDayId());
        DoctorDaySlotEntity doctorDaySlot = new DoctorDaySlotEntity(doctorDay, slot);
        return doctorDaySlotDao.save(doctorDaySlot);
    }

    
    public Optional<DoctorDaySlotEntity> getSlotsByDoctorDay(Integer docDayId) {
        logger.info("Fetching slots for doctor-day ID {}", docDayId);
        return doctorDaySlotDao.findByDoctorDayDocDayId(docDayId);
    }

    
    public DoctorDaySlotEntity getAvailableSlots(String dayName, String specialization) {
        logger.info("Fetching available slot for specialization {} on {}", specialization, dayName);
        return doctorDaySlotDao.findAvailableSlotsByDayAndSpecialization(dayName, specialization)
                .orElseThrow(() -> new SlotNotFoundException(
                        "No available slot found for specialization: " + specialization + " on " + dayName));
    }

    
    public DoctorDaySlotEntity updateSlotStatus(Integer doctorDaySlotId, String status) {
        logger.info("Updating slot ID {} status to {}", doctorDaySlotId, status);
        DoctorDaySlotEntity slot = doctorDaySlotDao.findById(doctorDaySlotId)
                .orElseThrow(() -> new SlotNotFoundException("Slot not found with ID: " + doctorDaySlotId));
        slot.setSlotStatus(status);
        return doctorDaySlotDao.save(slot);
    }
}



