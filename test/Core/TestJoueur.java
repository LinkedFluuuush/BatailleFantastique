package Core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Jean-Baptiste Louvet on 25/11/14.
 */
public class TestJoueur {

    @Test
    public void testCreate(){
        String nom = "toto";
        Joueur j1 = new Joueur(nom);

        assertTrue(j1.getNom().equals(nom));
    }

    @Test
    public void testSetNom(){
        Joueur j1 = new Joueur("toto");
        assertTrue(j1.getNom().equals("toto"));

        String nom = "titi";
        j1.setNom(nom);
        assertTrue(j1.getNom().equals(nom));
    }

    @Test
    public void testAddPersoMage() {
        Joueur j1 = new Joueur("toto");

        try {
            j1.addPerso("Mage", "Siegfried", 26);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(j1.getPersonnages().get(0).getClass() == Mage.class);
    }

    @Test
    public void testAddPersoGuerrier() {
        Joueur j1 = new Joueur("toto");

        try {
            j1.addPerso("Guerrier", "Durak", 26);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(j1.getPersonnages().get(0).getClass() == Guerrier.class);
    }

    @Test
    public void testAddPersoVoleur() {
        Joueur j1 = new Joueur("toto");

        try {
            j1.addPerso("Voleur", "Faïeur", 26);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(j1.getPersonnages().get(0).getClass() == Voleur.class);
    }

    @Test
    public void testAddPersoCavalierCeleste() {
        Joueur j1 = new Joueur("toto");

        try {
            j1.addPerso("CavalierCeleste", "Lorandara", 26);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(j1.getPersonnages().get(0).getClass() == CavalierCeleste.class);
    }

    @Test(expected = Exception.class) public void testAddPersoFaux() throws Exception {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("Haleflin dérangé", "TwentyThree", 18);
    }
}
