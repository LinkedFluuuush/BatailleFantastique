package gui.actionListeners;

import gui.MainFrame;
import gui.menuBar.BatailleMenuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jean-Baptiste Louvet on 19/12/14.
 */
public class ReglesActionListener implements ActionListener {
    private BatailleMenuBar theBar;

    public ReglesActionListener(BatailleMenuBar batailleMenuBar) {
        this.theBar = batailleMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final JDialog reglesDialog = new JDialog((MainFrame)theBar.getTopLevelAncestor(), "Règles du jeu", true);
//        reglesDialog.setLayout(new BoxLayout(reglesDialog, BoxLayout.PAGE_AXIS));
        JPanel mainDialogPanel = new JPanel();
        mainDialogPanel.setLayout(new BoxLayout(mainDialogPanel, BoxLayout.PAGE_AXIS));

        JPanel panelRegles = new JPanel();
        panelRegles.setSize(500, 500);

        JLabel labelRegles = new JLabel();

        labelRegles.setText("<html>Bienvenue sur Bataille Fantastique !<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>Yo</html>");

        JScrollPane scrollPane = new JScrollPane(panelRegles);
        scrollPane.setSize(400, 200);

        panelRegles.add(labelRegles);

        JButton buttonClose = new JButton("OK");
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reglesDialog.dispose();
            }
        });


        mainDialogPanel.add(scrollPane);
        mainDialogPanel.add(buttonClose);
        reglesDialog.add(mainDialogPanel);

        reglesDialog.setSize(400, 300);
        reglesDialog.setVisible(true);

    }
}
