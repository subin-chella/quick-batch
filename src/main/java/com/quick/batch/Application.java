package com.quick.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableBatchProcessing
public class Application implements CommandLineRunner {
	@Value("${spring.batch.job.enabled}")
	private String userBucketPath;
	
	
	
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... arg0) throws Exception {
       System.out.println("Hello world from Command Line Runner"+userBucketPath);
    } 
    

}
