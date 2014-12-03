package core;

public class CavalierCeleste extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public CavalierCeleste(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
		this.deplacement = 2;
		this.pv = 10;
		this.terrestre = false;
		// Attaques
//		public Attaque(String nom, int portee, int zone, boolean ralentissement, int effet, boolean celeste, boolean terrestre)
		this.attaques.add(new Attaque("Enchevêtrement de ronces", 4, 1, true, 0, false, true));
		this.attaques.add(new Attaque("Coup de jarnac", 4, 1, false, 1, false, true));
		this.attaques.add(new Attaque("Nuage maléfique", 2, 1, false, 3, true, true));
	}
	
	/** Vérifie si le déplacement voulu est valide
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @param j Jeu par rapport auquel on fait le déplacement
=	 * @return validité du déplacement
	 */
	public boolean verifDeplacementValide(int x, int y, Jeu j){
		// Le cavalier céleste peut se déplacer également en diagonale : on ne s'occupe pas de savoir si sa direction est valide
		int nbCasesParcourues = 0; // nombre de cases nécessaires à parcourir pour arriver à la case cible
		int deltaX = Math.abs(x - this.getPositionX()); // x - persoAttaquant.getPositionX() représente l'écart en abscisse de la position courante à la cible
		int deltaY = Math.abs(y - this.getPositionY()); // y - persoAttaquant.getPositionY() représente l'écart en ordonnée de la position courante à la cible
		nbCasesParcourues += deltaX;
		nbCasesParcourues += deltaY;
		return((nbCasesParcourues > this.deplacement) || j.getPerso(x,y) == null);
	}

}
