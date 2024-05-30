package com.resumemaker.service;

import com.resumemaker.model.Data;
import com.resumemaker.repository.ResumeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResumeGeneratorServiceTest {

    @Mock
    ResumeRepo resumeRepo;

    @InjectMocks
    ResumeGeneratorService resumeGeneratorService;

    private Data testData;

    @BeforeEach
    public void setup() {
        testData = new Data();
    }

    @Test
    public void generateResume_success() throws IOException {
        when(resumeRepo.getData(anyString())).thenReturn(testData);

        resumeGeneratorService.generateResume();

        Mockito.verify(resumeRepo).getData("src/main/resources/new_resume.json");
    }

    @Test
    public void generateResume_ioException() throws IOException {
        when(resumeRepo.getData(anyString())).thenThrow(new IOException("Test IOException"));

        Logger logger = LoggerFactory.getLogger(ResumeGeneratorService.class);
        resumeGeneratorService.generateResume();

        assertNotNull(logger);
    }

}
