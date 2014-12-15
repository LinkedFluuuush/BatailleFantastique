package gui.actionListeners;

import core.Jeu;
import core.Personnage;
import gui.MainFrame;
import gui.panels.JPanelPlateau;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

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

        int caseX = e.getX() / tailleH;
        int caseY = e.getY() / tailleV;

        if(jeu.getPersoAttaquant() != null){
            if(jeu.getAttaqueCourante() != null){
                if(!jeu.getPersoAttaquant().isAttaqueFaite()) {
                    if ((caseX == jeu.getPersoAttaquant().getPositionX() && Math.abs(jeu.getPersoAttaquant().getPositionX() - caseX) <= jeu.getAttaqueCourante().getPortee())
                            || (caseY == jeu.getPersoAttaquant().getPositionY() && Math.abs(jeu.getPersoAttaquant().getPositionY() - caseY) <= jeu.getAttaqueCourante().getPortee())) {
                        for (int i = Math.max(-(jeu.getAttaqueCourante().getZone() - 1), 0); i < Math.min((jeu.getAttaqueCourante().getZone() - 1), jeu.getnColonnes()); i++) {
                            for (int j = Math.max(-(jeu.getAttaqueCourante().getZone() - 1), 0); j < Math.min((jeu.getAttaqueCourante().getZone() - 1), jeu.getnLignes()); j++) {
                                Personnage temp = jeu.getPerso(caseX + i, caseY + j);
                                if (temp != null) {
                                    temp.appliquerEffets(jeu.getAttaqueCourante());
                                }
                            }
                        }
                        jeu.getPersoAttaquant().setAttaqueFaite(true);
                        jeu.setAttaqueCourante(null);
                    }
                }
            } else if(jeu.getPersoAttaquant().verifDeplacementValide(caseX, caseY, jeu) && !jeu.getPersoAttaquant().isDeplacementFait()){
                jeu.getPersoAttaquant().deplacerPerso(caseX, caseY);
                jeu.getPersoAttaquant().setDeplacementFait(true);
                panelPlateau.setCasesDeplacement(new LinkedList<Point>());
            }

            if(jeu.getPersoAttaquant().isAttaqueFaite() && jeu.getPersoAttaquant().isDeplacementFait()){
                jeu.setPersoAttaquant(null);
                jeu.setAttaqueCourante(null);
                jeu.setJoueurCourant(jeu.getJoueurs().get((jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) + 1) % jeu.getJoueurs().size()));
            }
        } else {
            Personnage temp = jeu.getPerso(caseX, caseY);
            jeu.setPersoAttaquant(temp);
            if (temp != null) {
                ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateStats(temp);

                LinkedList<Personnage> personnagesJouables = jeu.getJoueurCourant().getPersonnages();
                boolean isJouable = false;

                for (int i = 0; i < personnagesJouables.size(); i++) {
                    if (personnagesJouables.get(i) == temp) {
                        isJouable = true;
                        break;
                    }
                }

                LinkedList<Point> deplacements = new LinkedList<Point>();

                if (isJouable) {
                    for (int i = 0; i < jeu.getnColonnes(); i++) {
                        for (int j = 0; j < jeu.getnLignes(); j++) {
                            if (temp.verifDeplacementValide(i, j, jeu)) {
                                deplacements.add(new Point(i, j));
                            }
                        }
                    }


                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateActions(temp);
                }

                panelPlateau.setCasesDeplacement(deplacements);
            }
        }

        panelPlateau.repaint();
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
