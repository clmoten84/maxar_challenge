package com.maxar.challenge_runner.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * JobDetails
 *
 * Models a list of job UUIDs
 */
@Getter
@Setter
public class JobDetails {
    private List<String> jobs;

    public JobDetails(List<String> jobsData) {
        this.jobs = jobsData;
    }

    public JobDetails() {
        this.jobs = new ArrayList<>();
    }
}
