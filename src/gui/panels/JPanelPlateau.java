package gui.panels;

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
        int tailleH = 700 / 5;
        int tailleV = 550 / 5;
        boolean color = true;

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                if(color){
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(i * tailleH, j * tailleV, tailleH, tailleV);
                color = !color;
            }
        }
    }
}
