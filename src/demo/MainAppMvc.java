package demo;

import base.IView;
import etudiant.Etudiant;
import etudiant.controlleurs.ControleurInscription;
import etudiant.views.ViewInscription;

public class MainAppMvc {

	public static void main(String[] args) {
		
		IView<Etudiant> signup_view = new ViewInscription();
		
		ControleurInscription signup_controller = new ControleurInscription(signup_view);
		signup_controller.show();
	}
}
