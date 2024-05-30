package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Data {
    PersonalData personalData;
    Education education;
    Work work;
    Skills skills;
    Projects projects;
    Certificates certificates;
}
