package com.resumemaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String institution;
    private String location;
    private String area;
    private String startDate;
    private String endDate;
    private String studyType;
}
