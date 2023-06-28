package com.serand.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document(collection = "candidates")
@AllArgsConstructor
public class Candidate {
    @Id
    private String id;

    private String name;

    private String candidates;
    @DBRef
    private List<Job> appliedJobList;

    List<Map<String, String>> surveyQuestionsWithAnswere=new ArrayList<>();

    private Integer surveyScore;


}
