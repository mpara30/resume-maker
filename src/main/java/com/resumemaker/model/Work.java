package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Work {
    private String company;
    private String location;
    private String position;
    private String website;
    private String startDate;
    private String endDate;
    private List<String> highlights;
}
