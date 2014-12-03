package core;

public class Voleur extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Voleur(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}

    @Override
    public boolean verifDeplacementValide(int x, int y, Jeu j) {
        return false;
    }

}
