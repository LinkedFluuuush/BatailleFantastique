package gui.panels;

import gui.actionListeners.AddPersonnageActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * User: Linked
 * Date: 13/12/14
 * Time: 12:21
 */
public class JPanelStat extends JPanel {

    private JComboBox<String> typesPersoComboBox;
    private JTextField nomPersonnageTextField;
    private JTextField agePersonnageTextField;
    private JButton ajouterPersonnageButton;
    private JLabel labelError;

    public JPanelStat(){
        this.setPreferredSize(new Dimension(300, 550));
    }

    public void interfaceSelection() {

        typesPersoComboBox = new JComboBox<String>();
        typesPersoComboBox.addItem("Mage");
        typesPersoComboBox.addItem("Voleur");
        typesPersoComboBox.addItem("Guerrier");
        typesPersoComboBox.addItem("CavalierCeleste");

        nomPersonnageTextField = new JTextField(20);
        agePersonnageTextField = new JTextField(20);
        ajouterPersonnageButton = new JButton("Ajouter le personnage");
        labelError = new JLabel();

        ajouterPersonnageButton.addActionListener(new AddPersonnageActionListener(this));

        JPanel panelSelection = new JPanel();

        panelSelection.setLayout(new BoxLayout(panelSelection, BoxLayout.PAGE_AXIS));

        panelSelection.add(typesPersoComboBox);
        panelSelection.add(new JLabel("Nom"));
        panelSelection.add(nomPersonnageTextField);
        panelSelection.add(new JLabel("Age"));
        panelSelection.add(agePersonnageTextField);
        panelSelection.add(ajouterPersonnageButton);

        panelSelection.setBorder(BorderFactory.createTitledBorder("Sélection d'équipe"));

        this.add(panelSelection);
        this.add(labelError);
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
}
