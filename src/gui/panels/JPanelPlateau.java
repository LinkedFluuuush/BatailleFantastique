package gui.panels;

import core.*;
import gui.MainFrame;
import gui.actionListeners.PlateauMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 12:21
 */
public class JPanelPlateau extends JPanel {

    LinkedList<Point> casesDeplacement;

    public JPanelPlateau(){
        this.setPreferredSize(new Dimension(700,550));
        this.setBackground(Color.WHITE);

        this.casesDeplacement = new LinkedList<Point>();

        this.repaint();

        this.addMouseListener(new PlateauMouseListener(this));
    }

    public void setCasesDeplacement(LinkedList<Point> casesDeplacement) {
        this.casesDeplacement = casesDeplacement;
    }

    @Override
    public void paint(Graphics g){
        Jeu jeu = ((MainFrame)this.getTopLevelAncestor()).getJeu();
        int tailleH = 700 / jeu.getnColonnes();
        int tailleV = 550 / jeu.getnLignes();
        boolean color = true;

        String type;
        Personnage temp;

        Point p = new Point();

        for(int i = 0 ; i < jeu.getnColonnes() ; i++){
            for(int j = 0 ; j < jeu.getnLignes() ; j++){
                if(color){
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }

                type = "";

                temp = jeu.getPerso(i, j);
                if(temp != null){
                    if(temp.getClass() == Mage.class){
                        type = "M";
                    } else if(temp.getClass() == Voleur.class){
                        type = "V";
                    } else if(temp.getClass() == Guerrier.class){
                        type = "G";
                    } else if(temp.getClass() == CavalierCeleste.class){
                        type = "C";
                    }
                }

                if(color){
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * tailleH, j * tailleV, tailleH, tailleV);
                color = !color;

                p.setLocation(i, j);
                if(casesDeplacement.contains(p)){
                    g.setColor(Color.BLUE);
                    g.drawRect(i * tailleH, j * tailleV, tailleH - 1, tailleV - 1);
                }

                g.setColor(Color.BLACK);
                g.drawString(type, i * tailleH + (tailleH/2), j * tailleV + (tailleV/2));
            }
            if(jeu.getnLignes() % 2 == 0){
                color = !color;
            }
        }

        if(jeu.getPersoAttaquant() != null){
            g.setColor(Color.GREEN);
            g.drawRect(jeu.getPersoAttaquant().getPositionX() * tailleH, jeu.getPersoAttaquant().getPositionY() * tailleV, tailleH, tailleV);
        }
    }
}
