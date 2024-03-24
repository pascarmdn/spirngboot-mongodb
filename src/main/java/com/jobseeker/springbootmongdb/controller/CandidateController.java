package com.jobseeker.springbootmongdb.controller;

import com.jobseeker.springbootmongdb.model.Candidate;
import com.jobseeker.springbootmongdb.model.CandidateApply;
import com.jobseeker.springbootmongdb.repository.CandidateRepository;
import com.jobseeker.springbootmongdb.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping
    public List<Candidate> getAllCandidate() {
        return candidateService.getAllCandidates();
    }

    @PostMapping
    public Candidate savaData(@RequestBody Candidate candidate) {
        Optional<Candidate> existingCandidateOptional = candidateRepository.findById(candidate.getCandidateId());
        if (existingCandidateOptional.isEmpty()) {
            return candidateService.saveData(candidate);
        } else {
            throw new RuntimeException("Aplikasi kandidat dengan id : " + candidate.getCandidateId() + " sudah ada");
        }
    }

    @GetMapping("/filter-sort")
    public List<Candidate> filterCandidates(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return candidateService.filterCandidates(sortBy, sortOrder);
    }

    @GetMapping("/filter-keywords")
    public List<Candidate> filterByKeywords(@RequestParam(required = false) String field,
                                                 @RequestParam(required = false) String key) {
        return candidateService.findByKeywords(field, key);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable String id) {
        candidateService.deleteCandidateById(id);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable String id, @RequestBody Candidate candidate) {
        return candidateService.updateCandidate(id, candidate);
    }
}
