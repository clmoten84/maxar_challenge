package com.maxar.challenge_runner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDetailsAPIResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String jobId;
}
