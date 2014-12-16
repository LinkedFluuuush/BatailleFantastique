package gui.actionListeners;

import core.Jeu;
import gui.MainFrame;
import gui.panels.JPanelPlateau;
import gui.panels.JPanelStat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * User: Linked
 * Date: 16/12/2014
 * Time: 00:08
 */
public class FinTourActionListener implements ActionListener {
    private JPanelStat panelStat;

    public FinTourActionListener(JPanelStat panelStat) {
        this.panelStat = panelStat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jeu jeu = ((MainFrame)panelStat.getTopLevelAncestor()).getJeu();
        if(jeu.getPersoAttaquant() != null) {
            jeu.getPersoAttaquant().setnAttaquesRestantes(-1);
            jeu.getPersoAttaquant().setDeplacementFait(false);
        }
        jeu.setPersoAttaquant(null);
        jeu.setAttaqueCourante(null);
        jeu.setJoueurCourant(jeu.getJoueurs().get((jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) + 1) % jeu.getJoueurs().size()));

        JPanelPlateau panelPlateau = ((MainFrame)panelStat.getTopLevelAncestor()).getPanelPlateau();
        panelPlateau.setCasesDeplacement(new LinkedList<Point>());
        panelPlateau.repaint();

        panelStat.getLabelJoueur().setText("Au tour de " + jeu.getJoueurCourant().getNom());
        panelStat.getLabelAttaque().setText("");
        panelStat.updateActions(null, jeu);
        panelStat.updateStats(null);
    }
}
