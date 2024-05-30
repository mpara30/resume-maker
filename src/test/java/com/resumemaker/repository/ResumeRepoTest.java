package com.resumemaker.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumemaker.model.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ResumeRepoTest {
    private static final String resume = "src/test/resources/test_resume.json";
    @InjectMocks
    ResumeRepo resumeRepo;
    @Mock
    ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void readDataSuccessfully() throws IOException {
        Data expectedData = new Data();

        Mockito.when(mapper.readValue(Mockito.any(File.class), Mockito.eq(Data.class))).thenReturn(expectedData);

        Data testData = resumeRepo.getData(resume);

        assertNotNull(testData);
        assertEquals(expectedData, testData);
    }

}
