package com.jobseeker.springbootmongdb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "candidate_apply")
public class CandidateApply {
    @Id
    private String applyId;
    private String candidateId;
    private String vacancyId;
    private String applyDate;
    private String createdDate;
}
