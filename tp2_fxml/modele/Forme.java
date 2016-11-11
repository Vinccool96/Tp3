package tp2_fxml.modele;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author 1537391
 */
public abstract class Forme {
	public static final int MIN_VAL = 1;
	public static final int MAX_VAL = 2000;
	public static Color COULEUR_DEFAUT = Color.RED;

	private double positionX = 0;
	private double positionY = 0;
	private Color couleur = COULEUR_DEFAUT;
	protected Shape shape;
	protected String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Forme() {

	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Forme(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return la couleur
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur
	 *            la couleur à setter
	 */
	public void setCouleur(Color couleur) {
		if (couleur != null) {
			this.couleur = couleur;
		}
	}

	protected abstract Shape getShape();

	protected abstract void setShape();

}