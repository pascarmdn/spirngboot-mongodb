package com.jobseeker.springbootmongdb.service;

import com.jobseeker.springbootmongdb.model.CandidateApply;

import java.util.List;

public interface CandidateApplyService {
    List<CandidateApply> getAllCandidateApplys();
    CandidateApply saveApplication(CandidateApply application);
    List<CandidateApply> filterCandidates(String sortBy, String sortOrder);
    List<CandidateApply> findByKeywords(String field, String key);
    void deleteCandidateApplyById(String id);
    CandidateApply updateCandidateApply(String id, CandidateApply candidateApply);
}
