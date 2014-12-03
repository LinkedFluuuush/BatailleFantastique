package core;

import exception.ClassePersonnageManquanteException;

import java.util.LinkedList;

/**
 * Description de la classe
 * @author llaluque jlouvet
 * @version
 */
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
	 * @param joueur joueur à ajouter
	 */
	public void addJoueur(Joueur joueur) {
		this.joueurs.add(joueur);
	}
	
	/**
	 * Indique le personnage choisi par la joueur et demande à l'ajouter à l'équipe du joueur
	 * @param nomType nom du type du personnage choisi 
	 * @param joueur indice du joueur dans la liste des joueurs
	 * @param nom nom du personnage
	 * @param age âge du personnage
	 */
	public void choixPersonnage(String nomType, int joueur, String nom, int age) throws ClassePersonnageManquanteException {
		joueurs.get(joueur).addPerso(nomType, nom, age);
	}
}
