package convertisseur_fxml.modele;

public class Poids implements Convertir {

	@Override
	public double setVersBase(double value, Object type) {
		return value * ((TypePoids) type).getUniteVersBase();
	}

	@Override
	public double getVersUnite(double value, Object type) {
		return value * ((double) 1 / ((TypePoids) type).getUniteVersBase());
	}
}
