package core;

public class CavalierCeleste extends Personnage {

	/**
	 * @param nom
	 * @param age
	 */
	public CavalierCeleste(String nom, int age) {
		super(nom, age);
		// TODO Auto-generated constructor stub
	}

    @Override
    public boolean verifDeplacementValide(int x, int y, Jeu j) {
        return false;
    }

}
