package gui.actionListeners;

import core.Jeu;
import core.Personnage;
import gui.panels.JPanelStat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * User: Linked
 * Date: 16/12/2014
 * Time: 22:50
 */
public class selectPersoMouseListener implements MouseListener {
    private Jeu jeu;
    private Personnage p;
    private JPanel panel;
    private JPanel panelResume;

    public selectPersoMouseListener(Jeu jeu, Personnage p, JPanel panel, JPanel panelResume) {
        this.jeu = jeu;
        this.p = p;
        this.panel = panel;
        this.panelResume = panelResume;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(Component c : panelResume.getComponents()){
            if(c.getClass() == JPanel.class){
                ((JPanel)c).setBorder(BorderFactory.createEtchedBorder());
            }
        }

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jeu.setPersoAttaquant(p);
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
