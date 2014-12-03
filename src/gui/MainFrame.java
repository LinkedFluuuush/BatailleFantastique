package gui;

import core.Jeu;
import gui.menuBar.BatailleMenuBar;

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

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setJMenuBar(new BatailleMenuBar());

        this.setBounds(10, 10, 1000, 550);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    /**
     * Méthode de lancement de l'application
     * @param args arguments de lancement
     */
    public static void main(String[] args){
        MainFrame frame = new MainFrame("Bataille Fantastique");
    }
}
