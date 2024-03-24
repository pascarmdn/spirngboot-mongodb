package com.jobseeker.springbootmongdb.service.impl;

import com.jobseeker.springbootmongdb.model.Candidate;
import com.jobseeker.springbootmongdb.model.CandidateApply;
import com.jobseeker.springbootmongdb.repository.CandidateRepository;
import com.jobseeker.springbootmongdb.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl  implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate saveData(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> filterCandidates(String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortBy): Sort.by(Sort.Direction.DESC, sortBy);
        return candidateRepository.findAll(sort);
    }

    @Override
    public List<Candidate> findByKeywords(String field, String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(key));
        return mongoTemplate.find(query, Candidate.class);
    }

    @Override
    public void deleteCandidateById(String id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate updateCandidate(String id, Candidate candidate) {
        Optional<Candidate> existingCandidateOptional = candidateRepository.findById(id);
        if(existingCandidateOptional.isPresent()) {
            Candidate existingCandidate = existingCandidateOptional.get();
            existingCandidate.setCandidateId(candidate.getCandidateId());
            existingCandidate.setFullName(candidate.getFullName());
            existingCandidate.setDob(candidate.getDob());
            existingCandidate.setGender(candidate.getGender());
            return candidateRepository.save(existingCandidate);
        } else {
            throw new RuntimeException("Aplikasi kandidat tidak ditemukan dengan ID: " + id);
        }
    }

}
