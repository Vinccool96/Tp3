package tp2_fxml.modele;

import javafx.scene.shape.Ellipse;
import tp2_fxml.exception.FormeException;

public class Ovale extends Forme {

	private double largeur = MIN_VAL;
	private double hauteur = MIN_VAL;

	/**
	 * méthode construisant un objet de type Cercle avec un rayon valide, tout
	 * en lui assignant une couleur par défaut.
	 */
	public Ovale(double largeur, double hauteur) throws FormeException {
		boolean ok = validerEllipse(largeur, hauteur);
		if (ok) {
			setNom("Ovale");
			setDimensions(largeur, hauteur);
			setShape();
		} else {
			throw new FormeException();
		}
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setDimensions(double largeur, double hauteur) {
		setLargeur(largeur);
		setHauteur(hauteur);
	}

	public Ellipse getShape() {
		return (Ellipse) shape;
	}

	public void setShape() {
		this.shape = new Ellipse(largeur, hauteur);
		((Ellipse) this.shape).setCenterX(getLargeur() / 2);
		((Ellipse) this.shape).setCenterY(getHauteur() / 2);
	}

	/**
	 * Vérifie si le rayon est valide, soit entre 1 et 30 inclus
	 * 
	 * @param largeur
	 *            double, le rayon voulant être appliqué devant être vérifié
	 * @return true le rayon est valide
	 */
	private static boolean validerEllipse(double largeur, double hauteur) {
		return ((MIN_VAL <= largeur) && (largeur <= MAX_VAL)) && ((MIN_VAL <= hauteur) && (hauteur <= MAX_VAL));
	}

}