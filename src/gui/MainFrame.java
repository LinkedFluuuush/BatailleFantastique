package gui;

import core.Jeu;
import gui.menuBar.BatailleMenuBar;
import gui.panels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jean-Baptiste Louvet on 03/12/14.
 * Fenêtre principale du jeu Bataille Fantastique
 * @author Jean-Baptiste Louvet, Laurent Laluque
 * @version 0.1
 */
public class MainFrame extends JFrame {

    private Jeu jeu;
    private JPanel mainPanel;
    private JPanelPlateau panelPlateau;
    private JPanelStat panelStat;
    private BatailleMenuBar menuBar;

    public MainFrame(String title) throws HeadlessException {
        super(title);

        menuBar = new BatailleMenuBar();
        this.setJMenuBar(menuBar);

        mainPanel = new JPanel();
        this.add(mainPanel);

        this.setResizable(false);
        this.setBounds(10, 10, 550, 550);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
        this.setPanePlateaux();
    }

    private void setPanePlateaux() {
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        panelStat = new JPanelStat();
        panelPlateau = new JPanelPlateau();

        updateFromState();

        mainPanel.add(panelStat);
        mainPanel.add(panelPlateau);

        mainPanel.updateUI();
        this.pack();
    }

    public void updateFromState(){
        switch (this.jeu.getEtatCourant()){
            case SELECTION:
                panelStat.removeAll();
                panelStat.interfaceSelection(this.getJeu());
                break;
            case PLACEMENT:
                panelStat.removeAll();
                panelStat.interfacePlacement(this.getJeu());
                panelStat.updatePlacement(this.getJeu().getJoueurCourant());
                break;
            case ENCOURS:
                panelStat.removeAll();

/*
                //Placement des personnages, à retirer sur l'itération 3
                for(int i = 0; i < jeu.getJoueurs().size() ; i++){
                    for(int j = 0; j < jeu.getJoueurs().get(i).getPersonnages().size() ; j++){
                        jeu.getJoueurs().get(i).getPersonnages().get(j).deplacerPerso(Math.max(i * jeu.getnColonnes() - 1, 0), j);
                    }
                }
*/

                panelStat.interfaceJeu(this.getJeu());
                break;
        }

        panelPlateau.repaint();
        mainPanel.updateUI();
    }

    public JPanelPlateau getPanelPlateau() {
        return panelPlateau;
    }

    public JPanelStat getPanelStat() {
        return panelStat;
    }

    public void victory() {
        JOptionPane.showMessageDialog(this, "Victoire de " + jeu.getJoueurCourant().getNom(), "Victoire", JOptionPane.INFORMATION_MESSAGE);

        this.jeu = null;
        this.mainPanel.removeAll();
        this.menuBar.setNouveauJeuClicable(true);
        this.mainPanel.updateUI();
        this.setBounds(10, 10, 550, 550);
    }
}
