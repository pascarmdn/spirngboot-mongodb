package com.jobseeker.springbootmongdb.service.impl;

import com.jobseeker.springbootmongdb.model.Candidate;
import com.jobseeker.springbootmongdb.model.Vacancy;
import com.jobseeker.springbootmongdb.repository.VacancyRepository;
import com.jobseeker.springbootmongdb.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Vacancy> getAllVacancys() {
        return vacancyRepository.findAll();
    }

    @Override
    public Vacancy saveData(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public List<Vacancy> filterVacancy(String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, sortBy): Sort.by(Sort.Direction.DESC, sortBy);
        return vacancyRepository.findAll(sort);
    }

    @Override
    public List<Vacancy> findByKeywords(String field, String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(key));
        return mongoTemplate.find(query, Vacancy.class);
    }

    @Override
    public void deleteVacancyById(String id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public Vacancy updateVacancy(String id, Vacancy vacancy) {
        Optional<Vacancy> existingVacancyOptional = vacancyRepository.findById(id);
        if(existingVacancyOptional.isPresent()) {
            Vacancy existingVacancy = existingVacancyOptional.get();
            existingVacancy.setVacancyId(vacancy.getVacancyId());
            existingVacancy.setVacancyName(vacancy.getVacancyName());
            existingVacancy.setMinAge(vacancy.getMinAge());
            existingVacancy.setCreatedDate(vacancy.getCreatedDate());
            existingVacancy.setExpiredDate(vacancy.getExpiredDate());
            existingVacancy.setMaxAge(vacancy.getMaxAge());
            existingVacancy.setRequirementGender(vacancy.getRequirementGender());
            return vacancyRepository.save(existingVacancy);
        } else {
            throw new RuntimeException("Aplikasi kandidat tidak ditemukan dengan ID: " + id);
        }
    }
}
