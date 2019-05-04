package br.com.appProposta;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropostaCreditoApplication {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(PropostaCreditoApplication.class, args);
	}
}
