package com.jobseeker.springbootmongdb.repository;

import com.jobseeker.springbootmongdb.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends MongoRepository<Vacancy, String> {
}
