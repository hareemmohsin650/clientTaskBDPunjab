package com.serand.assignment.repository;

import com.serand.assignment.model.Candidate;
import com.serand.assignment.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

    List<Candidate> findBySurveyScore(Integer Score);
}