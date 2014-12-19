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

    private LinkedList<Point> casesDeplacement;
    private Rectangle zoneCible;

    public JPanelPlateau(){
        this.setPreferredSize(new Dimension(700,550));
        this.setBackground(Color.WHITE);

        this.casesDeplacement = new LinkedList<Point>();

        this.repaint();

        PlateauMouseListener plateauMouseListener = new PlateauMouseListener(this);
        this.addMouseListener(plateauMouseListener);
        this.addMouseMotionListener(plateauMouseListener);
    }

    public void setCasesDeplacement(LinkedList<Point> casesDeplacement) {
        this.casesDeplacement = casesDeplacement;
    }

    public void setZoneCible(Rectangle zoneCible) {
        this.zoneCible = zoneCible;
    }

    public void setZoneCible(int x, int y, int width, int height) {
        this.zoneCible = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g){
        Jeu jeu = ((MainFrame)this.getTopLevelAncestor()).getJeu();
        int tailleH = 700 / jeu.getnColonnes();
        int tailleV = 550 / jeu.getnLignes();

        this.setPreferredSize(new Dimension(tailleH * jeu.getnColonnes(), tailleV * jeu.getnLignes()));
        ((MainFrame)this.getTopLevelAncestor()).pack();


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
                if(casesDeplacement.contains(p) && jeu.getAttaqueCourante() == null){
                    g.setColor(Color.BLUE);
                    g.drawRect(i * tailleH, j * tailleV, tailleH - 1, tailleV - 1);
                }

                if(!type.equals("")){
                    for(int nJoueur = 0; nJoueur <  jeu.getJoueurs().size(); nJoueur++){
                        if(jeu.getJoueurs().get(nJoueur).getPersonnages().contains(temp)){
                            if(nJoueur == 1){
                                g.setColor(Color.CYAN);
                            } else {
                                g.setColor(Color.MAGENTA);
                            }

                            g.fillRect(i * tailleH, j * tailleV, tailleH, tailleV);
                        }
                    }

                    g.setColor(Color.BLACK);
                    g.drawString(type, i * tailleH + (tailleH/2), j * tailleV + (tailleV/2));
                }
            }
            if(jeu.getnLignes() % 2 == 0){
                color = !color;
            }
        }

        if(jeu.getPersoAttaquant() != null){
            g.setColor(Color.GREEN);
            g.drawRect(jeu.getPersoAttaquant().getPositionX() * tailleH, jeu.getPersoAttaquant().getPositionY() * tailleV, tailleH, tailleV);

            if(jeu.getAttaqueCourante() != null){
                int portee = jeu.getAttaqueCourante().getPortee();

                g.setColor(Color.RED);
                int x = Math.max((jeu.getPersoAttaquant().getPositionX() - portee), 0);
                int y = jeu.getPersoAttaquant().getPositionY();
                int width = jeu.getPersoAttaquant().getPositionX() - x + 1 + Math.min(portee, jeu.getnColonnes() - jeu.getPersoAttaquant().getPositionX());
                int height = 1 ;

                g.drawRect(x * tailleH, y * tailleV, width * tailleH, height * tailleV);

                x = jeu.getPersoAttaquant().getPositionX();
                y = Math.max((jeu.getPersoAttaquant().getPositionY() - portee), 0);
                width = 1;
                height = jeu.getPersoAttaquant().getPositionY() - y + 1 + Math.min(portee, jeu.getnLignes() - jeu.getPersoAttaquant().getPositionY());

                g.drawRect(x * tailleH, y * tailleV, width * tailleH, height * tailleV);
            }
        }

        if(zoneCible != null){
            Graphics2D g2d = (Graphics2D)g;
            float alpha = 0.75f;
            g2d.setColor(new Color(1, 0, 0, alpha));
            g2d.fillRect(zoneCible.x, zoneCible.y, zoneCible.width, zoneCible.height);
        }

        if(jeu.getEtatCourant() == Jeu.Etat.PLACEMENT){
            g.setColor(Color.RED);
            if (jeu.getJoueurs().indexOf(jeu.getJoueurCourant()) == 0){
                g.drawRect(0, 0, jeu.getTailleZoneDepart() * tailleH, jeu.getnLignes() * tailleV);
            } else {
                g.drawRect((jeu.getnColonnes() * tailleH) - (jeu.getTailleZoneDepart() * tailleH) - 1, 0, jeu.getTailleZoneDepart() * tailleH, jeu.getnLignes() * tailleV);
            }
        }
    }
}
