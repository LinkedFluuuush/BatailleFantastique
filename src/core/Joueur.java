package core;

import exception.ClassePersonnageManquanteException;

import java.util.LinkedList;


public class Joueur {

	private String nom;
	private LinkedList<Personnage> personnages;
	
	/**
	 * @param nom Le nom du joueur
	 */
	public Joueur(String nom) {
		super();
		this.nom = nom;
        this.personnages = new LinkedList<Personnage>();
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
	public void addPerso(String nomType, String nom, int age) throws ClassePersonnageManquanteException {
		Personnage nouveauPerso;
		// On fait des "if" car le switch case avec String n'est pas accepté
		if(nomType.equals("Mage")) {
			nouveauPerso = new Mage(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType.equals("CavalierCeleste")) {
			nouveauPerso = new CavalierCeleste(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType.equals("Voleur")) {
			nouveauPerso = new Voleur(nom, age);
			this.addPersonnage(nouveauPerso);
		} else if(nomType.equals("Guerrier")) {
			nouveauPerso = new Guerrier(nom, age);
			this.addPersonnage(nouveauPerso);
		} else {
			throw new ClassePersonnageManquanteException("Le type " + nomType + " n'existe pas !");
		}
	}
}
