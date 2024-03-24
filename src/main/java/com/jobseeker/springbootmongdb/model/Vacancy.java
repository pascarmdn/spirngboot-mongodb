package com.jobseeker.springbootmongdb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "vacancies")
public class Vacancy {
    @Id
    private String vacancyId;
    private String vacancyName;
    private int minAge;
    private int maxAge;
    private String requirementGender;
    private String createdDate;
    private String expiredDate;
}
