package gui.menuBar;

import gui.actionListeners.AProposActionListener;
import gui.actionListeners.NouveauJeuActionListener;
import gui.actionListeners.ReglesActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jean-Baptiste Louvet on 03/12/14.
 * Classe pour la barre de menus de l'application graphique
 * @author Jean-Baptiste Louvet, Laurent Laluque
 * @version 0.1
 */
public class BatailleMenuBar extends JMenuBar {
    JMenu menuJeu;
    JMenuItem boutonNouveauJeu;
    JMenuItem boutonSauvegarder;
    JMenuItem boutonCharger;
    JMenuItem boutonQuit;

    JMenu menuAide;
    JMenuItem boutonRegles;
    JMenuItem boutonAPropos;

    public BatailleMenuBar() {
        //Constitution du menu Jeu
        menuJeu = new JMenu("Jeu");

        boutonNouveauJeu = new JMenuItem("Nouveau Jeu");

        boutonSauvegarder = new JMenuItem("Sauvegarder la partie");
        boutonSauvegarder.setEnabled(false);
        boutonCharger = new JMenuItem("Charger une partie");
        boutonCharger.setEnabled(false);

        boutonQuit = new JMenuItem("Quitter");

        menuJeu.add(boutonNouveauJeu);
        menuJeu.addSeparator();
        menuJeu.add(boutonSauvegarder);
        menuJeu.add(boutonCharger);
        menuJeu.addSeparator();
        menuJeu.add(boutonQuit);

        //Constitution du menu Aide
        menuAide = new JMenu("Aide");

        boutonRegles = new JMenuItem("Règles du jeu");
        boutonAPropos = new JMenuItem("A propos");

        menuAide.add(boutonRegles);
        menuAide.addSeparator();
        menuAide.add(boutonAPropos);

        //Ajout des menus dans la barre
        this.add(menuJeu);
        this.add(menuAide);

        //Définition des Action Listeners
        boutonNouveauJeu.addActionListener(new NouveauJeuActionListener(this));

        boutonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        boutonAPropos.addActionListener(new AProposActionListener(this));

        boutonRegles.addActionListener(new ReglesActionListener(this));
    }

    public void setNouveauJeuClicable(boolean clicable){
        boutonNouveauJeu.setEnabled(clicable);
    }

}
