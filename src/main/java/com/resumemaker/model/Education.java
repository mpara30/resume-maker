package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Education {
    private String institution;
    private String location;
    private String area;
    private String startDate;
    private String endDate;
    private String gpa;
    private String studyType;
}
