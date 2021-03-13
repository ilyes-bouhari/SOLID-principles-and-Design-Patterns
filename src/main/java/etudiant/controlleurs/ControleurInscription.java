package etudiant.controlleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import base.IView;
import database.MySQLConnection;
import etudiant.Etudiant;
import etudiant.EtudiantRepository;
import etudiant.EtudiantService;
import universite.UniversiteRepository;

public class ControleurInscription {
	
	private IView<Etudiant> _view;
	
	public ControleurInscription(IView<Etudiant> view) {
		this._view = view;
	}
	
	public void initActionListeners() {
		_view.getBtnCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		_view.getBtnSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
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
			
			Etudiant student = _view.fillObject();
			
			studentService.inscription(
				student.getId(), 
				student.getNom(), 
				student.getPrenom(), 
				student.getEmail(), 
				student.getPwd(), 
				student.getId_universite(),
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
