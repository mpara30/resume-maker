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
public class Data {
    PersonalData personal_data;
    List<Education> education;
    List<Work> work;
    List<Skills> skills;
    List<Projects> projects;
    List<Certificates> certificates;
}
