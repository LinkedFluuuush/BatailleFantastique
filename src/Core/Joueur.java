package Core;

import java.util.LinkedList;

/**
 * Description de la classe
 * @author llaluque jlouvet
 * @version
 */
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
	public void addPerso(String nomType, String nom, int age) throws Exception {
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
			// TODO : créer classe étendant Exception
			throw new Exception("Le type " + nomType + " n'existe pas !");
		}
	}
	
	/**
	 * @param x abscisse de la case sélectionnée
	 * @param y ordonnées de la case sélectionnée
	 * @return perso personnage se situant à ces coordonnées, null si aucun personnage ne se trouve sur cette case
	 */
	public Personnage getPerso(int x, int y) {
		Personnage res = null; // Pour stocker le résultat
		for(int i = 0; i < personnages.size(); i++){
			if(personnages.get(i).getPositionX() == x){
				if(personnages.get(i).getPositionY() == y){
					res = personnages.get(i);
				}
			}
		}
		return res; // Si aucun personnage correspondant n'a été trouvé, res vaut null
	}
}
