package tp2_fxml.controleur;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tp2_fxml.modele.DataFactory;
import tp2_fxml.modele.FormesFactory;

public class ControleurForme extends Application {

	private boolean firstTime = true;

	public FormesFactory frmFact;
	private boolean started = false;
	private ActionStart start = new ActionStart();
	private String name;
	private ActionButton ecouteurButton = new ActionButton();
	private ActionForme ecouteurForme = new ActionForme();
	private ActionEffet ecouteurEffet = new ActionEffet();

	@FXML
	private Button buttonGenerer;
	@FXML
	private Button buttonReinitialiser;
	@FXML
	private Button buttonQuitter;

	@FXML
	private Scene scene;

	@FXML
	private BorderPane root;
	@FXML
	private HBox hbBottom;
	@FXML
	private StackPane pn;
	@FXML
	private ListView<TextFlow> listFormes;
	@FXML
	private ColorPicker theChosenOne;
	@FXML
	private CheckBox chkBxEffet;
	@FXML
	private TextField txtFldPositionX;
	@FXML
	private TextField txtFldPositionY;
	@FXML
	private TextField txtFldCoteA;
	@FXML
	private TextField txtFldCoteB;
	@FXML
	private TextField txtFldCoteC;
	@FXML
	private Slider io;

	@FXML
	private Label lbX;
	@FXML
	private Label lbY;
	@FXML
	private Label lbA;
	@FXML
	private Label lbB;

	@Override
	public void start(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/tp2_fxml/vue/VueTp2.fxml"));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		generer();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Dessin de formes");
		Text txt1 = new Text("Ovale");
		Text txt2 = new Text("Rectangle");
		Text txt3 = new Text("Triangle");
		Text txt4 = new Text("Ligne");

		TextFlow lb1 = new TextFlow(txt1);
		TextFlow tf2 = new TextFlow(txt2);
		TextFlow tf3 = new TextFlow(txt3);
		TextFlow tf4 = new TextFlow(txt4);

		ObservableList<TextFlow> ol = FXCollections.observableArrayList(lb1, tf2, tf3, tf4);

		listFormes.setItems(ol);
		listFormes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		listFormes.getItems().get(0).setOnMouseClicked(ecouteurForme);
		listFormes.getItems().get(1).setOnMouseClicked(ecouteurForme);
		listFormes.getItems().get(2).setOnMouseClicked(ecouteurForme);
		listFormes.getItems().get(3).setOnMouseClicked(ecouteurForme);

		stage.addEventHandler(WindowEvent.WINDOW_SHOWING, start);
		stage.show();

	}

	private void generer() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public class ActionStart implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if (!started) {
				buttonGenerer.setDisable(true);
				theChosenOne.setDisable(true);
				chkBxEffet.setDisable(true);
				txtFldPositionX.setDisable(true);
				txtFldPositionY.setDisable(true);
				txtFldCoteA.setDisable(true);
				txtFldCoteB.setDisable(true);
				txtFldCoteC.setDisable(true);
				io.setDisable(true);
				io.setValue(io.getMax());
			}
		}
	}

	private void appliquerEffet(boolean isEffet) {
		for (Node node : pn.getChildren()) {
			if (isEffet) {
				((Shape) node).setStroke(Paint.valueOf("#7F00FF"));
				((Shape) node).setStrokeWidth(1);
			} else {
				((Shape) node).setStrokeWidth(0);
			}
		}
	}

	public class ActionButton implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// FIXME Auto-generated method stub
			if (event.getSource() == buttonQuitter) {
				Platform.exit();

			} else if (event.getSource() == buttonReinitialiser) {
				pn.getChildren().removeAll(pn.getChildren());

			} else if (event.getSource() == buttonGenerer) {

				for (Node node : pn.getChildren()) {
					node.opacityProperty().bind(Bindings.divide(io.valueProperty(), io.maxProperty()));
				}

				DataFactory dF;

				if (!txtFldCoteC.isDisabled()) {
					dF = new DataFactory(name, theChosenOne.getValue(), Double.parseDouble(txtFldPositionX.getText()),
							Double.parseDouble(txtFldPositionY.getText()), Double.parseDouble(txtFldCoteA.getText()),
							Double.parseDouble(txtFldCoteB.getText()), Double.parseDouble(txtFldCoteC.getText()));
				} else {
					dF = new DataFactory(name, theChosenOne.getValue(), Double.parseDouble(txtFldPositionX.getText()),
							Double.parseDouble(txtFldPositionY.getText()), Double.parseDouble(txtFldCoteA.getText()),
							Double.parseDouble(txtFldCoteB.getText()), 0);
				}

				FormesFactory fF = new FormesFactory(dF);
				Shape newShape = fF.getShape();
				pn.getChildren().add(newShape);

				if (!dF.getNom().equals("Ligne")) {
					newShape.setTranslateX(dF.getPositionX() + 5);
					newShape.setTranslateY(dF.getPositionY() + 5);
					if (dF.getNom().equals("Ovale")) {
						newShape.setTranslateX(dF.getPositionX() + 5 + (((Ellipse) newShape).getRadiusX() / 2));
						newShape.setTranslateY(dF.getPositionY() + 5 + (((Ellipse) newShape).getRadiusY() / 2));
					}
				}

				newShape.opacityProperty().bind(Bindings.divide(io.valueProperty(), io.maxProperty()));
				appliquerEffet(chkBxEffet.isSelected());

			}

		}
	}

	public class ActionEffet implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == chkBxEffet) {
				boolean isEffet = chkBxEffet.isSelected();
				appliquerEffet(isEffet);
			}

		}

	}

	public class ActionOpacite {

	}

	public class ActionForme implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (firstTime) {
				firstTime = false;
				buttonGenerer.setDisable(false);
				theChosenOne.setDisable(false);
				txtFldPositionX.setDisable(false);
				txtFldPositionY.setDisable(false);
				chkBxEffet.setDisable(false);
				io.setDisable(false);
			}

			if (event.getSource() == listFormes.getItems().get(0)) {
				lbX.setText("PositionX");
				lbY.setText("PositionY");
				lbA.setText("Largeur");
				lbB.setText("Hauteur");
				txtFldCoteA.setDisable(false);
				txtFldCoteB.setDisable(false);
				txtFldCoteC.setDisable(true);
				name = "Ovale";

			} else if (event.getSource() == listFormes.getItems().get(1)) {
				lbX.setText("PositionX");
				lbY.setText("PositionY");
				lbA.setText("Largeur");
				lbB.setText("Hauteur");
				txtFldCoteA.setDisable(false);
				txtFldCoteB.setDisable(false);
				txtFldCoteC.setDisable(true);
				name = "Rectangle";
			} else if (event.getSource() == listFormes.getItems().get(2)) {
				lbX.setText("PositionX");
				lbY.setText("PositionY");
				lbA.setText("Côté A");
				lbB.setText("Côté B");
				txtFldCoteA.setDisable(false);
				txtFldCoteB.setDisable(false);
				txtFldCoteC.setDisable(false);
				name = "Triangle";
			} else if (event.getSource() == listFormes.getItems().get(3)) {
				lbX.setText("DébutX");
				lbY.setText("DébutY");
				lbA.setText("Fin X");
				lbB.setText("Fin Y");
				txtFldCoteA.setDisable(false);
				txtFldCoteB.setDisable(false);
				txtFldCoteC.setDisable(true);
				name = "ligne";
			}

		}
	}
}