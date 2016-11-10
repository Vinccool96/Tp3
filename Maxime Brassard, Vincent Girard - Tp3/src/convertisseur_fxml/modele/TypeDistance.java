package convertisseur_fxml.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TypeDistance {

	METRE("mètre (m)", 1), KILOMETRE("Kilomètre (Km)", 1000), MILLES(
			"milles (mi)", 1609.344), FOOT("foot (ft)", 0.3048), YARD(
			"yard (yd)", 0.9144);

	private String texte = null;
	// la base est le metre
	private double uniteVersBase = 0;

	private TypeDistance(String s, double f) {
		texte = s;
		uniteVersBase = f;
	}

	public String toString() {
		return texte;
	}

	public double getUniteVersBase() {
		return uniteVersBase;
	}

	public static ObservableList<Enum> getListeTypeDistance() {
		ObservableList<Enum> liste = FXCollections.observableArrayList();

		for (TypeDistance tp : TypeDistance.values()) {
			liste.add(tp);
		}

		return liste;
	}
}