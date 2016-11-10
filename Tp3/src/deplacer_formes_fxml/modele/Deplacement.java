package deplacer_formes_fxml.modele;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Deplacement extends Thread {
	public boolean enDeplacement = false;
	public Direction direction = Direction.AUCUNE;
	public DoubleProperty positionX = new SimpleDoubleProperty();
	public DoubleProperty positionY = new SimpleDoubleProperty();
	public ImageView imageView;
	private Scene scene;

	private TranslateTransition translateTransition = new TranslateTransition();

	@SuppressWarnings("incomplete-switch")
	private void justDoIt() {
		if (direction != Direction.AUCUNE) {
			switch (direction) {
			case BAS:
				if (positionY.get() >= scene.getHeight() - 30) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					translateTransition.setToY(positionY.get() + 1);
				}
				break;
			case DROITE:
				if (positionX.get() >= scene.getHeight() - 30) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					translateTransition.setToX(positionX.get() + 1);
				}
				break;
			case GAUCHE:
				if (positionX.get() <= 0) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					translateTransition.setToX(positionX.get() - 1);
				}
				break;
			case HAUT:
				if (positionY.get() <= 0) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					translateTransition.setToY(positionY.get() - 1);

				}
				break;
			}
			translateTransition.setNode(imageView);

			translateTransition.setDuration(Duration.millis(25));
			translateTransition.setInterpolator(Interpolator.LINEAR);
			translateTransition.setCycleCount(1);
			translateTransition.setAutoReverse(false);
			translateTransition.play();
		}
	}

	private void done() {
		if (direction == Direction.BAS) {
			justDoIt();
		} else if (direction == Direction.DROITE) {
			justDoIt();
		} else if (direction == Direction.GAUCHE) {
			justDoIt();
		} else if (direction == Direction.HAUT) {
			justDoIt();
		}
	}

	@Override
	public void run() {
		if (!isAlive()) {
			justDoIt();
		}
	}

	public Deplacement(Direction direction, ImageView image, Scene scene) {
		setDirection(direction);
		this.imageView = image;
		this.scene = scene;
		positionX.set(90);
		positionY.set(90);
		positionX.bindBidirectional(imageView.translateXProperty());
		positionY.bindBidirectional(imageView.translateYProperty());
		translateTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				done();
			}
		});
		run();
	}

	public boolean isEnDeplacement() {
		return enDeplacement;
	}

	public void setDeplacement(boolean deplacement) {
		this.enDeplacement = deplacement;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
