package forfait;

import etudiant.Etudiant;

public abstract class Forfait<T> {
	private int nbrLivreMensuelAutorise;

	public Forfait(int nbrLivreMensuelAutorise) {
		this.nbrLivreMensuelAutorise = nbrLivreMensuelAutorise;
	}
	
	public int getNbrLivreMensuelAutorise() {
		return nbrLivreMensuelAutorise;
	}

	public void setNbrLivreMensuelAutorise(int nbrLivreMensuelAutorise) {
		this.nbrLivreMensuelAutorise = nbrLivreMensuelAutorise;
	}
	
	public abstract Etudiant increaseNbrLivreMensuelAutorise(Etudiant etudiant);
}

