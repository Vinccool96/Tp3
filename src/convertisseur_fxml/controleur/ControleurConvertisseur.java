package convertisseur_fxml.controleur;

import convertisseur_fxml.modele.Convertir;
import convertisseur_fxml.modele.Poids;
import convertisseur_fxml.modele.TypePoids;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControleurConvertisseur extends Application {

	private BorderPane root = null;
	private Scene scene = null;
	private Convertir modele = null;

	@FXML
	private RadioButton poids;
	@FXML
	private RadioButton volume;
	@FXML
	private RadioButton distance;
	@FXML
	private ComboBox<TypePoids> listeGauche;
	@FXML
	private ComboBox<TypePoids> listeDroite;
	@FXML
	private TextField valeurDroite;
	@FXML
	private TextField valeurGauche;

	public ControleurConvertisseur() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/convertisseur_fxml/vue/VueConvertisseur.fxml"));
		// Chargement du fichier FXML
		root = loader.load();
		scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Convertisseur...");
		primaryStage.show();
	}

	@FXML
	private void initialize() {
		initPoids();
		poids.setSelected(true);
	}

	private void initPoids() {
		modele = new Poids();
		listeGauche.setItems(TypePoids.getListeTypePoids());
		listeGauche.getSelectionModel().select(0);
		listeDroite.setItems(TypePoids.getListeTypePoids());
		listeDroite.getSelectionModel().select(1);
		valeurGauche.setText("0");
		valeurDroite.setText("0");
	}

	private void initVolume() {
		System.out.println("volume");
	}

	private void initDistance() {
		System.out.println("distance");
	}

	@FXML
	public void changerTypeConvertion(ActionEvent event) {
		if (event.getSource() == poids) {
			initPoids();
		} else if (event.getSource() == volume) {
			initVolume();
		} else if (event.getSource() == distance) {
			initDistance();
		}
	}

	@FXML
	private void changerListeGauche() {
		try {
			if ((listeGauche.getValue() != null) && (listeDroite.getValue() != null)) {
				valeurGauche.setText(modele.getVersUnite(
						modele.setVersBase(Double.parseDouble(valeurDroite.getText()), listeDroite.getValue()),
						listeGauche.getValue()) + "");
			}
		} catch (NumberFormatException e) {
		}
	}

	@FXML
	private void editerValeurGauche(KeyEvent event) {
		/*
		 * try {
		 * 
		 * valeurDroite.setText(modele.getVersUnite(modele.setVersBase(
		 * Double.parseDouble(valeurGauche.getText()), listeGauche.getValue()),
		 * listeDroite.getValue()) + ""); } catch (NumberFormatException e) { }
		 */
	}

	@FXML
	private void changerListeDroite() {
		try {
			if ((listeGauche.getValue() != null) && (listeDroite.getValue() != null)) {
				valeurDroite.setText(modele.getVersUnite(
						modele.setVersBase(Double.parseDouble(valeurGauche.getText()), listeGauche.getValue()),
						listeDroite.getValue()) + "");
			}
		} catch (NumberFormatException e) {
		}
	}

	@FXML
	private void editerValeurDroite(KeyEvent event) {
		/*
		 * try { valeurGauche.setText(modele.getVersUnite(modele.setVersBase(
		 * Double.parseDouble(valeurDroite.getText()), listeDroite.getValue()),
		 * listeGauche.getValue()) + ""); } catch (NumberFormatException e) { }
		 */
	}

	public static void main(String[] args) {
		launch(args);
	}

}
