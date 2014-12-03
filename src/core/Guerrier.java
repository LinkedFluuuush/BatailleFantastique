package core;

public class Guerrier extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public Guerrier(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}

    @Override
    public boolean verifDeplacementValide(int x, int y, Jeu j) {
        return false;
    }

}
