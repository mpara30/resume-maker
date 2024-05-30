package com.resumemaker.service;

import com.resumemaker.model.Data;
import com.resumemaker.repository.ResumeRepo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ResumeGeneratorService {
    private static final String resumePath = "src/main/resources/new_resume.json";
    @Resource()
    private ResumeRepo resumeRepo;
    private Data jsonData;
    public void generateResume() {
        try {
            jsonData = resumeRepo.getData(resumePath);
            System.out.println(jsonData.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
