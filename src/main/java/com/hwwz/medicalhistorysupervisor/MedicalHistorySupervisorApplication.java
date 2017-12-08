package com.hwwz.medicalhistorysupervisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.hwwz.medicalhistorysupervisor"})

public class MedicalHistorySupervisorApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MedicalHistorySupervisorApplication.class);
	}

=======

@SpringBootApplication
public class MedicalHistorySupervisorApplication {
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f

	public static void main(String[] args) {
		SpringApplication.run(MedicalHistorySupervisorApplication.class, args);
	}
}
