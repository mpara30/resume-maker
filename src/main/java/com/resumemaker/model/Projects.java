package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
public class Projects {
    private String name;
    private String description;
    private String url;
    private List<String> keywords;
}
