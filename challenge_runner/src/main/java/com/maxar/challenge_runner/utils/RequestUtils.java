package com.maxar.challenge_runner.utils;

import com.maxar.challenge_runner.model.JobDetailsAPIResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class RequestUtils {

    private static final String BASE_URL = "http://localhost:8080/api";

    /**
     * Executes a GET request to API specified at BASE_URL to fetch a job details UUID
     * @param resourceId id of resource to fetch
     * @return job details response object
     */
    public static JobDetailsAPIResponse executeJobDetailsRequest(int resourceId) {
        // Build instance of web client and set URL to base url
        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();

        // Execute request and block until response object is ready
        return webClient.get()
                .uri("/jobs/" + resourceId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(JobDetailsAPIResponse.class)
                .block();
    }
}
