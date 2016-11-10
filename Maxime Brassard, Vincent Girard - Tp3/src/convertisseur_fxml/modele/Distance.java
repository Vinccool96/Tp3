package convertisseur_fxml.modele;

public class Distance implements Convertir{
	@Override
	public double setVersBase(double value, Object type) {
		return value * ((TypeDistance) type).getUniteVersBase();
	}

	@Override
	public double getVersUnite(double value, Object type) {
		return value * ((double) 1 / ((TypeDistance) type).getUniteVersBase());
	}
}
