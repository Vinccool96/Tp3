package convertisseur_fxml.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TypeVolume {
	LITRE("litre (l)", 1), MILILITRE("millilitre (ml)", 0.001), MCUBE("mètre cube (m2)",
			1000), CMCUBE("centimètre Cube (cm2)", 0.001), USOUNCE("ounce américaine (fl oz)", 453.59237);

	private String texte = null;
	// la base est le litre
	private double uniteVersBase = 0;

	private TypeVolume(String s, double f) {
		texte = s;
		uniteVersBase = f;
	}

	public String toString() {
		return texte;
	}

	public double getUniteVersBase() {
		return uniteVersBase;
	}

	public static ObservableList<Enum> getListeTypeVolume() {
		ObservableList<Enum> liste = FXCollections.observableArrayList();

		for (TypeVolume tp : TypeVolume.values()) {
			liste.add(tp);
		}

		return liste;
	}
}
