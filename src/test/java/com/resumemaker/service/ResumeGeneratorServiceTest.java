package com.resumemaker.service;

import com.resumemaker.model.*;
import com.resumemaker.repository.ResumeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResumeGeneratorServiceTest {

    @Mock
    ResumeRepo resumeRepo;

    @InjectMocks
    ResumeGeneratorService resumeGeneratorService;

    @Mock
    private Data testData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateResume_success() throws IOException {
        mockData();
        when(resumeRepo.getData(anyString())).thenReturn(testData);

        resumeGeneratorService.generateResume();

        verify(resumeRepo, times(1)).getData("src/main/resources/new_resume.json");
        assertNotNull(resumeGeneratorService);
    }

    @Test
    public void generateResume_ioException() throws IOException {
        mockData();
        when(resumeRepo.getData(anyString())).thenThrow(new IOException("Test IOException"));

        Logger logger = LoggerFactory.getLogger(ResumeGeneratorService.class);
        resumeGeneratorService.generateResume();

        assertNotNull(logger);
    }

    private void mockData() {
        when(testData.getSkills())
                .thenReturn(List.of(new Skills(List.of("Spring", "Hibernate"),"Java")));
        when(testData.getWork())
                .thenReturn(List.of(new Work("Developer", "Company", "Location", "2020-01-01", "2022-01-01", List.of("Development"))));
        when(testData.getEducation())
                .thenReturn(List.of(new Education("University", "Location", "BSc", "Computer Science", "2018-01-01", "2022-01-01")));

        PersonalData personalData = new PersonalData("John Doe", "john.doe@example.com", "1234567890", new Location("123 Main St"), "www.example.com");
        when(testData.getPersonal_data()).thenReturn(personalData);

        when(testData.getProjects())
                .thenReturn(List.of(new Projects("Resume Generator", "Some description", List.of("Java", "Spring"))));
        when(testData.getCertificates())
                .thenReturn(List.of(new Certificates("AWS Cloud Practitioner", "2022", "AWS")));
    }

}
