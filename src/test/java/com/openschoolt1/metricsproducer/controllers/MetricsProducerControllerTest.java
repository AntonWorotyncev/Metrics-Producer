package com.openschoolt1.metricsproducer.controllers;

import com.openschoolt1.metricsproducer.service.MetricsService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(MetricsProducerController.class)
@ExtendWith(MockitoExtension.class)
public class MetricsProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricsService metricsService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testSendMetrics() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(post("/api/metrics-producer/metrics"))
                .andExpect(status().isOk());
        verify(metricsService).publishingTopicMetrics();
    }
}
