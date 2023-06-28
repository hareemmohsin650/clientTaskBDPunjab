package com.serand.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class SurveyCompletionRequest {

    @JsonProperty(value="candidate-id")
    private String Id;

    @JsonProperty(value = "survey-Id")
    private String  surveyId;

    @JsonProperty(value = "survey-questions-answere")
    private Map<String, String> surveyQuestionsWithAnswere;

}
