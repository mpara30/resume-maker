package com.resumemaker.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.resumemaker.model.Data;
import com.resumemaker.repository.ResumeRepo;
import com.resumemaker.util.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
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
                .replace("{{education_section}}", buildEducationSection(educationHtml))
                .replace("{{work_section}}", buildWorkSection(workHtml))
                .replace("{{skills_section}}", buildSkillsSection(skillsHtml))
                .replace("{{projects_section}}", buildProjectsSection(projectsHtml))
                .replace("{{certificates_section}}", buildCertificatesSection(certificatesHtml));

        System.out.println(finalResume);
        createPdfFile(finalResume);
    }

    private void createPdfFile(String finalResume) {
        PdfWriter pdfWriter;

        try {
            pdfWriter = new PdfWriter("src/main/resources/resume.pdf");
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            return;
        }

        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDoc);

        HtmlConverter.convertToElements(finalResume).forEach(e -> document.add((IBlockElement) e));
        document.close();
    }

    private String buildCertificatesSection(String certificatesHtml) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonData.getCertificates().size(); i++) {
            String certificateElement = certificatesHtml.replace("{{certificate_title}}", jsonData.getCertificates().get(i).getTitle())
                    .replace("{{certificate_date}}", jsonData.getCertificates().get(i).getDate())
                    .replace("{{certificate_awarder}}", jsonData.getCertificates().get(i).getAwarder());
            sb.append(certificateElement);
        }
        return sb.toString();
    }

    private String buildProjectsSection(String projectsHtml) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonData.getProjects().size(); i++) {
            String projectElement = projectsHtml.replace("{{project_name}}", jsonData.getProjects().get(i).getName())
                    .replace("{{project_keywords}}",
                            jsonData.getProjects().get(i).getKeywords().toString()
                                    .replace("[","")
                                    .replace("]","")) //TODO adapt
                    .replace("{{project_description}}", jsonData.getProjects().get(i).getDescription());
            sb.append(projectElement);
        }
        return sb.toString();
    }

    private String buildSkillsSection(String skillsHtml) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonData.getSkills().size(); i++) {
            String skillsElement = skillsHtml.replace("{{skills_name}}", jsonData.getSkills().get(i).getName())
                    .replace("{{skills_keywords}}",
                            jsonData.getSkills().get(i).getKeywords().toString()
                                    .replace("[","")
                                    .replace("]","")); //TODO adapt;
            sb.append(skillsElement);
        }
        return sb.toString();
    }

    private String buildWorkSection(String workHtml) {
        StringBuilder sb =  new StringBuilder();
        for (int i = 0; i < jsonData.getWork().size(); i++) {
            String workElement = workHtml.replace("{{work_position}}", jsonData.getWork().get(i).getPosition())
                    .replace("{{work_company}}", jsonData.getWork().get(i).getCompany())
                    .replace("{{work_location}}", jsonData.getWork().get(i).getLocation())
                    .replace("{{work_startDate}}", jsonData.getWork().get(i).getStartDate())
                    .replace("{{work_endDate}}", jsonData.getWork().get(i).getEndDate())
                    .replace("{{work_highlights}}",
                            jsonData.getWork().get(i).getHighlights().toString()
                                    .replace("[","")
                                    .replace("]","")); //TODO adapt
            sb.append(workElement);
        }
        return sb.toString();
    }

    private String buildEducationSection(String educationHtml) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonData.getEducation().size(); i++) {
            String educationElement = educationHtml.replace("{{education_institution}}", jsonData.getEducation().get(i).getInstitution())
                    .replace("{{education_location}}", jsonData.getEducation().get(i).getLocation())
                    .replace("{{education_studyType}}", jsonData.getEducation().get(i).getStudyType())
                    .replace("{{education_area}}", jsonData.getEducation().get(i).getArea())
                    .replace("{{education_startDate}}", jsonData.getEducation().get(i).getStartDate())
                    .replace("{{education_endDate}}", jsonData.getEducation().get(i).getEndDate());
            sb.append(educationElement);
        }
        return sb.toString();
    }

    private String buildPersonalDataSection(String personalDataHtml) {
        return personalDataHtml.replace("{{personal_data_name}}", jsonData.getPersonal_data().getName())
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
