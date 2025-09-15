package com.HAM_application.service;

import com.HAM_application.dao.BillDao;
import com.HAM_application.dao.SpecializationFeeDao;
import com.HAM_application.dao.entity.AppointmentEntity;
import com.HAM_application.dao.entity.BillEntity;
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
public class BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillService.class);

    private final BillDao billDao;
    private final SpecializationFeeDao specializationFeeDao;

    public BillService(BillDao billDao, SpecializationFeeDao specializationFeeDao) {
        this.billDao = billDao;
        this.specializationFeeDao = specializationFeeDao;
    }

    
    public BillEntity generateBill(AppointmentEntity appointment) {
        String specialization = appointment.getDoctor().getDoctorSpecialization();
        logger.info("Generating bill for appointment ID: {}, specialization: {}", 
                    appointment.getAppointmentId(), specialization);

        SpecializationFeeEntity feeEntity = specializationFeeDao.findBySpecialization(specialization)
                .orElseThrow(() -> new SpecializationNotFoundException(
                        "Specialization not found: " + specialization));

        BigDecimal amount = feeEntity.getFee();

        BillEntity bill = new BillEntity();
        bill.setAppointment(appointment);
        
        bill.setBillAmount(amount);

        logger.info("Bill amount for appointment ID {}: {}", appointment.getAppointmentId(), amount);

        return billDao.save(bill);
    }

    
    public List<BillEntity> getBillByAppointment(Integer appointmentId) {
        logger.info("Fetching bill for appointment ID: {}", appointmentId);
        return billDao.findByAppointmentAppointmentId(appointmentId);
    }

    
    public List<BillEntity> getBillByPatient(Integer patientId) {
        logger.info("Fetching bills for patient ID: {}", patientId);
        return billDao.findByPatientId(patientId);
    }

    
    public List<BillEntity> getAllBills() {
        logger.info("Fetching all bills");
        return billDao.findAll();
    }
}



