package core;

public class Attaque {
	
	private String nom; // nom de l'attaque
	private int portee; // portée de l'attaque
	private int zone; // zone de l'attaque en cas d'attaque sur une zone
	private boolean ralentissement; // vaut vrai si l'attaque est un ralentissement
	private int effet; // nombre de points de vie perdus par la cible
	/**
	 * @return the effet
	 */
	public int getEffet() {
		return effet;
	}
	/**
	 * @param effet the effet to set
	 */
	public void setEffet(int effet) {
		this.effet = effet;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}
	/**
	 * @return the ralentissement
	 */
	public boolean isRalentissement() {
		return ralentissement;
	}
	/**
	 * @param ralentissement the ralentissement to set
	 */
	public void setRalentissement(boolean ralentissement) {
		this.ralentissement = ralentissement;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @param portee the portee to set
	 */
	public void setPortee(int portee) {
		this.portee = portee;
	}
	/**
	 * @return the portee
	 */
	public int getPortee() {
		return portee;
	}
	
}
