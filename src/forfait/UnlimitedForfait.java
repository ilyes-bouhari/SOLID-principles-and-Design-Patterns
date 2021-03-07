package forfait;

import package_1.Etudiant;

public class UnlimitedForfait extends Forfait {

	public UnlimitedForfait(int nbrLivreMensuelAutorise) {
		super(nbrLivreMensuelAutorise);
	}

	@Override
	public Etudiant increaseNbrLivreMensuelAutorise(Etudiant etudiant) {
		return etudiant;
	}
}
