package com.maxar.challenge_api.controller.rest;

import com.maxar.challenge_api.dto.JobDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class JobsRestController {

    /**
     * Generates a random UUID and returns in job details response
     * @param id id value from request
     * @return job details response containing unique job UUID
     */
    @GetMapping("jobs/{id}")
    public JobDetailsResponse getJobDetails(@PathVariable int id) {
        return new JobDetailsResponse(UUID.randomUUID().toString());
    }
}
