package gui.actionListeners;

import gui.menuBar.BatailleMenuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jean-Baptiste Louvet on 19/12/14.
 */
public class AProposActionListener implements ActionListener {
    private BatailleMenuBar theBar;

    public AProposActionListener(BatailleMenuBar theBar){
        this.theBar = theBar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(theBar.getTopLevelAncestor(), "Bataille Fantastique est un jeu de stratégie en tour par tour\n" +
                "créé par Laurent Laluque et Jean-Baptiste Louvet\n" +
                "pour un projet de l'Enssat.", "A propos", JOptionPane.PLAIN_MESSAGE);
    }
}
