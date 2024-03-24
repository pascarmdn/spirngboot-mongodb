package com.jobseeker.springbootmongdb.controller;

import com.jobseeker.springbootmongdb.model.CandidateApply;
import com.jobseeker.springbootmongdb.service.CandidateApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidateApply")
public class CandidateApplyController {
    @Autowired
    private CandidateApplyService candidateApplyService;

    @GetMapping
    public List<CandidateApply> getAllCandidateApply() {
        return candidateApplyService.getAllCandidateApplys();
    }

    @PostMapping
    public CandidateApply apply(@RequestBody CandidateApply application) {
        return candidateApplyService.saveApplication(application);
    }

    @GetMapping("/filter-sort")
    public List<CandidateApply> filterCandidates(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        return candidateApplyService.filterCandidates(sortBy, sortOrder);
    }

    @GetMapping("/filter-keywords")
    public List<CandidateApply> filterByKeywords(@RequestParam(required = false) String field,
                                                 @RequestParam(required = false) String key) {
        return candidateApplyService.findByKeywords(field, key);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidateApply(@PathVariable String id) {
        candidateApplyService.deleteCandidateApplyById(id);
    }

    @PutMapping("/{id}")
    public CandidateApply updateCandidateApply(@PathVariable String id, @RequestBody CandidateApply candidateApply) {
        return candidateApplyService.updateCandidateApply(id, candidateApply);
    }
}
