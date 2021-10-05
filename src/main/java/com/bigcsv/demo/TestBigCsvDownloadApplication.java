package com.bigcsv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//StreamingResponseBody example from here:
//https://medium.com/swlh/streaming-data-with-spring-boot-restful-web-service-87522511c071
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bigcsv.demo.repository")
@EnableTransactionManagement
public class TestBigCsvDownloadApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestBigCsvDownloadApplication.class);
    }

	
	public static void main(String[] args) {
		SpringApplication.run(TestBigCsvDownloadApplication.class, args);
	}
	

}
