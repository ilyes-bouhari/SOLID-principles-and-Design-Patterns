package demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import database.MySQLConnection;
import etudiant.EtudiantRepository;
import etudiant.EtudiantService;
import universite.UniversiteRepository;

public class DemoTests {

	@Test
	public void a_user_can_register_a_student() throws SQLException {
		EtudiantService studentService = new EtudiantService();
		
		boolean reponse = studentService.inscription(1, "Doe", "John", "johndoe@gmail.com", "password", 1, new EtudiantRepository(new MySQLConnection()), new UniversiteRepository(new MySQLConnection()));

		assertTrue(reponse);
	}
}

