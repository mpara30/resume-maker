package com.resumemaker.service;

import com.resumemaker.model.Data;
import com.resumemaker.repository.ResumeRepo;
import com.resumemaker.util.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class ResumeGeneratorService {
    private String resumeHtml;
    private String personalDataHtml;
    private String certificatesHtml;
    private String educationHtml;
    private String projectsHtml;
    private String skillsHtml;
    private String workHtml;
    @Resource()
    private ResumeRepo resumeRepo;
    private Data jsonData;
    public void generateResume() {
        try {
            jsonData = resumeRepo.getData(Constants.resumePath);
            System.out.println(jsonData.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        loadHtmlTemplates();

        String finalResume = resumeHtml.replace("{{personal_data}}", buildPersonalDataSection(personalDataHtml))
                .replace("{{education_section}}", buildEducationSection(educationHtml));


        System.out.println(finalResume);
    }

    private String buildEducationSection(String education) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonData.getEducation().size(); i++) {
            String educationElement = education.replace("{{education_institution}}", jsonData.getEducation().get(i).getInstitution())
                    .replace("{{education_location}}", jsonData.getEducation().get(i).getLocation())
                    .replace("{{education_studyType}}", jsonData.getEducation().get(i).getStudyType())
                    .replace("{{education_area}}", jsonData.getEducation().get(i).getArea())
                    .replace("{{education_startDate}}", jsonData.getEducation().get(i).getStartDate())
                    .replace("{{education_endDate}}", jsonData.getEducation().get(i).getEndDate());
            sb.append(educationElement);
        }
        return sb.toString();
    }

    private String buildPersonalDataSection(String personalData) {
        return personalData.replace("{{personal_data_name}}", jsonData.getPersonal_data().getName())
                .replace("{{personal_data_email}}", jsonData.getPersonal_data().getEmail())
                .replace("{{personal_data_phone}}", jsonData.getPersonal_data().getPhone())
                .replace("{{personal_data_address}}", jsonData.getPersonal_data().getLocation().getAddress())
                .replace("{{personal_data_website}}", jsonData.getPersonal_data().getWebsite());
    }

    private void loadHtmlTemplates() {
        try {
            personalDataHtml = Files.readString(Path.of(Constants.personalDataHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            certificatesHtml = Files.readString(Path.of(Constants.certificatesHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            educationHtml = Files.readString(Path.of(Constants.educationHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            projectsHtml = Files.readString(Path.of(Constants.projectsHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            skillsHtml = Files.readString(Path.of(Constants.skillsHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            workHtml = Files.readString(Path.of(Constants.workHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        try {
            resumeHtml = Files.readString(Path.of(Constants.resumeHtmlPath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
