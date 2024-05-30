package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class Data {
    PersonalData personal_data;
    List<Education> education;
    List<Work> work;
    List<Skills> skills;
    List<Projects> projects;
    List<Certificates> certificates;
}
