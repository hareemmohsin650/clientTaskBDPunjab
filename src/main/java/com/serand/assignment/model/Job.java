package com.serand.assignment.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document(collection = "Job")
@AllArgsConstructor
public class Job {
    @Id
    private Long Id;

    private String jobTitle;

    private String jobDescription;
    @DBRef
    List<Survey> listOfSurvey=new ArrayList<>();

}
