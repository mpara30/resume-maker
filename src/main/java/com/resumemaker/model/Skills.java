package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Skills {
    private List<String> keywords;
    private String name;
}
