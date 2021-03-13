package com.tp2.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tp2.di.AppConfig;
import com.tp2.etudiant.EtudiantService;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
	
	@Autowired
	private EtudiantService etudiantService;
		
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		etudiantService.inscription(1, "Doe", "John", "johndoe@gmail.com", "password", 1);
		etudiantService.increase_NbLivreMensuel_Autorise(1);
	}
}
