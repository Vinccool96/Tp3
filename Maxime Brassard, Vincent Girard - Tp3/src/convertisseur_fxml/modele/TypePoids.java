package convertisseur_fxml.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TypePoids {

	KILOGRAMME("kilogramme (Kg)", 1000), GRAMME("gramme (g)", 1), MILLIGRAMME("milligramme (ml)",
			.001), OUNCE("ounce (oz)", 28.2495231), LIVRE("livre (lb)", 453.59237);

	private String texte = null;
	// la base est le gramme
	private double uniteVersBase = 0;

	private TypePoids(String s, double f) {
		texte = s;
		uniteVersBase = f;
	}

	public String toString() {
		return texte;
	}

	public double getUniteVersBase() {
		return uniteVersBase;
	}

	public static ObservableList<Enum> getListeTypePoids() {
		ObservableList<Enum> liste = FXCollections.observableArrayList();

		for (TypePoids tp : TypePoids.values()) {
			liste.add(tp);
		}

		return liste;
	}
}
