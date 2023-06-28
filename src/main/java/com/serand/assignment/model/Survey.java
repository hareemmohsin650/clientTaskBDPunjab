package com.serand.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@Document(collection = "surveys")
@AllArgsConstructor
public class Survey {
    @Id
    private String id;

    private String name;

    Map<String, String> surveyQuestionsWithAnswereCriteria = new HashMap<String, String>();

}
