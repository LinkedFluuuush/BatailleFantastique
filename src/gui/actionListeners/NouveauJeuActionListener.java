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
 * Action Listener pour la création d'un nouveau jeu
 */
public class NouveauJeuActionListener implements ActionListener {
    final protected BatailleMenuBar theBar;

    public NouveauJeuActionListener(BatailleMenuBar bar){
        this.theBar = bar;
    }

    String msgErreurTailleEquipe = new String("");
	String msgErreurTaillePlateau = new String("");
	String msgErreurTailleZone = new String("");
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final JDialog newGameDialog = new JDialog((JFrame) (this.theBar.getTopLevelAncestor()), "Nouveau jeu", true);

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

        JButton buttonOK1 = new JButton("OK");

        newGameDialog.add(panelJ1);
        newGameDialog.add(panelJ2);
        newGameDialog.add(buttonOK1);
        
        buttonOK1.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent actionEvent) {  		
                Random r = new Random();
                final Jeu j = new Jeu();
                j.addJoueur(new Joueur(textNameJ1.getText()));
                j.addJoueur(new Joueur(textNameJ2.getText()));

                j.setJoueurCourant(j.getJoueurs().get(r.nextInt(2)));
        		
        		newGameDialog.dispose();

        		final JDialog parametersDialog = new JDialog();
        		parametersDialog.setLayout(new BoxLayout(parametersDialog.getContentPane(), BoxLayout.PAGE_AXIS));
        		
        		final JPanel panelTeamSize = new JPanel();
        		panelTeamSize.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        		
        		final JPanel panelHorizontalBoardSize = new JPanel();
        		panelHorizontalBoardSize.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        		
        		final JPanel panelVerticalBoardSize = new JPanel();
        		panelVerticalBoardSize.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        		
        		final JPanel panelZoneSize = new JPanel();
        		panelZoneSize.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        		
        		panelTeamSize.add(new JLabel("Sélectionnez une taille d'équipe"));
                final JTextField textTeamSize = new JTextField(15);
                panelTeamSize.add(textTeamSize);
                
                panelHorizontalBoardSize.add(new JLabel("Sélectionnez une taille horizontale pour le plateau" + msgErreurTaillePlateau));
                final JTextField textHorizontalBoardSize = new JTextField(15);
                panelHorizontalBoardSize.add(textHorizontalBoardSize);
                
                panelVerticalBoardSize.add(new JLabel("Sélectionnez une taille verticale pour le plateau" + msgErreurTailleZone));
                final JTextField textVerticalBoardSize = new JTextField(15);
                panelVerticalBoardSize.add(textVerticalBoardSize);
                
                panelZoneSize.add(new JLabel("Sélectionnez le nombre de rangées de départ"));
                final JTextField textZoneSize = new JTextField(15);
                panelZoneSize.add(textZoneSize);
                
                JButton buttonOK2 = new JButton("OK");
                
                parametersDialog.add(panelTeamSize);
                parametersDialog.add(panelHorizontalBoardSize);
                parametersDialog.add(panelVerticalBoardSize);
                parametersDialog.add(panelZoneSize);
                parametersDialog.add(buttonOK2);
                
                parametersDialog.setSize(400, 300);
                parametersDialog.setVisible(true);
                
                buttonOK2.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent actionEvent) {
	                    	int teamSize = Integer.parseInt(textTeamSize.getText());
	                    	int horizontalBoardSize = Integer.parseInt(textHorizontalBoardSize.getText());
	                    	int verticalBoardSize = Integer.parseInt(textVerticalBoardSize.getText());
	                    	int zoneSize = Integer.parseInt(textZoneSize.getText());
	                    	
	                    	boolean bonneTailleEquipe = true;
	                    	boolean bonneTaillePlateau = true;
	                    	boolean bonneTailleZoneDepart = true;
	                    	
	                    	if(j.validerTailleEquipe(teamSize)){
	                    		bonneTailleEquipe = true;
	                    		j.setnPersonnages(teamSize);
	                    		if(j.validerTaillePlateau(horizontalBoardSize, verticalBoardSize)){
	                    			bonneTaillePlateau = true;
	                    			j.setnColonnes(horizontalBoardSize);
				                    j.setnLignes(verticalBoardSize);
				                    if(j.validerTailleZoneDepart(zoneSize)){
				                    	bonneTailleZoneDepart = true;
				                    	System.out.println(zoneSize);
				                    	j.setTailleZoneDepart(zoneSize);
					                    ((MainFrame) theBar.getTopLevelAncestor()).setJeu(j);
				                        theBar.setNouveauJeuClicable(false);
				                    } else {
				                    	bonneTailleZoneDepart = false;
				                    	msgErreurTailleZone = "\n La zone de départ ne doit pas être plus grande que la moitié du plateau !";
				                    	panelTeamSize.add(new JLabel(msgErreurTailleEquipe));
				                    }
	                    		} else {
	                    			bonneTaillePlateau = false;
	                    			msgErreurTaillePlateau = "\n Le plateau doit faire au moins 5 cases de côté !";
	                    		}
	                    	} else {
	                    		bonneTailleEquipe = false;
	                    		msgErreurTailleEquipe = "\n L'équipe doit avoir entre 1 et 5 combattants !";
	                    	}
	
	                        if(bonneTailleEquipe && bonneTaillePlateau){
	                        	parametersDialog.dispose();
	                        }
	                    }
	                });
                
          }
        });

        newGameDialog.setSize(275, 200);

        newGameDialog.setVisible(true);
    }
}
