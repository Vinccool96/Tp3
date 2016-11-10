package tp2_fxml.exception;

@SuppressWarnings("serial")
public class FormeException extends Exception {

	public FormeException() {
		super("Erreur dans la construction de la forme");
	}

	public FormeException(String message) {
		super(message);
	}

}
