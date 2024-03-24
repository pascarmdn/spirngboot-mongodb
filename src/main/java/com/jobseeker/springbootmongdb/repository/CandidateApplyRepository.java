package com.jobseeker.springbootmongdb.repository;

import com.jobseeker.springbootmongdb.model.CandidateApply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateApplyRepository extends MongoRepository<CandidateApply, String> {
}
