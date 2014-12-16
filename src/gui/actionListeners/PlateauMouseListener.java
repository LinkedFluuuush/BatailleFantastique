package gui.actionListeners;

import core.Jeu;
import core.Personnage;
import gui.MainFrame;
import gui.panels.JPanelPlateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 22:23
 */
public class PlateauMouseListener implements MouseListener, MouseMotionListener {
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
                        for (int i = (Math.max(caseX -(jeu.getAttaqueCourante().getZone()/2), 0)); i <= Math.min(caseX + (jeu.getAttaqueCourante().getZone()/2), jeu.getnColonnes()); i++) {
                            for (int j =(Math.max(caseX -(jeu.getAttaqueCourante().getZone()/2), 0)); j <= Math.min(caseX + (jeu.getAttaqueCourante().getZone()/2), jeu.getnLignes()); j++) {
                                Personnage temp = jeu.getPerso(i, j);
                                if (temp != null) {
                                    temp.appliquerEffets(jeu.getAttaqueCourante());
                                }
                            }
                        }
                        jeu.getPersoAttaquant().setAttaqueFaite(true);
                        jeu.setAttaqueCourante(null);
                        ((MainFrame)panelPlateau.getTopLevelAncestor()).getPanelStat().getLabelAttaque().setText("");
                        ((MainFrame)panelPlateau.getTopLevelAncestor()).getPanelStat().attaqueEnabled(false);
                        panelPlateau.setZoneCible(null);
                    }
                }
            } else if(jeu.getPersoAttaquant().verifDeplacementValide(caseX, caseY, jeu) && !jeu.getPersoAttaquant().isDeplacementFait()){
                jeu.getPersoAttaquant().deplacerPerso(caseX, caseY);
                jeu.getPersoAttaquant().setDeplacementFait(true);
                panelPlateau.setCasesDeplacement(new LinkedList<Point>());
            }

            if(jeu.getPersoAttaquant().isAttaqueFaite() && jeu.getPersoAttaquant().isDeplacementFait()){
                jeu.getPersoAttaquant().setAttaqueFaite(false);
                jeu.getPersoAttaquant().setDeplacementFait(false);
                jeu.setPersoAttaquant(null);
                jeu.setAttaqueCourante(null);
                jeu.setJoueurCourant(jeu.getJoueurs().get((jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) + 1) % jeu.getJoueurs().size()));
                ((MainFrame)panelPlateau.getTopLevelAncestor()).getPanelStat().getLabelJoueur().setText("Au tour de " + jeu.getJoueurCourant().getNom());
                ((MainFrame)panelPlateau.getTopLevelAncestor()).getPanelStat().updateActions(null, jeu);
                ((MainFrame)panelPlateau.getTopLevelAncestor()).getPanelStat().updateStats(null);

                panelPlateau.setCasesDeplacement(new LinkedList<Point>());
            }
        } else {
            Personnage temp = jeu.getPerso(caseX, caseY);
            if (temp != null) {
                ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateStats(temp);

                LinkedList<Personnage> personnagesJouables = jeu.getJoueurCourant().getPersonnages();
                boolean isJouable = false;

                for (int i = 0; i < personnagesJouables.size(); i++) {
                    if (personnagesJouables.get(i) == temp) {
                        jeu.setPersoAttaquant(temp);
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


                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateActions(temp, jeu);
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Jeu jeu = ((MainFrame) panelPlateau.getTopLevelAncestor()).getJeu();
        int tailleH = 700 / jeu.getnColonnes();
        int tailleV = 550 / jeu.getnLignes();

        int caseX = e.getX() / tailleH;
        int caseY = e.getY() / tailleV;

        if(jeu.getAttaqueCourante() != null){
            int zone = jeu.getAttaqueCourante().getZone();

            int caseXOrig = (Math.max(caseX -(jeu.getAttaqueCourante().getZone()/2), 0)) * tailleH;
            int caseYOrig = (Math.max(caseY -(jeu.getAttaqueCourante().getZone()/2), 0)) * tailleV;

            panelPlateau.setZoneCible(caseXOrig , caseYOrig, (zone) * tailleH, (zone) * tailleV);

            panelPlateau.repaint();
        }
    }
}
