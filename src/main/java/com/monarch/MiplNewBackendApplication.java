package com.monarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.monarch.domain.candidate.FileStorageProperties;


@SpringBootApplication


@EnableConfigurationProperties({
    FileStorageProperties.class
})
@ComponentScan({"com.monarch", "com.monarch.service"})
public class MiplNewBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiplNewBackendApplication.class, args);
	}
}
