package gui.panels;

import core.*;
import gui.MainFrame;
import gui.actionListeners.AddPersonnageActionListener;
import gui.actionListeners.FinTourActionListener;
import gui.actionListeners.SelectAttaqueActionListener;
import gui.actionListeners.SelectPersoMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 12:21
 */
public class JPanelStat extends JPanel {

    private JPanel panelResumePerso;
    private JPanel panelResumeActions;

    //Composants pour l'interface de sélection d'équipe
    private JComboBox<String> typesPersoComboBox;
    private JTextField nomPersonnageTextField;
    private JTextField agePersonnageTextField;
    private JButton ajouterPersonnageButton;
    private JLabel labelError;
    private JPanel panelSelection;

    //Composants pour l'interface de jeu
    private JLabel labelClasse;
    private JLabel labelNom;
    private JLabel labelAge;
    private JLabel labelStatut;
    private JLabel labelPV;
    private JLabel labelDeplacement;
    private JLabel labelAttaque;
    private JLabel labelJoueur;

    public JPanelStat(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(new Dimension(300, 400));
        this.setPreferredSize(new Dimension(300, 400));
    }

    public void interfaceSelection(Jeu jeu) {

        typesPersoComboBox = new JComboBox<String>();
        typesPersoComboBox.addItem("Mage");
        typesPersoComboBox.addItem("Voleur");
        typesPersoComboBox.addItem("Guerrier");
        typesPersoComboBox.addItem("CavalierCeleste");

        nomPersonnageTextField = new JTextField(20);
        agePersonnageTextField = new JTextField(20);
        ajouterPersonnageButton = new JButton("Ajouter le personnage");
        labelError = new JLabel();

        ajouterPersonnageButton.addActionListener(new AddPersonnageActionListener(this, jeu));

        panelSelection = new JPanel();
        panelResumePerso = new JPanel();
        panelResumePerso.setLayout(new BoxLayout(panelResumePerso, BoxLayout.PAGE_AXIS));

        panelSelection.setLayout(new BoxLayout(panelSelection, BoxLayout.PAGE_AXIS));

        panelSelection.add(typesPersoComboBox);
        panelSelection.add(new JLabel("Nom"));
        panelSelection.add(nomPersonnageTextField);
        panelSelection.add(new JLabel("Age"));
        panelSelection.add(agePersonnageTextField);
        panelSelection.add(ajouterPersonnageButton);

        panelSelection.setBorder(BorderFactory.createTitledBorder("Sélection d'équipe - " + jeu.getJoueurCourant().getNom()));

        this.add(panelSelection);
        this.add(labelError);
        this.add(panelResumePerso);
    }

    public void majResumePersos(Joueur j){
        LinkedList<Personnage> persos = j.getPersonnages();
        Iterator<Personnage> persosIt = persos.iterator();
        Personnage p;
        JPanel unPerso;

        panelResumePerso.removeAll();

        while(persosIt.hasNext()){
            p = persosIt.next();
            unPerso = new JPanel();
            unPerso.setLayout(new BoxLayout(unPerso, BoxLayout.PAGE_AXIS));
            
            if (p.getClass() == Mage.class) {
                unPerso.add(new JLabel("Mage"));
            } else if (p.getClass() == Voleur.class) {
                unPerso.add(new JLabel("Voleur"));
            } else if (p.getClass() == Guerrier.class) {
                unPerso.add(new JLabel("Guerrier"));
            } else if (p.getClass() == CavalierCeleste.class) {
                unPerso.add(new JLabel("Cavalier Céleste"));
            }

            unPerso.add(new JLabel(p.getNom()));
            unPerso.add(new JLabel(p.getAge() + " ans"));
            unPerso.setBorder(BorderFactory.createEtchedBorder());

            panelResumePerso.add(unPerso);
        }
    }

    public void interfaceJeu(Jeu jeu){
        panelResumePerso = new JPanel();
        panelResumeActions = new JPanel();

        panelResumePerso.setBorder(BorderFactory.createTitledBorder(""));

        panelResumePerso.setLayout(new BoxLayout(panelResumePerso, BoxLayout.PAGE_AXIS));
        panelResumeActions.setLayout(new BoxLayout(panelResumeActions, BoxLayout.PAGE_AXIS));

        labelJoueur = new JLabel("Au tour de " + jeu.getJoueurCourant().getNom());
        this.add(labelJoueur);

        JButton buttonFinTour = new JButton("Fin du tour");
        buttonFinTour.addActionListener(new FinTourActionListener(this));
        this.add(buttonFinTour);

        this.add(panelResumePerso);
        this.add(panelResumeActions);

        labelAttaque = new JLabel();
        this.add(labelAttaque);

        panelResumePerso.updateUI();
        panelResumeActions.updateUI();
        this.updateUI();
    }

    public void updateStats(Personnage perso) {
        panelResumePerso.removeAll();
        panelResumeActions.removeAll();

        if(perso != null) {

            labelClasse = new JLabel();
            labelNom = new JLabel();
            labelAge = new JLabel();
            labelStatut = new JLabel();
            labelPV = new JLabel();
            labelDeplacement = new JLabel();

            panelResumePerso.add(labelClasse);
            panelResumePerso.add(labelNom);
            panelResumePerso.add(labelAge);
            panelResumePerso.add(labelStatut);
            panelResumePerso.add(labelDeplacement);
            panelResumePerso.add(labelPV);

            if (perso.getClass() == Mage.class) {
                labelClasse.setText("Mage");
            } else if (perso.getClass() == Voleur.class) {
                labelClasse.setText("Voleur");
            } else if (perso.getClass() == Guerrier.class) {
                labelClasse.setText("Guerrier");
            } else if (perso.getClass() == CavalierCeleste.class) {
                labelClasse.setText("Cavalier Céleste");
            }

            labelNom.setText(perso.getNom());
            labelAge.setText(perso.getAge() + " ans");
            if(perso.getMalusDeplacement() != 0) {
                labelStatut.setText("Statut : Ralenti");
            } else if(perso.getProtection() != 0) {
                labelStatut.setText("Statut : Protégé");
            } else {
                labelStatut.setText("Statut : En vie");
            }
            labelDeplacement.setText("Déplacement : " + (perso.getDeplacement() - perso.getMalusDeplacement()) + " cases par tour");
            labelPV.setText("Vie : " + perso.getPv() + " PV");
        }

        this.updateUI();
    }

    public void updateActions(Personnage perso, Jeu jeu) {
        panelResumeActions.removeAll();
        JButton buttonAttaque;

        if(perso != null) {
            for (int i = 0; i < perso.getAttaques().size(); i++) {
                buttonAttaque = new JButton(perso.getAttaques().get(i).getNom());
                buttonAttaque.addActionListener(new SelectAttaqueActionListener(perso.getAttaques().get(i), this));

                if(jeu.getPersoAttaquant().getnAttaquesRestantes() < perso.getAttaques().get(i).getnAttaques()){
                    buttonAttaque.setEnabled(false);
                }

                panelResumeActions.add(buttonAttaque);
            }
        }

        panelResumeActions.setBorder(BorderFactory.createTitledBorder("Attaques"));
        this.updateUI();
    }

    public JComboBox<String> getTypesPersoComboBox() {
        return typesPersoComboBox;
    }

    public void setTypesPersoComboBox(JComboBox<String> typesPersoComboBox) {
        this.typesPersoComboBox = typesPersoComboBox;
    }

    public JTextField getNomPersonnageTextField() {
        return nomPersonnageTextField;
    }

    public void setNomPersonnageTextField(JTextField nomPersonnageTextField) {
        this.nomPersonnageTextField = nomPersonnageTextField;
    }

    public JTextField getAgePersonnageTextField() {
        return agePersonnageTextField;
    }

    public void setAgePersonnageTextField(JTextField agePersonnageTextField) {
        this.agePersonnageTextField = agePersonnageTextField;
    }

    public JButton getAjouterPersonnageButton() {
        return ajouterPersonnageButton;
    }

    public void setAjouterPersonnageButton(JButton ajouterPersonnageButton) {
        this.ajouterPersonnageButton = ajouterPersonnageButton;
    }

    public JLabel getLabelError() {
        return labelError;
    }

    public JPanel getPanelSelection() {
        return panelSelection;
    }

    public JPanel getPanelResumePerso() {
        return panelResumePerso;
    }

    public JLabel getLabelAttaque() {
        return labelAttaque;
    }

    public JLabel getLabelJoueur() {
        return labelJoueur;
    }

    public void attaqueEnabled(boolean b) {
        for(Component c : this.panelResumeActions.getComponents()){
            if(c.getClass() == JButton.class){
                c.setEnabled(b);
            }
        }
    }

    public void interfacePlacement(final Jeu jeu) {
        this.removeAll();

        panelResumePerso = new JPanel();
        labelError = new JLabel();

        this.add(panelResumePerso);
        this.add(labelError);

        this.updateUI();
    }

    public void updatePlacement(Joueur joueur){
        panelResumePerso.removeAll();
        JPanel unPerso;

        for(Personnage p : joueur.getPersonnages()){
            if(p.getPositionX() == -1) {

                unPerso = new JPanel();
                unPerso.setLayout(new BoxLayout(unPerso, BoxLayout.PAGE_AXIS));

                if (p.getClass() == Mage.class) {
                    unPerso.add(new JLabel("Mage"));
                } else if (p.getClass() == Voleur.class) {
                    unPerso.add(new JLabel("Voleur"));
                } else if (p.getClass() == Guerrier.class) {
                    unPerso.add(new JLabel("Guerrier"));
                } else if (p.getClass() == CavalierCeleste.class) {
                    unPerso.add(new JLabel("Cavalier Céleste"));
                }

                unPerso.add(new JLabel(p.getNom()));
                unPerso.add(new JLabel(p.getAge() + " ans"));
                unPerso.setBorder(BorderFactory.createEtchedBorder());

//                unPerso.addActionListener(new SelectPersoMouseListener(((MainFrame) this.getTopLevelAncestor()).getJeu(), p, unPerso, panelResumePerso));
                unPerso.addMouseListener(new SelectPersoMouseListener(((MainFrame) this.getTopLevelAncestor()).getJeu(), p, unPerso, panelResumePerso));

                panelResumePerso.add(unPerso);
            }
        }

        labelError.setText("");

        this.updateUI();
    }
}
