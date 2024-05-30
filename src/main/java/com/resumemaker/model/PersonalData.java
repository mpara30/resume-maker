package com.resumemaker.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonalData {
    private String name;
    private String email;
    private String phone;
    private Location location;
    private String website;

}
