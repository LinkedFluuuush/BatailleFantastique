package core;

public class Attaque {
	
	private String nom; // nom de l'attaque
	private int portee; // portée de l'attaque
	/**
	 * @return the celeste
	 */
	public boolean isCeleste() {
		return celeste;
	}
	/**
	 * @param celeste the celeste to set
	 */
	public void setCeleste(boolean celeste) {
		this.celeste = celeste;
	}
	/**
	 * @return the terrestre
	 */
	public boolean isTerrestre() {
		return terrestre;
	}
	/**
	 * @param terrestre the terrestre to set
	 */
	public void setTerrestre(boolean terrestre) {
		this.terrestre = terrestre;
	}
	private int zone; // zone de l'attaque en cas d'attaque sur une zone
	private boolean ralentissement; // vaut vrai si l'attaque est un ralentissement
	private int effet; // nombre de points de vie perdus par la cible
	private boolean celeste; // vaut vrai si l'attaque est céleste
	private boolean terrestre; // vaut vrai si l'attaque est terrestre
	private int nAttaques; // nombre d'attaques possibles avec cette attaque

	/**
	 * @param nom
	 * @param portee
	 * @param zone
	 * @param ralentissement
	 * @param effet
	 * @param celeste
	 * @param terrestre
	 * @param nAttaques
	 */
	public Attaque(String nom, int portee, int zone, boolean ralentissement,
			int effet, boolean celeste, boolean terrestre, int nAttaques) {
		super();
		this.nom = nom;
		this.portee = portee;
		this.zone = zone;
		this.ralentissement = ralentissement;
		this.effet = effet;
		this.celeste = celeste;
		this.terrestre = terrestre;
		this.nAttaques = nAttaques;
	}
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

	public int getnAttaques() {
		return nAttaques;
	}

	public void setnAttaques(int nAttaques) {
		this.nAttaques = nAttaques;
	}
}
