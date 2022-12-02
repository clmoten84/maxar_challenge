package com.maxar.challenge_runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maxar.challenge_runner.model.JobDetails;
import com.maxar.challenge_runner.model.JobDetailsAPIResponse;
import com.maxar.challenge_runner.utils.RequestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ChallengeRunnerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ChallengeRunnerApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        int numExecutions = 1500;

        List<String> jobIds = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < numExecutions; i++) {
            // Spawn a thread and execute API request in a thread
            int resourceId = i;
            Thread thread = new Thread(() -> {
                JobDetailsAPIResponse response = RequestUtils.executeJobDetailsRequest(resourceId + 1);
                if (response != null) {
                    jobIds.add(response.getJobId());
                }
            });

            thread.start();
            thread.join();
        }

        // Add response to JobDetails list and pretty print JobDetails JSON object
        JobDetails jobDetails = new JobDetails(jobIds);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.printf("Number of Job IDs fetched vs. Number of API requests: %d / %d\n", jobIds.size(), numExecutions);
        System.out.println(mapper.writeValueAsString(jobDetails));
    }
}
