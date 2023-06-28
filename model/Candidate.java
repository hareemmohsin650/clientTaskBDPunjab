package com.example.clientbdtask.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Candidate")
public class Candidate {
    @Id
    private Long id;

    private String candidateName;

    private String candidateEmail;

    private String candidatePhoneNo;
    @DBRef
    private List<Job> appliedJobs;


}
