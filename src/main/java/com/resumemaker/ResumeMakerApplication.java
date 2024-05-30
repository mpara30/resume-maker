package com.resumemaker;

import com.resumemaker.service.ResumeGeneratorService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeMakerApplication implements CommandLineRunner {
	@Resource
	ResumeGeneratorService resumeGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(ResumeMakerApplication.class, args);
	}
	@Override
	public void run(String... args) {
		resumeGeneratorService.generateResume();
	}

}
