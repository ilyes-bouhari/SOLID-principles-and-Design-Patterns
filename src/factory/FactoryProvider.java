package factory;

public class FactoryProvider {
	
	public static AbstractFactory<?> getFactory(String factory) {
		
		if ("Forfait".equalsIgnoreCase(factory)) {
			return new ForfaitFactory();
		}
		
		return null;
	}
}
