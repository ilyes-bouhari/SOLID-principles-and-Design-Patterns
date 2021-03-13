package forfait;

import etudiant.Etudiant;

public class UnlimitedForfait extends Forfait {

	public UnlimitedForfait(int nbrLivreMensuelAutorise) {
		super(nbrLivreMensuelAutorise);
	}

	@Override
	public Etudiant increaseNbrLivreMensuelAutorise(Etudiant etudiant) {
		return etudiant;
	}
}
