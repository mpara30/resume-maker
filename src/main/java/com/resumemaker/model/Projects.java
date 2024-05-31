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
public class Projects {
    private String name;
    private String description;
    private String url;
    private List<String> keywords;
}
