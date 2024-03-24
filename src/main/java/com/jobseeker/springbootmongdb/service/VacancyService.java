package com.jobseeker.springbootmongdb.service;

import com.jobseeker.springbootmongdb.model.Vacancy;

import java.util.List;

public interface VacancyService {
    List<Vacancy> getAllVacancys();
    Vacancy saveData(Vacancy vacancy);
    List<Vacancy> filterVacancy(String sortBy, String sortOrder);
    List<Vacancy> findByKeywords(String field, String key);
    void deleteVacancyById(String id);
    Vacancy updateVacancy(String id, Vacancy vacancy);
}
