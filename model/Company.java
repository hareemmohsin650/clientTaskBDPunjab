package com.example.clientbdtask.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Company")
public class Company {
    @Id
    private Long id;

    private String companyName;

    private String companyEmail;

    private String companyMobileNo;

    private String companyRegisteredAddress;

    private String businessId;
    @DBRef
    private List<Job> jobs;

}
