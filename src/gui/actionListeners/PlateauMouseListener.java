package gui.actionListeners;

import core.Jeu;
import core.Personnage;
import gui.MainFrame;
import gui.panels.JPanelPlateau;

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

        if(jeu.getEtatCourant() == Jeu.Etat.ENCOURS) {
            if (jeu.getPersoAttaquant() != null) {
                if (jeu.getAttaqueCourante() != null) {
                    if (jeu.getPersoAttaquant().getnAttaquesRestantes() != 0) {
                        if ((caseX == jeu.getPersoAttaquant().getPositionX() && Math.abs(jeu.getPersoAttaquant().getPositionX() - caseX) <= jeu.getAttaqueCourante().getPortee())
                                || (caseY == jeu.getPersoAttaquant().getPositionY() && Math.abs(jeu.getPersoAttaquant().getPositionY() - caseY) <= jeu.getAttaqueCourante().getPortee())) {
                            for (int i = (Math.max(caseX - (jeu.getAttaqueCourante().getZone() / 2), 0)); i <= Math.min(caseX + (jeu.getAttaqueCourante().getZone() / 2), jeu.getnColonnes()); i++) {
                                for (int j = (Math.max(caseY - (jeu.getAttaqueCourante().getZone() / 2), 0)); j <= Math.min(caseY + (jeu.getAttaqueCourante().getZone() / 2), jeu.getnLignes()); j++) {
                                    Personnage temp = jeu.getPerso(i, j);
                                    if (temp != null) {
                                        jeu.attaquerCible(jeu.getAttaqueCourante(), temp);

                                        if (jeu.getJoueurs().size() == 1) {
                                            jeu.setJoueurCourant(jeu.getJoueurs().getFirst());
                                            ((MainFrame) panelPlateau.getTopLevelAncestor()).victory();
                                            return;
                                        }
                                    }
                                }
                            }
                            jeu.getPersoAttaquant().setnAttaquesRestantes(jeu.getPersoAttaquant().getnAttaquesRestantes() - 1);

                            if (jeu.getPersoAttaquant().getnAttaquesRestantes() == 0) {
                                jeu.setAttaqueCourante(null);
                                ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().getLabelAttaque().setText("");
                            }

                            ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().attaqueEnabled(false);
                            panelPlateau.setZoneCible(null);
                        }
                    }
                } else if (jeu.getPersoAttaquant().verifDeplacementValide(caseX, caseY, jeu) && !jeu.getPersoAttaquant().isDeplacementFait()) {
                    jeu.getPersoAttaquant().deplacerPerso(caseX, caseY);
                    jeu.getPersoAttaquant().setDeplacementFait(true);
                    panelPlateau.setCasesDeplacement(new LinkedList<Point>());
                }

                if (jeu.getPersoAttaquant().getnAttaquesRestantes() == 0 && jeu.getPersoAttaquant().isDeplacementFait()) {
                    jeu.getPersoAttaquant().setnAttaquesRestantes(-1);
                    jeu.getPersoAttaquant().setDeplacementFait(false);
                    jeu.setPersoAttaquant(null);
                    jeu.setAttaqueCourante(null);
                    jeu.setJoueurCourant(jeu.getJoueurs().get((jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) + 1) % jeu.getJoueurs().size()));
                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().getLabelJoueur().setText("Au tour de " + jeu.getJoueurCourant().getNom());
                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateActions(null, jeu);
                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updateStats(null);

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
        } else if (jeu.getEtatCourant() == Jeu.Etat.PLACEMENT){
            if(jeu.getPersoAttaquant() != null){
                boolean resteAPlacer = false;
                jeu.getPersoAttaquant().deplacerPerso(caseX, caseY);
                jeu.setJoueurCourant(jeu.getJoueurs().get((jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) + 1) % jeu.getJoueurs().size()));

                for(Personnage p : jeu.getJoueurCourant().getPersonnages()){
                    if(p.getPositionX() == -1){
                        resteAPlacer = true;
                        break;
                    }
                }

                if(resteAPlacer) {
                    ((MainFrame) panelPlateau.getTopLevelAncestor()).getPanelStat().updatePlacement(jeu.getJoueurCourant());
                } else {
                    jeu.setEtatCourant(Jeu.Etat.ENCOURS);
                    ((MainFrame) panelPlateau.getTopLevelAncestor()).updateFromState();
                }
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

            int tailleX = (zone) * tailleH;
            int tailleY = (zone) * tailleV;

            if((caseX -(jeu.getAttaqueCourante().getZone()/2)) < 0){
                tailleX = (zone - 1) * tailleH;
            }

            if((caseY -(jeu.getAttaqueCourante().getZone()/2)) < 0){
                tailleY = (zone - 1) * tailleV;
            }

            int caseXOrig = (Math.max(caseX -(jeu.getAttaqueCourante().getZone()/2), 0)) * tailleH;
            int caseYOrig = (Math.max(caseY -(jeu.getAttaqueCourante().getZone()/2), 0)) * tailleV;

            panelPlateau.setZoneCible(caseXOrig , caseYOrig, tailleX, tailleY);

            panelPlateau.repaint();
        }
    }
}
