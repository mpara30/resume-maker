package com.resumemaker.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumemaker.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component("resumeRepo")
public class ResumeRepo {
    @Autowired
    private ObjectMapper mapper;

    public Data getData(String path) throws IOException {
        return mapper.readValue(new File(path), Data.class);
    }
}
