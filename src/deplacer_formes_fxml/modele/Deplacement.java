package deplacer_formes_fxml.modele;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Deplacement extends Thread {
	public boolean enDeplacement = false;
	public Direction direction = Direction.AUCUNE;
	public DoubleProperty positionX = new SimpleDoubleProperty();
	public DoubleProperty positionY = new SimpleDoubleProperty();
	public ImageView imageView;
	TranslateTransition pathTransition = new TranslateTransition();

	@SuppressWarnings("incomplete-switch")
	private void justDoIt() {
		if (direction != Direction.AUCUNE) {
			switch (direction) {
			case BAS:
				if (positionY.get() >= 210 - 30) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					pathTransition.setToY(positionY.get() + 1);
				}
				break;
			case DROITE:
				if (positionX.get() >= 210 - 30) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					pathTransition.setToX(positionX.get() + 1);
				}
				break;
			case GAUCHE:
				if (positionX.get() <= 0) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					pathTransition.setToX(positionX.get() - 1);
				}
				break;
			case HAUT:
				if (positionY.get() <= 0) {
					enDeplacement = false;
					direction = Direction.AUCUNE;
				} else {
					pathTransition.setToY(positionY.get() - 1);

				}
				break;
			}
			pathTransition.setNode(imageView);

			pathTransition.setDuration(Duration.millis(25));
			pathTransition.setInterpolator(Interpolator.LINEAR);
			pathTransition.setCycleCount(1);
			pathTransition.setAutoReverse(false);
			pathTransition.play();
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

	public Deplacement(Direction direction, ImageView image) {
		setDirection(direction);
		this.imageView = image;
		positionX.set(90);
		positionY.set(90);
		positionX.bindBidirectional(imageView.translateXProperty());
		positionY.bindBidirectional(imageView.translateYProperty());
		pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
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
