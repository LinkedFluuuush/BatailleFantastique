import java.util.LinkedList;


public class Jeu {

	private Personnage persoAttaquant;
	private LinkedList<Joueur> joueurs;
	
	/**
	 * @param persoAttaquant
	 * @param joueurs
	 */
	public Jeu(Personnage persoAttaquant, LinkedList<Joueur> joueurs) {
		super();
		this.persoAttaquant = persoAttaquant;
		this.joueurs = joueurs;
	}
	
	/**
	 * 
	 */
	public Jeu() {
		super();
		this.joueurs = new LinkedList<Joueur>();
	}

	/**
	 * @return the persoAttaquant
	 */
	public Personnage getPersoAttaquant() {
		return persoAttaquant;
	}
	/**
	 * @param persoAttaquant the persoAttaquant to set
	 */
	public void setPersoAttaquant(Personnage persoAttaquant) {
		this.persoAttaquant = persoAttaquant;
	}
	/**
	 * @return the joueurs
	 */
	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}
	/**
	 * @param joueurs the joueurs to set
	 */
	public void setJoueurs(LinkedList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
}
