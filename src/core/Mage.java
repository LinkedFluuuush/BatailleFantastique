package core;

public class Mage extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Mage(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
/*		this.deplacement = 2;
		this.pv = 8;
		// Attaques
		this.attaques.add(new Attaque("Tempête", int portee, int zone, boolean ralentissement,int effet));*/
	}

	/** Vérifie si le déplacement voulu est valide
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @param j Jeu par rapport auquel on fait le déplacement
=	 * @return validité du déplacement
	 */
	public boolean verifDeplacementValide(int x, int y, Jeu j){
		// Le mage ne peut se déplacer qu'en vertical ou horizontal
		boolean directionValide = (x == this.getPositionX() || y == this.getPositionY());
		int nbCasesParcourues = 0; // nombre de cases nécessaires à parcourir pour arriver à la case cible
		int deltaX = Math.abs(x - this.getPositionX()); // x - persoAttaquant.getPositionX() représente l'écart en abscisse de la position courante à la cible
		int deltaY = Math.abs(y - this.getPositionY()); // y - persoAttaquant.getPositionY() représente l'écart en ordonnée de la position courante à la cible
		nbCasesParcourues += deltaX;
		nbCasesParcourues += deltaY;
		return(directionValide || (nbCasesParcourues > this.deplacement) || j.getPerso(x,y) == null);
	}
	
}
