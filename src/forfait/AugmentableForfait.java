package forfait;

import package_1.Etudiant;

public abstract class AugmentableForfait extends Forfait {

	private int _increaseBy;

	public AugmentableForfait(int nbrLivreMensuelAutorise, int increaseBy) {
		super(nbrLivreMensuelAutorise);
		
		this._increaseBy = increaseBy;
	}
	
	public int getIncreaseBy() {
		return _increaseBy;
	}

	public void setIncreaseBy(int increaseBy) {
		this._increaseBy = increaseBy;
	}

	@Override
	public Etudiant increaseNbrLivreMensuelAutorise(Etudiant etudiant) {
		etudiant.increase_NbLivreMensuel_Autorise(getIncreaseBy());
		
		return etudiant;
	}
}
