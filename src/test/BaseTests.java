package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import database.MySQLConnection;
import package_1.EtudiantRepository;
import package_1.EtudiantService;
import package_1.UniversiteRepository;

public class BaseTests {
	
	@Test
	public void a_user_can_register_a_student() throws SQLException {
		EtudiantService studentService = new EtudiantService();
		
		boolean reponse = studentService.inscription(1, "Doe", "John", "johndoe@gmail.com", "password", 1, new EtudiantRepository(new MySQLConnection()), new UniversiteRepository(new MySQLConnection()));

		assertEquals(true, reponse);
	}
}

