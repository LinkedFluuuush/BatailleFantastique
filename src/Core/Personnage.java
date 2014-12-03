package Core;

import java.util.LinkedList;

public abstract class Personnage {

	private String nom;
	private int age;
	private int positionX;
	private int positionY;
	private LinkedList<Attaque> attaques;
	private int deplacement;
	private int pv; // points de vie
	
	
	/**
	 * @param nom
	 * @param age
	 */
	public Personnage(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
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
}
