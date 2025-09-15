package com.HAM_application.service;

import com.HAM_application.dao.DayDao;
import com.HAM_application.dao.entity.DayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DayService {

    private static final Logger logger = LoggerFactory.getLogger(DayService.class);

    private final DayDao dayDao;

    public DayService(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    
    public List<DayEntity> getAllDays() {
        logger.info("Fetching all days");
        return dayDao.findAll();
    }

    
    public List<DayEntity> getAvailableDaysBySpecialization(String specialization) {
        logger.info("Fetching available days for specialization: {}", specialization);
        return dayDao.findAvailableDaysBySpecialization(specialization);
    }
}


