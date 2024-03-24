package com.jobseeker.springbootmongdb.controller;

import com.jobseeker.springbootmongdb.model.Candidate;
import com.jobseeker.springbootmongdb.model.Vacancy;
import com.jobseeker.springbootmongdb.repository.CandidateRepository;
import com.jobseeker.springbootmongdb.repository.VacancyRepository;
import com.jobseeker.springbootmongdb.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @Autowired
    VacancyRepository vacancyRepository;

    @GetMapping
    public List<Vacancy> getAllVacancy() {
        return vacancyService.getAllVacancys();
    }

    @PostMapping
    public Vacancy savaData(@RequestBody Vacancy vacancy) {
        Optional<Vacancy> existingVacancyOptional = vacancyRepository.findById(vacancy.getVacancyId());
        if (existingVacancyOptional.isEmpty()) {
            return vacancyService.saveData(vacancy);
        } else {
            throw new RuntimeException("Aplikasi kandidat dengan id : " + vacancy.getVacancyId() + " sudah ada");
        }
    }

    @GetMapping("/filter-sort")
    public List<Vacancy> filterCandidates(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return vacancyService.filterVacancy(sortBy, sortOrder);
    }

    @GetMapping("/filter-keywords")
    public List<Vacancy> filterByKeywords(@RequestParam(required = false) String field,
                                            @RequestParam(required = false) String key) {
        return vacancyService.findByKeywords(field, key);
    }

    @DeleteMapping("/{id}")
    public void deleteVacancy(@PathVariable String id) {
        vacancyService.deleteVacancyById(id);
    }

    @PutMapping("/{id}")
    public Vacancy updateVacancy(@PathVariable String id, @RequestBody Vacancy vacancy) {
        return vacancyService.updateVacancy(id, vacancy);
    }
}
