package gui.panels;

import core.*;
import gui.MainFrame;
import gui.actionListeners.AddPersonnageActionListener;

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

    //Composants pour l'interface de sélection d'équipe
    private JComboBox<String> typesPersoComboBox;
    private JTextField nomPersonnageTextField;
    private JTextField agePersonnageTextField;
    private JButton ajouterPersonnageButton;
    private JLabel labelError;
    private JPanel resumePersos;
    private JPanel panelSelection;

    //Composants pour l'interface de jeu
    private JLabel labelClasse;
    private JLabel labelNom;
    private JLabel labelAge;
    private JLabel labelStatut;
    private JLabel labelPV;
    private JLabel labelDeplacement;



    public JPanelStat(){
        this.setPreferredSize(new Dimension(300, 550));
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
        resumePersos = new JPanel();
        resumePersos.setLayout(new BoxLayout(resumePersos, BoxLayout.PAGE_AXIS));

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
        this.add(resumePersos);
    }

    public void majResumePersos(Joueur j){
        LinkedList<Personnage> persos = j.getPersonnages();
        Iterator<Personnage> persosIt = persos.iterator();
        Personnage p;
        JPanel unPerso;

        resumePersos.removeAll();

        while(persosIt.hasNext()){
            p = persosIt.next();
            unPerso = new JPanel();
            unPerso.setLayout(new BoxLayout(unPerso, BoxLayout.PAGE_AXIS));
            unPerso.add(new JLabel(p.getClass().toString()));
            unPerso.add(new JLabel(p.getNom()));
            unPerso.add(new JLabel(p.getAge() + " ans"));
            unPerso.setBorder(BorderFactory.createEtchedBorder());

            resumePersos.add(unPerso);
        }
    }

    public void interfaceJeu(Jeu jeu){
        JPanel panelResumePerso = new JPanel();
        panelResumePerso.setLayout(new BoxLayout(panelResumePerso, BoxLayout.PAGE_AXIS));

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

        panelResumePerso.setBorder(BorderFactory.createTitledBorder(""));

        this.add(panelResumePerso);
    }

    public void updateStats(Personnage perso) {
        if(perso.getClass() == Mage.class){
            labelClasse.setText("Mage");
        } else if(perso.getClass() == Voleur.class){
            labelClasse.setText("Voleur");
        } else if(perso.getClass() == Guerrier.class){
            labelClasse.setText("Guerrier");
        } else if(perso.getClass() == CavalierCeleste.class){
            labelClasse.setText("Cavalier Céleste");
        }

        labelNom.setText(perso.getNom());
        labelAge.setText(perso.getAge() + " ans");
        labelStatut.setText("Statut : En vie");
        labelDeplacement.setText("Déplacement : " + perso.getDeplacement() + " cases par tour");
        labelPV.setText("Vie : " + perso.getPv() + " PV");

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

}
