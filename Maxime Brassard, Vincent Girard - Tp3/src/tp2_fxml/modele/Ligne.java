package tp2_fxml.modele;

import javafx.scene.shape.Line;
import tp2_fxml.exception.FormeException;

public class Ligne extends Forme {

	private double endX;
	private double endY;

	public Ligne(double startX, double startY, double endX, double endY) throws FormeException {
		super(startX, startY);
		if (validerLine(startX, startY, endX, endY)) {
			setNom("Ligne");
			setEndX(endX);
			setEndY(endY);
			setShape();
		} else {
			throw new FormeException();
		}
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = getPositionX() + endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = getPositionY() + endY;
	}

	@Override
	public Line getShape() {
		return (Line) this.shape;
	}

	@Override
	public void setShape() {
		this.shape = new Line(this.getPositionX(), this.getPositionY(), this.getEndX(), this.getEndY());
	}

	private boolean validerLine(double startX, double startY, double endX, double endY) {
		return (MIN_VAL <= (Math.abs(endX - startX))) && (MIN_VAL <= (Math.abs(endY - startY)))
				&& ((Math.abs(endX - startX)) <= MAX_VAL) && ((Math.abs(endY - startY)) <= MAX_VAL);
	}
}