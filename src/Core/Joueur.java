package Core;

import java.util.LinkedList;


public class Joueur {

	private String nom;
	private LinkedList<Personnage> personnages;
	
	/**
	 * @param nom
	 */
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return the personnages
	 */
	public LinkedList<Personnage> getPersonnages() {
		return personnages;
	}

	/**
	 * @param personnage personnage à ajouter
	 */
	public void addPersonnage(Personnage personnage) {
		this.personnages.add(personnage);
	}

	/**
	 * Ajoute un personnage à l'équipe du joueur
	 * @param nomType nom du type du personnage choisi 
	 * @param nom nom du personnage
	 * @param age âge du personnage
	 */
	public void addPerso(String nomType, String nom, int age){
		Personnage nouveauPerso;
		// On fait des "if" car le switch case avec String n'est pas accepté
		if(nomType == "Mage") {
			nouveauPerso = new Mage(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType == "CavalierCeleste") {
			nouveauPerso = new CavalierCeleste(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType == "Voleur") {
			nouveauPerso = new Voleur(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType == "Guerrier") {
			nouveauPerso = new Guerrier(nom, age);
			this.addPersonnage(nouveauPerso);
		} else {
			// LEVER UNE EXCEPTION
		}
	}
}
