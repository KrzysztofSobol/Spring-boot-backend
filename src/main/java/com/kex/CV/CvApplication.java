package com.kex.CV;

import lombok.extern.java.Log;
import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Log
public class CvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvApplication.class, args);
	}
}
