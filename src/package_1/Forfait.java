package package_1;

public abstract class Forfait {
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

