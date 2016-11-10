package convertisseur_fxml.modele;

public class Volume implements Convertir {

	@Override
	public double setVersBase(double value, Object type) {
		return value * ((TypeVolume) type).getUniteVersBase();
	}

	@Override
	public double getVersUnite(double value, Object type) {
		return value * ((double) 1 / ((TypeVolume) type).getUniteVersBase());
	}
}
