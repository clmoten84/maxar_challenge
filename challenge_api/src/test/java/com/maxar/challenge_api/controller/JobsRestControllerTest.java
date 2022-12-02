package com.maxar.challenge_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxar.challenge_api.dto.JobDetailsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JobsRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("GET: Fetch job details UUID")
    @Test
    public void testGetJobDetails() {
        try {
            int resourceId = 1;
            MvcResult testResult = this.mockMvc.perform(get("/api/jobs/" + resourceId)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
            assertEquals(MediaType.APPLICATION_JSON_VALUE, testResult.getResponse().getContentType());

            JobDetailsResponse response = this.getJobDetailsResponse(testResult);
            assertNotNull(response);
            assertNotNull(response.getJobId());
        }
        catch (Exception ex) {
            fail("An unexpected error occurred during test...");
        }
    }

    private JobDetailsResponse getJobDetailsResponse(MvcResult result) throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), JobDetailsResponse.class);
    }
}
