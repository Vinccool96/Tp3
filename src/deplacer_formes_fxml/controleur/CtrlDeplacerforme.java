package deplacer_formes_fxml.controleur;

import deplacer_formes_fxml.modele.Deplacement;
import deplacer_formes_fxml.modele.Direction;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlDeplacerforme extends Application {

	@FXML
	private ImageView imageView;
	private Pane root;

	private Direction direction;
	private Deplacement deplacement;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/deplacer_formes_fxml/vue/VueDeplacerForme.fxml"));
		root = loader.load();
		imageView = (ImageView) root.getChildren().get(0);
		Scene scene = new Scene(root);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EcouteurDeplacement());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

	private class EcouteurDeplacement implements EventHandler<KeyEvent> {

		@SuppressWarnings("incomplete-switch")
		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			switch (event.getCode()) {
			case DOWN:
				direction = Direction.BAS;
				deplacement = Deplacement.ACTIF;
				move();
				break;
			case ESCAPE:
				direction = Direction.AUCUN;
				deplacement = Deplacement.INACTIF;
				move();
				break;
			case LEFT:
				direction = Direction.HAUT;
				deplacement = Deplacement.ACTIF;
				move();
				break;
			case RIGHT:
				direction = Direction.DROITE;
				deplacement = Deplacement.ACTIF;
				move();
				break;
			case UP:
				direction = Direction.HAUT;
				deplacement = Deplacement.ACTIF;
				move();
				break;
			}

		}
	}

	@SuppressWarnings("incomplete-switch")
	private void move() {
		Path path = new Path();
		PathTransition pathTransition = new PathTransition();

		path = new Path();
		pathTransition = new PathTransition();
		switch (direction) {
		case BAS:
			if (imageView.getTranslateY() >= 210 - 30) {
				deplacement = Deplacement.INACTIF;
				direction = Direction.AUCUN;
				path = null;
			} else {
				path.getElements().add(new MoveTo(imageView.getTranslateX(), imageView.getTranslateY() + 90));
			}
			break;
		case DROITE:
			if (imageView.getTranslateX() >= 210 - 30) {
				deplacement = Deplacement.INACTIF;
				direction = Direction.AUCUN;
				path = null;
			} else {
				path.getElements().add(new MoveTo(imageView.getTranslateX() + 90, imageView.getTranslateY()));
			}
			break;
		case GAUCHE:
			if (imageView.getTranslateX() <= 0) {
				deplacement = Deplacement.INACTIF;
				direction = Direction.AUCUN;
				path = null;
			} else {
				path.getElements().add(new MoveTo(imageView.getTranslateX() - 90, imageView.getTranslateY()));
			}
			break;
		case HAUT:
			if (imageView.getTranslateY() <= 0) {
				deplacement = Deplacement.INACTIF;
				direction = Direction.AUCUN;
				path = null;
			} else {
				path.getElements().add(new MoveTo(imageView.getTranslateX(), imageView.getTranslateY() - 90));
			}
			break;
		}
		path.getElements().add(new ClosePath());
		root.getChildren().add(path);
		pathTransition.setDuration(Duration.seconds(1));
		pathTransition.setNode(imageView);
		pathTransition.setPath(path);
		pathTransition.setInterpolator(Interpolator.LINEAR);
		pathTransition.setCycleCount(1);
		pathTransition.play();
		root.getChildren().remove(path);

	}
}
