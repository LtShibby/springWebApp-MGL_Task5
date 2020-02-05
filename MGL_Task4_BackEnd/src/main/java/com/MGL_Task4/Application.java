package com.MGL_Task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.MGL_Task4.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "com.MGL_Task4" })
@ComponentScan("com.MGL_Task4")
@EntityScan("com.MGL_Task4.model")
public class Application {
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
