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
				final JLabel labelErreurTeamSize = new JLabel();
				final JLabel labelErreurBoardSize = new JLabel();
				final JLabel labelErreurZoneSize = new JLabel();

                j.addJoueur(new Joueur(textNameJ1.getText()));
                j.addJoueur(new Joueur(textNameJ2.getText()));

                j.setJoueurCourant(j.getJoueurs().get(r.nextInt(2)));
        		
        		newGameDialog.dispose();

        		final JDialog parametersDialog = new JDialog();
        		parametersDialog.setLayout(new BoxLayout(parametersDialog.getContentPane(), BoxLayout.PAGE_AXIS));
        		
        		final JPanel panelTeamSize = new JPanel();
        		panelTeamSize.setLayout(new BoxLayout(panelTeamSize, BoxLayout.PAGE_AXIS));
        		
        		final JPanel panelHorizontalBoardSize = new JPanel();
        		panelHorizontalBoardSize.setLayout(new BoxLayout(panelHorizontalBoardSize, BoxLayout.PAGE_AXIS));
        		
        		final JPanel panelVerticalBoardSize = new JPanel();
        		panelVerticalBoardSize.setLayout(new BoxLayout(panelVerticalBoardSize,BoxLayout.PAGE_AXIS));

        		final JPanel panelZoneSize = new JPanel();
        		panelZoneSize.setLayout(new BoxLayout(panelZoneSize,BoxLayout.PAGE_AXIS));
        		
        		panelTeamSize.add(new JLabel("Sélectionnez une taille d'équipe"));
                final JTextField textTeamSize = new JTextField(15);
                panelTeamSize.add(textTeamSize);
                panelTeamSize.add(labelErreurTeamSize);

                panelHorizontalBoardSize.add(new JLabel("Sélectionnez une taille horizontale pour le plateau"));
                final JTextField textHorizontalBoardSize = new JTextField(15);
                panelHorizontalBoardSize.add(textHorizontalBoardSize);
                
                panelVerticalBoardSize.add(new JLabel("Sélectionnez une taille verticale pour le plateau"));
                final JTextField textVerticalBoardSize = new JTextField(15);
                panelVerticalBoardSize.add(textVerticalBoardSize);
                panelVerticalBoardSize.add(labelErreurBoardSize);

                panelZoneSize.add(new JLabel("Sélectionnez le nombre de rangées pour le placement des personnages"));
                final JTextField textZoneSize = new JTextField(15);
                panelZoneSize.add(textZoneSize);
                panelZoneSize.add(labelErreurZoneSize);

                JButton buttonOK2 = new JButton("OK");
                
                parametersDialog.add(panelTeamSize);
                parametersDialog.add(panelHorizontalBoardSize);
                parametersDialog.add(panelVerticalBoardSize);
                parametersDialog.add(panelZoneSize);
                parametersDialog.add(buttonOK2);
                
                parametersDialog.setSize(450,300);
                parametersDialog.setVisible(true);
                
                buttonOK2.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent actionEvent) {
							int teamSize;
							int horizontalBoardSize;
							int verticalBoardSize;
							int zoneSize;

							boolean bonneTailleEquipe = false;
							boolean bonneTaillePlateau = false;
							boolean bonneTailleZoneDepart = false;


							try {
								teamSize = Integer.parseInt(textTeamSize.getText());
								if(j.validerTailleEquipe(teamSize)) {
									bonneTailleEquipe = true;
									j.setnPersonnages(teamSize);
									labelErreurTeamSize.setText("");
								} else {
									bonneTailleEquipe = false;
									labelErreurTeamSize.setText("<html><font color=#FF0000>L'équipe doit avoir entre 1 et 5 combattants !</font></html>");
								}
							} catch (NumberFormatException e){
								labelErreurTeamSize.setText("<html><font color=#FF0000>Veuillez entrer un chiffre entre 1 et 5.</font></html>");
							}

							try {
	                    		horizontalBoardSize = Integer.parseInt(textHorizontalBoardSize.getText());
								verticalBoardSize = Integer.parseInt(textVerticalBoardSize.getText());
								if(j.validerTaillePlateau(horizontalBoardSize, verticalBoardSize)){
									bonneTaillePlateau = true;
									j.setnColonnes(horizontalBoardSize);
									j.setnLignes(verticalBoardSize);
									labelErreurBoardSize.setText("");
								} else {
									bonneTaillePlateau = false;
									labelErreurBoardSize.setText("<html><font color=#FF0000>Le plateau doit faire au moins 5 cases de côté !</font></html>");
								}
							} catch (NumberFormatException e){
								labelErreurBoardSize.setText("<html><font color=#FF0000>Veuillez entrer une taille de minimum 5 par 5, en chiffres.</font></html>");
							}

							try {
	                    		zoneSize = Integer.parseInt(textZoneSize.getText());
								if(j.validerTailleZoneDepart(zoneSize)){
									bonneTailleZoneDepart = true;
									j.setTailleZoneDepart(zoneSize);
									labelErreurZoneSize.setText("");
								} else {
									bonneTailleZoneDepart = false;
									labelErreurZoneSize.setText("<html><font color=#FF0000>La zone de départ ne doit pas être plus grande que la moitié du plateau !</font></html>");
								}
							} catch (NumberFormatException e){
								labelErreurZoneSize.setText("<html><font color=#FF0000>Veuillez entrer une taille de zone en chiffres.</font></html>");
							}

	                        if(bonneTailleEquipe && bonneTaillePlateau && bonneTailleZoneDepart){
								((MainFrame) theBar.getTopLevelAncestor()).setJeu(j);
								theBar.setNouveauJeuClicable(false);

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
