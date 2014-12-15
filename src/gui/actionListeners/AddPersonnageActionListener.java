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
    private Jeu jeu;

    public AddPersonnageActionListener(JPanelStat panelStat, Jeu jeu){
        this.panelStat = panelStat;
        this.jeu = jeu;
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
                    jeu.getJoueurCourant().addPerso(typePerso, nomPerso, agePerso);
                    panelStat.getTypesPersoComboBox().setSelectedIndex(0);
                    panelStat.getNomPersonnageTextField().setText("");
                    panelStat.getAgePersonnageTextField().setText("");

                    if(jeu.getJoueurCourant().getPersonnages().size() == jeu.getnPersonnages()){
                        for(int i = 0; i < jeu.getJoueurs().size(); i++){
                            if(jeu.getJoueurs().get(i) == jeu.getJoueurCourant()){
                                jeu.setJoueurCourant(jeu.getJoueurs().get((i+1) % jeu.getJoueurs().size()));
                                if(jeu.getJoueurCourant().getPersonnages().size() == 0){
                                    panelStat.getPanelSelection().setBorder(BorderFactory.createTitledBorder("Sélection d'équipe - " + jeu.getJoueurCourant().getNom()));
                                } else {
                                    jeu.setEtatCourant(Jeu.Etat.ENCOURS);
                                    ((MainFrame) panelStat.getTopLevelAncestor()).updateFromState();
                                }
                                break;
                            }
                        }
                    }

                    panelStat.majResumePersos(jeu.getJoueurCourant());
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
