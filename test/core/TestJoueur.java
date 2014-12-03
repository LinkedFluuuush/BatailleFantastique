package core;

import exception.ClassePersonnageManquanteException;
import org.junit.Test;

import core.*;
import static org.junit.Assert.*;

/**
 * Created by Jean-Baptiste Louvet on 25/11/14.
 * Classe de test de la classe core.Joueur
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
    public void testAddPersoMage() throws ClassePersonnageManquanteException {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("Mage", "Siegfried", 26);

        assertTrue(j1.getPersonnages().get(0).getClass() == Mage.class);
    }

    @Test
    public void testAddPersoGuerrier() throws ClassePersonnageManquanteException {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("Guerrier", "Durak", 26);

        assertTrue(j1.getPersonnages().get(0).getClass() == Guerrier.class);
    }

    @Test
    public void testAddPersoVoleur() throws ClassePersonnageManquanteException {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("Voleur", "Faïeur", 26);

        assertTrue(j1.getPersonnages().get(0).getClass() == Voleur.class);
    }

    @Test
    public void testAddPersoCavalierCeleste() throws ClassePersonnageManquanteException {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("CavalierCeleste", "Lorandara", 26);

        assertTrue(j1.getPersonnages().get(0).getClass() == CavalierCeleste.class);
    }

    @Test(expected = ClassePersonnageManquanteException.class)
    public void testAddPersoException() throws ClassePersonnageManquanteException {
        Joueur j1 = new Joueur("toto");

        j1.addPerso("Haleflin dérangé", "TwentyThree", 18);
    }
}
