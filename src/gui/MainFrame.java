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

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setJMenuBar(new BatailleMenuBar());

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
                break;
            case ENCOURS:
                panelStat.removeAll();
                break;
        }
    }

    /**
     * Méthode de lancement de l'application
     * @param args arguments de lancement
     */
    public static void main(String[] args){
        MainFrame frame = new MainFrame("Bataille Fantastique");
    }
}
