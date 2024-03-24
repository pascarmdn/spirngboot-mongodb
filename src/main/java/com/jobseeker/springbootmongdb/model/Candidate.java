package com.jobseeker.springbootmongdb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@Builder
@Document(collection = "candidates")
public class Candidate {
    @Id
    private String candidateId;
    private String fullName;
    private String dob;
    private String gender;
}
