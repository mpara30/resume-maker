package com.resumemaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private String company;
    private String location;
    private String position;
    private String startDate;
    private String endDate;
    private List<String> highlights;
}
