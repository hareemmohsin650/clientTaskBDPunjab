package com.example.clientbdtask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Job")
public class Job {
    @Id
    private Long id;
    private String jobDescription;
    private String jobBudget;
}
