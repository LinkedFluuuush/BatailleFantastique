package gui.actionListeners;

import core.Jeu;
import core.Joueur;
import gui.MainFrame;
import gui.menuBar.BatailleMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Jean-Baptiste Louvet on 03/12/14.
 * Action Listener pourla création d'un nouveau jeu
 */
public class NouveauJeuActionListener implements ActionListener {
    final protected BatailleMenuBar theBar;

    public NouveauJeuActionListener(BatailleMenuBar bar){
        this.theBar = bar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final JDialog newGameDialog = new JDialog((JFrame) (this.theBar.getParent().getParent().getParent().getParent()), "Nouveau jeu", true);

        newGameDialog.setLayout(new BoxLayout(newGameDialog.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelJ1 = new JPanel();
        panelJ1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        JPanel panelJ2 = new JPanel();
        panelJ2.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        panelJ1.add(new JLabel("Joueur 1"));
        final JTextField textNameJ1 = new JTextField(15);
        panelJ1.add(textNameJ1);

        panelJ2.add(new JLabel("Joueur 2"));
        final JTextField textNameJ2 = new JTextField(15);
        panelJ2.add(textNameJ2);

        JButton buttonOK = new JButton("OK");

        newGameDialog.add(panelJ1);
        newGameDialog.add(panelJ2);
        newGameDialog.add(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Random r = new Random();
                Jeu j = new Jeu();
                j.addJoueur(new Joueur(textNameJ1.getText()));
                j.addJoueur(new Joueur(textNameJ2.getText()));

                j.setJoueurCourant(j.getJoueurs().get(r.nextInt(2)));
                j.setnPersonnages(5);
                j.setnColonnes(10);
                j.setnLignes(10);

                ((MainFrame) theBar.getTopLevelAncestor()).setJeu(j);
                theBar.setNouveauJeuClicable(false);

                newGameDialog.dispose();
            }
        });

        newGameDialog.setSize(275, 200);

        newGameDialog.setVisible(true);
    }
}
