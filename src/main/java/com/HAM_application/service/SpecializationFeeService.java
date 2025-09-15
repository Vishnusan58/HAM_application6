package com.HAM_application.service;

import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.exception.IllegalArgumentException;
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
        String sanitizedSpecialization = normalizeSpecialization(specialization);
        BigDecimal sanitizedFee = validateFee(fee);

        logger.info("Adding new fee for specialization: {} with amount: {}", sanitizedSpecialization, sanitizedFee);
        SpecializationFeeEntity fees = new SpecializationFeeEntity(sanitizedSpecialization, sanitizedFee);
        return feeDao.save(fees);
    }


    public SpecializationFeeEntity updateFee(String specialization, BigDecimal newAmount) {
        String sanitizedSpecialization = normalizeSpecialization(specialization);
        BigDecimal sanitizedAmount = validateFee(newAmount);

        logger.info("Updating fee for specialization: {} with new amount: {}", sanitizedSpecialization, sanitizedAmount);
        SpecializationFeeEntity fees = feeDao.findBySpecialization(sanitizedSpecialization)
                .orElseThrow(() -> {
                    logger.error("Specialization not found: {}", sanitizedSpecialization);
                    return new SpecializationNotFoundException("Specialization not found: " + sanitizedSpecialization);
                });
        fees.setFee(sanitizedAmount);
        return feeDao.save(fees);
    }


    public BigDecimal getFeeBySpecialization(String specialization) {
        String sanitizedSpecialization = normalizeSpecialization(specialization);

        logger.info("Fetching fee for specialization: {}", sanitizedSpecialization);
        return feeDao.findBySpecialization(sanitizedSpecialization)
                .map(SpecializationFeeEntity::getFee)
                .orElseThrow(() -> {
                    logger.error("Specialization not found: {}", sanitizedSpecialization);
                    return new SpecializationNotFoundException("Specialization not found: " + sanitizedSpecialization);
                });
    }

   
    public List<SpecializationFeeEntity> getAllFees() {
        logger.info("Fetching all specialization fees");
        List<SpecializationFeeEntity> fees = feeDao.findAll();
        logger.info("Total fees found: {}", fees.size());
        return fees;
    }

    private String normalizeSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            logger.error("Invalid specialization input: {}", specialization);
            throw new IllegalArgumentException("Specialization must not be blank");
        }
        return specialization.trim();
    }

    private BigDecimal validateFee(BigDecimal fee) {
        if (fee == null) {
            logger.error("Fee value is required");
            throw new IllegalArgumentException("Fee must not be null");
        }
        if (fee.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid fee amount: {}", fee);
            throw new IllegalArgumentException("Fee must be greater than zero");
        }
        return fee;
    }
}



