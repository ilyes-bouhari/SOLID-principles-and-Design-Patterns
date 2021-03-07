package package_1;

import java.sql.SQLException;

public class ControleurInscription {
	
	private ViewInscription _view;
	
	public ControleurInscription(ViewInscription view) {
		this._view = view;
	}
	
	public void initActionListeners() {
		_view.getBtnCancel().addActionListener(e -> cancel());
		_view.getBtnSubmit().addActionListener(e -> submit());
	}
	
	public void show() {
		_view.show();
		initActionListeners();
	}
	
	public void submit() {
		if (! _view.validate()) return;

		// 1. instantiate student service
		EtudiantService studentService = new EtudiantService();
		
		// 2. call student service inscription method
		try {
					
			studentService.inscription(
				_view.Get_Mat(), 
				_view.Get_Nom(), 
				_view.Get_Prenom(), 
				_view.Get_email(), 
				_view.Get_pwd(), 
				_view.Get_id_univ(),
				new EtudiantRepository(new MySQLConnection()),
				new UniversiteRepository(new MySQLConnection())
			);
			
			_view.reset();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel() {
		System.exit(0);
	}
}
