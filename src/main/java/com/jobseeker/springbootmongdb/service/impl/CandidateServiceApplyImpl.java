package com.jobseeker.springbootmongdb.service.impl;

import com.jobseeker.springbootmongdb.model.CandidateApply;
import com.jobseeker.springbootmongdb.repository.CandidateApplyRepository;
import com.jobseeker.springbootmongdb.service.CandidateApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceApplyImpl  implements CandidateApplyService {
    @Autowired
    private CandidateApplyRepository candidateApplyRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<CandidateApply> getAllCandidateApplys() {
        return candidateApplyRepository.findAll();
    }

    @Override
    public CandidateApply saveApplication(CandidateApply application) {
        return candidateApplyRepository.save(application);
    }

    @Override
    public List<CandidateApply> filterCandidates(String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortBy): Sort.by(Sort.Direction.DESC, sortBy);
        return candidateApplyRepository.findAll(sort);
    }

    @Override
    public List<CandidateApply> findByKeywords(String field, String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(key));
        return mongoTemplate.find(query, CandidateApply.class);
    }

    @Override
    public void deleteCandidateApplyById(String id) {
        candidateApplyRepository.deleteById(id);
    }

    @Override
    public CandidateApply updateCandidateApply(String id, CandidateApply candidateApply) {
        Optional<CandidateApply> existingCandidateApplyOptional = candidateApplyRepository.findById(id);
        if(existingCandidateApplyOptional.isPresent()) {
            CandidateApply existingCandidateApply = existingCandidateApplyOptional.get();
            existingCandidateApply.setCandidateId(candidateApply.getCandidateId());
            existingCandidateApply.setVacancyId(candidateApply.getVacancyId());
            existingCandidateApply.setApplyDate(candidateApply.getApplyDate());
            existingCandidateApply.setCreatedDate(candidateApply.getCreatedDate());
            return candidateApplyRepository.save(existingCandidateApply);
        } else {
            throw new RuntimeException("Aplikasi kandidat tidak ditemukan dengan ID: " + id);
        }
    }


}
