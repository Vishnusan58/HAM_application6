package com.HAM_application.service;

import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.SpecializationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class SpecializationFeeService {

    private static final Logger logger = LoggerFactory.getLogger(SpecializationFeeService.class);

    private final SpecializationFeeDao feeDao;

    public SpecializationFeeService(SpecializationFeeDao feeDao) {
        this.feeDao = feeDao;
    }

    // Add new specialization fee
    public SpecializationFeeEntity addFee(String specialization, BigDecimal fee) {
        logger.info("Adding new fee for specialization: {} with amount: {}", specialization, fee);
        SpecializationFeeEntity fees = new SpecializationFeeEntity(specialization, fee);
        return feeDao.save(fees);
    }

    
    public SpecializationFeeEntity updateFee(String specialization, BigDecimal newAmount) {
        logger.info("Updating fee for specialization: {} with new amount: {}", specialization, newAmount);
        SpecializationFeeEntity fees = feeDao.findBySpecialization(specialization)
                .orElseThrow(() -> {
                    logger.error("Specialization not found: {}", specialization);
                    return new SpecializationNotFoundException("Specialization not found: " + specialization);
                });
        fees.setFee(newAmount);
        return feeDao.save(fees);
    }

    
    public BigDecimal getFeeBySpecialization(String specialization) {
        logger.info("Fetching fee for specialization: {}", specialization);
        return feeDao.findBySpecialization(specialization)
                .map(SpecializationFeeEntity::getFee)
                .orElseThrow(() -> {
                    logger.error("Specialization not found: {}", specialization);
                    return new SpecializationNotFoundException("Specialization not found: " + specialization);
                });
    }

   
    public List<SpecializationFeeEntity> getAllFees() {
        logger.info("Fetching all specialization fees");
        List<SpecializationFeeEntity> fees = feeDao.findAll();
        logger.info("Total fees found: {}", fees.size());
        return fees;
    }
}



