package gui.actionListeners;

import core.Jeu;
import exception.ClassePersonnageManquanteException;
import gui.MainFrame;
import gui.panels.JPanelStat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 17:33
 */
public class AddPersonnageActionListener implements ActionListener {
    private JPanelStat panelStat;

    public AddPersonnageActionListener(JPanelStat panelStat){
        this.panelStat = panelStat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelStat.getLabelError().setText("");
        String typePerso = (String) panelStat.getTypesPersoComboBox().getSelectedItem();
        String nomPerso = panelStat.getNomPersonnageTextField().getText();

        if(nomPerso.equals("")){
            panelStat.getLabelError().setText("<html><font color=#FF0000>Nom incorrect</font></html>");
        } else {
            try{
                int agePerso = Integer.parseInt(panelStat.getAgePersonnageTextField().getText());

                try {
                    Jeu jeu = ((MainFrame) panelStat.getParent().getParent().getParent().getParent().getParent()).getJeu();
                    jeu.getJoueurCourant().addPerso(typePerso, nomPerso, agePerso);
                    panelStat.getTypesPersoComboBox().setSelectedIndex(0);
                    panelStat.getNomPersonnageTextField().setText("");
                    panelStat.getAgePersonnageTextField().setText("");
                    System.out.println("Added : " + typePerso + " : " + nomPerso + ", " + agePerso + "ans.");
                } catch (ClassePersonnageManquanteException e1) {
                    e1.printStackTrace();
                }

            } catch(NumberFormatException e1){
                panelStat.getLabelError().setText("<html><font color=#FF0000>Age incorrect</font></html>");
            }
        }

        panelStat.updateUI();
    }
}
