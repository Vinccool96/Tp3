package tp2_fxml.modele;

import javafx.scene.paint.Color;

public class DataFactory {
	private String nom = null;
	private Color couleur = null;
	private double positionX = 0;
	private double positionY = 0;
	private double largeur = 0;
	private double hauteur = 0;
	private double coteC = 0;

	public DataFactory(String nom, Color couleur, double positionX, double positionY, double largeur, double hauteur,
			double coteC) {
		setNom(nom);
		setCouleur(couleur);
		setPositionX(Math.abs(positionX));
		setPositionY(Math.abs(positionY));
		setLargeur(Math.abs(largeur));
		setHauteur(Math.abs(hauteur));
		if (nom.equals("Triangle")) {
			setCoteC(Math.abs(coteC));
		} else {
			setCoteC(1);
		}
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String forme) {
		this.nom = forme;
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public double getPositionX() {
		return this.positionX;
	}

	public void setPositionX(double aPositionX) {
		this.positionX = aPositionX;
	}

	public double getPositionY() {
		return this.positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getLargeur() {
		return this.largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public double getCoteC() {
		return this.coteC;
	}

	public void setCoteC(double coteC) {
		this.coteC = coteC;
	}

}