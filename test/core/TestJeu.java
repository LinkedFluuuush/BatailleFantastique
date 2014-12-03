package core;

import exception.ClassePersonnageManquanteException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Jean-Baptiste Louvet on 25/11/14.
 * Classe de test de la classe core.Jeu
 */
public class TestJeu {

    @Test
    public void testCreate(){
        Jeu jeu = new Jeu();
        assertTrue(jeu.getJoueurs() != null);
    }

    @Test
    public void testPersoAttaquant(){
        Jeu jeu = new Jeu();
        Personnage perso = new Guerrier("Lulu", 74);
        jeu.setPersoAttaquant(perso);

        assertTrue(jeu.getPersoAttaquant() == perso);
    }

    @Test
    public void testListePerso(){
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        assertTrue(jeu.getJoueurs().getFirst().getNom().equals("Flibidi"));
    }

    @Test
    public void testPersonnageMage() throws ClassePersonnageManquanteException {
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        jeu.choixPersonnage("Mage", 0, "Lulu", 74);
    }

    @Test
    public void testPersonnageGuerrier() throws ClassePersonnageManquanteException {
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        jeu.choixPersonnage("Guerrier", 0, "Lulu", 74);
    }

    @Test
    public void testPersonnageVoleur() throws ClassePersonnageManquanteException {
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        jeu.choixPersonnage("Voleur", 0, "Lulu", 74);
    }

    @Test
    public void testPersonnageCavalierCeleste() throws ClassePersonnageManquanteException {
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        jeu.choixPersonnage("CavalierCeleste", 0, "Lulu", 74);
    }

    @Test(expected = ClassePersonnageManquanteException.class)
    public void testPersonnageManquant() throws ClassePersonnageManquanteException {
        Jeu jeu = new Jeu();
        Joueur j1 = new Joueur("Flibidi");

        jeu.addJoueur(j1);

        jeu.choixPersonnage("Halfing défectueux", 0, "Lulu", 74);
    }
}
