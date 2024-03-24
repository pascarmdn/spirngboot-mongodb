package com.jobseeker.springbootmongdb.repository;

import com.jobseeker.springbootmongdb.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {
}
