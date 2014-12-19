package core;

import java.util.LinkedList;

public abstract class Personnage {

	private String nom;
	private int age;
	private int positionX;
	private int positionY;
	protected LinkedList<Attaque> attaques;
	protected int deplacement;
	protected int malusDeplacement;
    protected int pv; // points de vie
	protected boolean terrestre; // vaut vrai si le personnage est terrestre
	private int protection; // protection dont le personnage peut bénéficier grâce à l'instinct d'esquive, par exemple
	private int nAttaquesRestantes;
	private boolean deplacementFait;
	
	/**
	 * @param nom
	 * @param age
	 */
	public Personnage(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
		this.positionX = -1;
		this.positionY = -1;
		this.malusDeplacement = 0;
		this.protection = 0;
        this.attaques = new LinkedList<Attaque>();
		this.deplacementFait = false;
		this.nAttaquesRestantes = 2;
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

    public int getPv() {
            return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

	public int getnAttaquesRestantes() {
		return nAttaquesRestantes;
	}

	public void setnAttaquesRestantes(int nAttaquesRestantes) {
		this.nAttaquesRestantes = nAttaquesRestantes;
	}

	public boolean isDeplacementFait() {
		return deplacementFait;
	}

	public void setDeplacementFait(boolean deplacementFait) {
		this.deplacementFait = deplacementFait;
	}

    public int getMalusDeplacement() {
        return malusDeplacement;
    }

    public int getProtection(){
        return  protection;
    }

    /** Modifie le nombre de points de vie du personnage
	 * @param attaque effet d'une attaque subie
	 */
	public void appliquerEffets(Attaque attaque){
		if(attaque.getNom() == "Instinct d'esquive") {
			protection = 3;
		}
		if((attaque.isCeleste() && !this.terrestre) || (attaque.isTerrestre() && this.terrestre)){ // vérifie si le personnage est vulnérable ou non à l'attaque, selon qu'il est terrestre ou céleste
			if(attaque.isRalentissement()){
				this.malusDeplacement++;
			} else {
				if (attaque.getEffet() >= protection){
					pv = pv - attaque.getEffet() + protection;
                    protection = 0;
				} else {
					protection -= attaque.getEffet();
					// Le nombre de PV ne change pas car la protection absorbe les dégâts faits par l'attaque
				}
			}
		}
	}
	
	
	/** Modifie la position du personnage
	 * @param x nouvelle abscisse
	 * @param y nouvelle ordonnée
	 */
	public void deplacerPerso(int x, int y){
		positionX = x;
		positionY = y;
        malusDeplacement = 0;
	}
	
	/** Vérifie si le déplacement voulu est valide
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @return validité du déplacement
	 */
	abstract public boolean verifDeplacementValide(int x, int y, Jeu j);
	
	/** Met le personnage à sa nouvelle position (pour le début de partie)
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 */
	public void nouvelleCase(int x,int y) {
		this.positionX = x;
		this.positionY = y;
	}
}
