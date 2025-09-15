package com.HAM_application;

import com.HAM_application.controller.SpecializationFeeController;
import com.HAM_application.dao.entity.SpecializationFeeEntity;
import com.HAM_application.service.SpecializationFeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpecializationFeeController.class)
class SpecializationFeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecializationFeeService feeService;

    @Test
    void addFee_ShouldAcceptJsonPayload() throws Exception {
        SpecializationFeeEntity saved = new SpecializationFeeEntity("Cardiologist", new BigDecimal("1500"));
        when(feeService.addFee(eq("Cardiologist"), eq(new BigDecimal("1500")))).thenReturn(saved);

        mockMvc.perform(post("/specialization-fees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"specialization\":\"Cardiologist\",\"fee\":1500}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialization").value("Cardiologist"))
                .andExpect(jsonPath("$.fee").value(1500));

        verify(feeService).addFee("Cardiologist", new BigDecimal("1500"));
    }

    @Test
    void addFee_ShouldAcceptQueryParameters() throws Exception {
        SpecializationFeeEntity saved = new SpecializationFeeEntity("Dermatologist", new BigDecimal("1000"));
        when(feeService.addFee(eq("Dermatologist"), eq(new BigDecimal("1000")))).thenReturn(saved);

        mockMvc.perform(post("/specialization-fees/add")
                        .param("specialization", "Dermatologist")
                        .param("fee", "1000"))
                .andExpect(status().isOk());

        verify(feeService).addFee("Dermatologist", new BigDecimal("1000"));
    }

    @Test
    void addFee_ShouldReturnBadRequestWhenPayloadMissing() throws Exception {
        mockMvc.perform(post("/specialization-fees/add"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(feeService);
    }
}
