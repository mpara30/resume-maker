package com.resumemaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {
    private String name;
    private String email;
    private String phone;
    private Location location;
    private String website;

}
