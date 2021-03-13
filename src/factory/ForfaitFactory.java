package factory;

import forfait.*;

public class ForfaitFactory implements AbstractFactory<Forfait> {

	@Override
	public Forfait create(String factory) {
		
		if ("Standard".equalsIgnoreCase(factory)) {
			return new StandardForfait(10, 5);
		} else if ("Premium".equalsIgnoreCase(factory)) {
			return new PremiumForfait(20, 10);
		} else if ("Unlimited".equalsIgnoreCase(factory)) {
			return new UnlimitedForfait(0);
		}
				
		return null;
	}

}
