package tp2_fxml.modele;

import javafx.scene.shape.Shape;
import tp2_fxml.controleur.ControleurForme;
import tp2_fxml.exception.FormeException;

public class FormesFactory {
	public double _mIN_ZONE = 400;
	public Object _mAX_ZONE = 500;
	public Forme forme;
	public ControleurForme _unnamed_ControleurForme_;
	private Shape shape;

	public FormesFactory(DataFactory dF) {
		this.forme = getInstance(dF);
		this.shape = this.forme.getShape();
		this.shape.setFill(dF.getCouleur());

		if (!dF.getNom().equals("Ligne")) {
			this.shape.setTranslateX(dF.getPositionX());
			this.shape.setTranslateY(dF.getPositionY());
		}

	}

	public Forme getInstance(DataFactory dF) {
		Forme forme = null;
		try {
			if (dF.getNom().equals("Ovale")) {
				forme = new Ovale(dF.getLargeur(), dF.getHauteur());
			} else if (dF.getNom().equals("Rectangle")) {
				forme = new Rect(dF.getLargeur(), dF.getHauteur());
			} else if (dF.getNom().equals("Triangle")) {
				forme = new Triangle(dF.getLargeur(), dF.getHauteur(), dF.getCoteC());
			} else if (dF.getNom().equals("Ligne")) {
				forme = new Ligne(dF.getPositionX(), dF.getPositionY(), dF.getLargeur(), dF.getHauteur());
			}
		} catch (FormeException e) {
			e.printStackTrace();
		}
		return forme;

	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}