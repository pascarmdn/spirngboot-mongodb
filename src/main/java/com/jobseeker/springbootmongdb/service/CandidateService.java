package com.jobseeker.springbootmongdb.service;

import com.jobseeker.springbootmongdb.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> getAllCandidates();
    Candidate saveData(Candidate candidate);
    List<Candidate> filterCandidates(String sortBy, String sortOrder);
    List<Candidate> findByKeywords(String field, String key);
    void deleteCandidateById(String id);
    Candidate updateCandidate(String id, Candidate candidate);
}
