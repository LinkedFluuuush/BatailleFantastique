package core;

import java.util.LinkedList;

public abstract class Personnage {

	private String nom;
	private int age;
	private int positionX;
	private int positionY;
	protected LinkedList<Attaque> attaques;
	protected int deplacement;
	protected int pv; // points de vie
	
	
	/**
	 * @param nom
	 * @param age
	 */
	public Personnage(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
		this.positionX = 0;
		this.positionY = 0;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}
	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @return the attaques
	 */
	public LinkedList<Attaque> getAttaques() {
		return attaques;
	}

	/**
	 * @return the deplacement
	 */
	public int getDeplacement() {
		return deplacement;
	}
	
	/** Modifie le nombre de points de vie du personnage
	 * @param effet effet d'une attaque subie
	 */
	public void appliquerEffets(int effet){
		pv = pv - effet;
	}
	
	
	/** Modifie la position du personnage
	 * @param x nouvelle abscisse
	 * @param y nouvelle ordonnée
	 */
	public void deplacerPerso(int x, int y){
		positionX = x;
		positionY = y;
	}
	
	/** Vérifie si le déplacement voulu est valide
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @return validité du déplacement
	 */
	abstract public boolean verifDeplacementValide(int x, int y, Jeu j);
}
