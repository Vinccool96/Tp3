package tp2_fxml.modele;

/**
 * �num�rations des couleurs possibles de la forme: rouge, bleu, vert, noir ou
 * orange.
 */
public enum Couleur {
	ROUGE("rouge"), 
	VERT("vert"), 
	BLEU("bleu"), 
	JAUNE("jaune"), 
	NOIR("noir"), 
	ORANGE("orange");
	private String _nom = null;

	private Couleur(String aNom) {
		throw new UnsupportedOperationException();
	}

	/**
	 * M�thode toString affichant dans la console le nom de la Couleur.
	 */
	public String toString() {
		throw new UnsupportedOperationException();
	}

	public void setNom(String aNom) {
		this._nom = aNom;
	}

	public String getNom() {
		return this._nom;
	}
}