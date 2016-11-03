package deplacer_formes_fxml.controleur;

import deplacer_formes_fxml.modele.Deplacement;
import deplacer_formes_fxml.modele.Direction;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CtrlDeplacerforme extends Application {

	@FXML
	private ImageView imageView;
	private Pane root;

	private Direction direction;
	private Direction directionMouvement = Direction.BAS;
	private Deplacement deplacement;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/deplacer_formes_fxml/vue/VueDeplacerForme.fxml"));
		root = loader.load();
		imageView = (ImageView) root.getChildren().get(0);
		deplacement = new Deplacement(Direction.AUCUNE, imageView);
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
			imageView.translateXProperty().bindBidirectional(deplacement.positionX);
			imageView.translateYProperty().bindBidirectional(deplacement.positionY);
			// TODO Auto-generated method stub
			switch (event.getCode()) {
			case DOWN:
				deplacement.interrupt();
				direction = Direction.BAS;
				directionMouvement = Direction.BAS;
				imageView.setImage(new Image("images/koala_down.gif"));
				break;
			case ESCAPE:
				deplacement.interrupt();
				direction = Direction.AUCUNE;
				switch (directionMouvement) {
				case BAS:
					imageView.setImage(new Image("images/koala_stand_down.JPG"));
					break;
				case DROITE:
					imageView.setImage(new Image("images/koala_stand_right.JPG"));
					break;
				case GAUCHE:
					imageView.setImage(new Image("images/koala_stand_left.JPG"));
					break;
				case HAUT:
					imageView.setImage(new Image("images/koala_stand_up.JPG"));
					break;
				}
				break;
			case LEFT:
				deplacement.interrupt();
				direction = Direction.GAUCHE;
				directionMouvement = Direction.GAUCHE;
				imageView.setImage(new Image("images/koala_left.gif"));
				break;
			case RIGHT:
				deplacement.interrupt();
				direction = Direction.DROITE;
				directionMouvement = Direction.DROITE;
				imageView.setImage(new Image("images/koala_right.gif"));
				break;
			case UP:
				deplacement.interrupt();
				direction = Direction.HAUT;
				directionMouvement = Direction.HAUT;
				imageView.setImage(new Image("images/koala_up.gif"));
				break;
			}
			deplacement.setDirection(direction);
			deplacement.run();
		}
	}

}
