package gui.panels;

import core.Jeu;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 12:21
 */
public class JPanelPlateau extends JPanel {

    public JPanelPlateau(){
        this.setPreferredSize(new Dimension(700,550));
        this.setBackground(Color.WHITE);

        this.repaint();
    }

    public void paint(Graphics g){
        Jeu jeu = ((MainFrame)this.getTopLevelAncestor()).getJeu();
        int tailleH = 700 / jeu.getnColonnes();
        int tailleV = 550 / jeu.getnLignes();
        boolean color = true;

        for(int i = 0 ; i < jeu.getnColonnes() ; i++){
            for(int j = 0 ; j < jeu.getnLignes() ; j++){
                if(color){
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(i * tailleH, j * tailleV, tailleH, tailleV);
                color = !color;
            }
            if(jeu.getnLignes() % 2 == 0){
                color = !color;
            }
        }
    }
}
