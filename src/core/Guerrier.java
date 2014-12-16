package core;

public class Guerrier extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Guerrier(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
        this.deplacement = 2;
        this.pv = 12;
        this.terrestre = true;
        // Attaques
//		public Attaque(String nom, int portee, int zone, boolean ralentissement, int effet, boolean celeste, boolean terrestre)
        this.attaques.add(new Attaque("Coup de taille", 1, 1, false, 4, false, true, 1));
        this.attaques.add(new Attaque("Baliste de feu", 4, 1, false, 6, false, true, 1));
        this.attaques.add(new Attaque("Coup de jarnac", 4, 1, false, 1, false, true, 2));
	}
    
    public boolean verifDeplacementValide(int x, int y, Jeu j){
        // Le mage ne peut se déplacer qu'en vertical ou horizontal
        boolean directionValide = (x == this.getPositionX() || y == this.getPositionY());
        int nbCasesParcourues = 0; // nombre de cases nécessaires à parcourir pour arriver à la case cible
        int deltaX = Math.abs(x - this.getPositionX()); // x - persoAttaquant.getPositionX() représente l'écart en abscisse de la position courante à la cible
        int deltaY = Math.abs(y - this.getPositionY()); // y - persoAttaquant.getPositionY() représente l'écart en ordonnée de la position courante à la cible
        nbCasesParcourues += deltaX;
        nbCasesParcourues += deltaY;
        return(directionValide && (nbCasesParcourues <= this.deplacement - this.malusDeplacement) && j.getPerso(x,y) == null);
    }
}
