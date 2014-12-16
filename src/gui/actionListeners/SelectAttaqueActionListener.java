package gui.actionListeners;

import core.Attaque;
import core.Jeu;
import gui.MainFrame;
import gui.panels.JPanelStat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Linked
 * Date: 15/12/2014
 * Time: 23:54
 */
public class SelectAttaqueActionListener implements ActionListener {
    private Attaque attaque;
    private JPanelStat panelStat;

    public SelectAttaqueActionListener(Attaque attaque, JPanelStat panelStat) {
        this.attaque = attaque;
        this.panelStat = panelStat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jeu jeu = ((MainFrame)panelStat.getTopLevelAncestor()).getJeu();
        jeu.setAttaqueCourante(attaque);
        panelStat.getLabelAttaque().setText("Attaque sélectionnée : " + jeu.getAttaqueCourante().getNom());
    }
}
