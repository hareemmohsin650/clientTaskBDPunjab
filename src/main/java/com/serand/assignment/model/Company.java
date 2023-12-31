package com.serand.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@Document(collection = "company")
@AllArgsConstructor
public class Company {
    @Id
    private String id;

    private String name;

    @DBRef
    private List<Job> jobList;


    // Other company properties, getters, and setters

}
