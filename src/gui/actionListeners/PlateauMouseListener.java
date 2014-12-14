package gui.actionListeners;

import core.Jeu;
import core.Personnage;
import gui.MainFrame;
import gui.panels.JPanelPlateau;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 22:23
 */
public class PlateauMouseListener implements MouseListener {
    private JPanelPlateau panelPlateau;

    public PlateauMouseListener(JPanelPlateau panelPlateau) {
        this.panelPlateau = panelPlateau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Jeu jeu = ((MainFrame) panelPlateau.getTopLevelAncestor()).getJeu();
        int tailleH = 700 / jeu.getnColonnes();
        int tailleV = 550 / jeu.getnLignes();

        Personnage temp = jeu.getPerso(e.getX()/tailleH, e.getY()/tailleV);
        jeu.setPersoAttaquant(temp);
        if(temp != null){
            ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateStats(temp);
            panelPlateau.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
