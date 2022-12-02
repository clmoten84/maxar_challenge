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

    /**
     * Runner will spawn multiple threads to make concurrent GET requests to fetch job details
     * from job details API. The responses will be collected in a thread-safe list implementation
     * and used to populate a JobDetails model. The JobDetails object will be pretty printed as a
     * JSON representation at program end.
     * @param args 1 expected arg (number of GET requests to make to job details API)
     */
    @Override
    public void run(String... args) throws Exception {
        int numExecutions;
        try {
            numExecutions = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Number of executions argument must be parsable to an integer...");
        }

        // Use thread-safe synchronized list to collect responses from each thread
        List<String> jobIds = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < numExecutions; i++) {
            // Spawn a new thread and execute a GET request to the job details API
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

        // Add collected job UUIDs to JobDetails list and pretty print JobDetails JSON object
        JobDetails jobDetails = new JobDetails(jobIds);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.printf("Number of Job IDs fetched vs. Number of API requests: %d / %d\n", jobIds.size(), numExecutions);
        System.out.println(mapper.writeValueAsString(jobDetails));
    }
}
