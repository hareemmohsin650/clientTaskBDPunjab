package com.serand.assignment.controller;


import com.serand.assignment.dto.SurveyCompletionRequest;
import com.serand.assignment.model.Candidate;
import com.serand.assignment.model.Job;
import com.serand.assignment.model.Survey;
import com.serand.assignment.repository.CandidateRepository;
import com.serand.assignment.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Responsible for all user related endpoints.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/surveys")
public class SurveyController {


    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/company/{companyId}")
    public List<Survey> getSurveysByCompanyId(@PathVariable("companyId") String companyId) {
        return surveyRepository.findByCompanyId(companyId);
    }
    @PostMapping
    public ResponseEntity<Survey>  createSurveyQuestions(@RequestBody Survey survey) {
        Survey surveyResponse=surveyRepository.save(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);

    }
    @PostMapping("/createSurvey-candidate")
    public ResponseEntity<Survey>  submitSurvey(@RequestBody Survey survey) {
        Survey surveyResponse=surveyRepository.save(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);

    }
    @GetMapping("/search-Survey-on-score/{score}")
    public ResponseEntity<List<Candidate>>  searchsurvey(@PathVariable("score") Integer score, @RequestParam (required = false,defaultValue = "") Job job) {
        List<Candidate> candiadatesWithSurveyCompleted=this.candidateRepository.findBySurveyScore(score);
        List<Survey>surveys=job.getListOfSurvey();
        List<Candidate> matchedJobs=new ArrayList<>();
        candiadatesWithSurveyCompleted.forEach(candidate -> candidate.getAppliedJobList().forEach(job1 ->{
       if(job1.getJobTitle().equals(job.getJobTitle())) {
           matchedJobs.add(candidate);
       }
        }));
        if(job.equals("")){
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(candiadatesWithSurveyCompleted);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchedJobs);

    }
    @PostMapping("/company/createSurvey")
    public  ResponseEntity<Candidate> createSurvey(@RequestBody SurveyCompletionRequest surveyCompletionRequest)
    {
        Candidate candidate = this.candidateRepository.findById(surveyCompletionRequest.getId()).orElseThrow(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        candidate.getSurveyQuestionsWithAnswere().add(surveyCompletionRequest.getSurveyQuestionsWithAnswere());
        candidate.setSurveyQuestionsWithAnswere(candidate.getSurveyQuestionsWithAnswere());
        Candidate candidate1=this.candidateRepository.save(candidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate1);
    }
    @PostMapping("/company/createScore")
    public Integer createScoreForSurveyCompletion(@RequestBody SurveyCompletionRequest surveyCompletionRequest) {
        Integer score=new Integer(0);
        Survey survey = this.surveyRepository.findById(surveyCompletionRequest.getSurveyId()).orElseThrow(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        int count=0;
        Map<String, String> surveyQuestionsWithAnsweres=surveyCompletionRequest.getSurveyQuestionsWithAnswere();
        for (Map.Entry<String,String> entry : surveyQuestionsWithAnsweres.entrySet()) {
            String answereExpected=survey.getSurveyQuestionsWithAnswereCriteria().get(entry.getKey());
            if(entry.getValue().equals(answereExpected)){
                score+=2;
            }
            count++;
        }

        Candidate candidate = this.candidateRepository.findById(surveyCompletionRequest.getSurveyId()).orElseThrow(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        candidate.setSurveyScore(score);
        this.candidateRepository.save(candidate);
        return score;
    }
}