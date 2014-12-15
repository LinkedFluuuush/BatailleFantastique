package core;

public class Mage extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Mage(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
		this.deplacement = 2;
		this.pv = 8;
		// Attaques
        // TODO : définir l'infini (à la place de 1000)
        this.attaques.add(new Attaque("Tempête", 1000, 2, false, 4, true, false));
        this.attaques.add(new Attaque("Tremblement de terre", 1000, 2, false, 3, false, true));
        this.attaques.add(new Attaque("Boule de feu", 4, 1, false, 4, true, true));
        this.attaques.add(new Attaque("Enchevêtrement de ronces", 4, 1, true, 0, false, true));
        this.attaques.add(new Attaque("Terre marécageuse", 1000, 2, true, 0, false, true));
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
		return(directionValide && (nbCasesParcourues <= this.deplacement) && j.getPerso(x,y) == null);
	}
	
}
