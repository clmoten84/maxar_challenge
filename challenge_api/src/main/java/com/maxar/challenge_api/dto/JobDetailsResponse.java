package com.maxar.challenge_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

/**
 * JobDetailsResponse
 *
 * Models response from job details request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailsResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jobId;
}
