package Core;

import java.util.LinkedList;

public abstract class Personnage {

	private String nom;
	private int age;
	private int positionX;
	private int positionY;
	private LinkedList<Attaque> attaques;
	private int deplacement;
	
	
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
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}
	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
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
	
}
