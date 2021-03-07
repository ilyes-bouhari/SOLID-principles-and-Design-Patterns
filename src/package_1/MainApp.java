package package_1;

public class MainApp {

	public static void main(String[] args) {

		EtudiantService studentService = new EtudiantService();
		
		try {
			studentService.inscription(1, "Doe", "John", "johndoe@gmail.com", "password", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
