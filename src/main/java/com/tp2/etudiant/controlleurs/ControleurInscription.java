package com.tp2.etudiant.controlleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp2.etudiant.Etudiant;
import com.tp2.etudiant.EtudiantService;
import com.tp2.etudiant.views.ViewInscription;

@Component
public class ControleurInscription {
		
	@Autowired
	private ViewInscription viewInscription;
	
	@Autowired
	private Etudiant etudiant;
	
	@Autowired
	private EtudiantService etudiantService;
	
	
	public void initActionListeners() {
		viewInscription.getBtnCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		viewInscription.getBtnSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
	}
	
	public void show() {
		viewInscription.show();
		initActionListeners();
	}
	
	public void submit() {
		if (! viewInscription.validate()) return;

		try {
			
			etudiant = viewInscription.fillObject();
			
			etudiantService.inscription(
				etudiant.getId(), 
				etudiant.getNom(), 
				etudiant.getPrenom(), 
				etudiant.getEmail(), 
				etudiant.getPwd(), 
				etudiant.getId_universite()
			);
			
			viewInscription.reset();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel() {
		System.exit(0);
	}
}
