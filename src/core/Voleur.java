package core;

public class Voleur extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Voleur(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
        this.deplacement = 2;
        this.pv = 8;
        this.terrestre = true;
        // Attaques
//		public Attaque(String nom, int portee, int zone, boolean ralentissement, int effet, boolean celeste, boolean terrestre)
        this.attaques.add(new Attaque("Instinct d'esquive", 0, 1, false, 0, false, true, 2));
        this.attaques.add(new Attaque("Coup de jarnac", 4, 1, false, 1, false, true, 1));
        this.attaques.add(new Attaque("Fiole de poison", 2, 1, false, 2, false, true, 2));
	}
    
    public boolean verifDeplacementValide(int x, int y, Jeu j){
        // On teste si le voleur se déplace en diagonale ou en direction unique :
        boolean directionUnique = (x == this.getPositionX() || y == this.getPositionY());
        int nbCasesParcourues = 0; // nombre de cases nécessaires à parcourir pour arriver à la case cible
        int deltaX = Math.abs(x - this.getPositionX()); // x - persoAttaquant.getPositionX() représente l'écart en abscisse de la position courante à la cible
        int deltaY = Math.abs(y - this.getPositionY()); // y - persoAttaquant.getPositionY() représente l'écart en ordonnée de la position courante à la cible
        nbCasesParcourues += deltaX;
        nbCasesParcourues += deltaY;
        if(directionUnique){
            // Sans diagonale, le voleur se déplace de 3
            return((nbCasesParcourues <= this.deplacement + 1 - this.malusDeplacement) && j.getPerso(x,y) == null);
        } else {
            // Avec diagonale, le voleur se déplace de 2
            return((nbCasesParcourues <= this.deplacement - this.malusDeplacement) && j.getPerso(x,y) == null);
        }
    }
}
