package package_1;

public class MainApp {

	public static void main(String[] args) {

		EtudiantService studentService = new EtudiantService();
		
		try {
			studentService.inscription(
				1, "Doe", "John", "johndoe@gmail.com", "password", 1, // student info
				new EtudiantRepository(new MySQLConnection()), // student repository with database connection
				new UniversiteRepository(new MySQLConnection()) // university repository with database connection
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
