package tp2_fxml.modele;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import tp2_fxml.exception.FormeException;

public class Triangle extends Forme {
	private double coteA = 0;
	private double coteB = 0;
	private double coteC = 0;

	private Point2D pointA;
	private Point2D pointB;
	private Point2D pointC;

	public Triangle(double a, double b, double c) throws FormeException {
		if (estTriangle(a, b, c) && validerTriangle(a, b, c)) {
			setNom("Triangle");
			coteA = a;
			coteB = b;
			coteC = c;
			setShape();
		} else {
			throw new FormeException();
		}
	}

	private static boolean estTriangle(double a, double b, double c) {
		boolean realy = false;

		double[] table = { a, b, c };
		int n = table.length - 1;

		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= i; j++)
				if (table[j - 1] > table[j]) {
					double temp = table[j - 1];
					table[j - 1] = table[j];
					table[j] = temp;
				}
		}

		if ((table[2]) < ((table[0]) + (table[1]))) {

			realy = true;

		}

		return realy;

	}

	private static boolean validerTriangle(double a, double b, double c) {
		boolean realy = false;

		if (((MIN_VAL <= a) && (a <= MAX_VAL)) && ((MIN_VAL <= b) && (b <= MAX_VAL))
				&& ((MIN_VAL <= c) && (c <= MAX_VAL))) {
			realy = true;
		}

		return realy;
	}

	/**
	 * @return coteA
	 */
	public double getCoteA() {
		return coteA;
	}

	/**
	 * @return coteB
	 */
	public double getCoteB() {
		return coteB;
	}

	/**
	 * @return coteC
	 */
	public double getCoteC() {
		return coteC;
	}

	public Polygon getShape() {
		return (Polygon) shape;
	}

	public void setShape() {
		this.shape = new Polygon();
		setPointA();
		setPointB();
		setPointC();
		((Polygon) shape).getPoints().addAll(getPointA().getX(), getPointA().getY());
		((Polygon) shape).getPoints().addAll(getPointB().getX(), getPointB().getY());
		((Polygon) shape).getPoints().addAll(getPointC().getX(), getPointC().getY());
	}

	public Point2D getPointA() {
		return pointA;
	}

	public void setPointA() {
		this.pointA = new Point2D(0, 0);
	}

	public Point2D getPointB() {
		return pointB;
	}

	public void setPointB() {
		this.pointB = new Point2D(getCoteA(), 0);
	}

	public Point2D getPointC() {
		return pointC;
	}

	public double area() {
		double p = (getCoteA() + getCoteB() + getCoteC()) / 2;
		return Math.sqrt(p * (p - getCoteA()) * (p - getCoteB()) * (p - getCoteC()));
	}

	public void setPointC() {
		double xC = (getCoteC() * getCoteC() - getCoteB() * getCoteB() + getCoteA() * getCoteA()) / (2 * getCoteA());
		double yC = Math.sqrt(getCoteC() * getCoteC() - xC * xC);
		this.pointC = new Point2D(xC, yC);
	}
}