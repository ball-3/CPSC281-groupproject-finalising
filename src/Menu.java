import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener
{
        JPanel panel;
        GridBagConstraints constraints;
        JButton startButton;
        JButton settingsButton;
        //could be swapped for an image for fancier menu
        JTextField title;
        Font butttonFont = new Font("Arial Bold", 0, 48);
        Font fontTwo = new Font("Arial Bold", 0, 16);
        Color background = UIManager.getColor("Panel.background");
        Game game;
        String actionCommand;
        //should have some kind of documentation for the gamemode options and the associated numbers
        int[] gamemode = new int[/*size of number of gamemode options*/5];
        JButton[] buttons;

        //frame 1000 by 800
        public Menu()
        {
                panel = new JPanel();

                //default settings:
                gamemode[1] = 0;
                gamemode[2] = 1;
                gamemode[3] = 0;
                gamemode[4] = 0;

                makeStartPanel();
        }

        public JPanel getPanel()
        {
                return panel;
        }

        private void makeStartPanel()
        {
                panel.setVisible(false);

                constraints = new GridBagConstraints();
                panel.removeAll();
                panel.setBackground(background);
                panel.setLayout(new GridBagLayout());

                setupTitle();
                constraints.gridwidth = 3;
                constraints.gridx = 1;
                constraints.gridy = 1;
                constraints.weightx = 0.6;
                constraints.weighty = 0.3;
                panel.add(title, constraints);

                setupButtons();
                constraints.gridwidth = 3;
                constraints.gridx = 1;
                constraints.gridy = 2;
                constraints.weightx = 0.4;
                constraints.weighty = 0.2;
                panel.add(startButton, constraints);
                constraints.gridwidth = 3;
                constraints.gridx = 1;
                constraints.gridy = 3;
                constraints.weightx = 0.4;
                constraints.weighty = 0.2;
                panel.add(settingsButton, constraints);
                panel.setVisible(true);
        }

        private void makeSettingsPanel()
        {
                panel.setVisible(false);
                //TODO make components array more dynamic ?
                //TODO make buttons array more dynamic ?
                JComponent[][] components = new JComponent[5][4];       //five rows, four columns (three buttons + one title)
                constraints = new GridBagConstraints();

                panel.removeAll();
                panel.setLayout(new GridBagLayout());
                panel.setBackground(Color.LIGHT_GRAY);
                setupSettingsComponents(components);

                panel.setVisible(true);
        }

        private void setupSettingsComponents(JComponent[][] components)
        {
                buttons = new JButton[8];
                JTextField[] textFields = new JTextField[5];

                for (int i = 0; i < buttons.length; i++)
                {
                        buttons[i] = new JButton();
                        buttons[i].addActionListener(this);
                        buttons[i].setFont(fontTwo);
                        buttons[i].setBackground(Color.GRAY);
                        buttons[i].setPreferredSize(new Dimension(200,50));
                }
                buttons[0].setPreferredSize(new Dimension(100,50));
                buttons[0].setBackground(background);
                buttons[0].setActionCommand("back");
                buttons[0].setText("Back");
                buttons[1].setActionCommand("p1human");
                buttons[1].setText("Human");
                if (gamemode[1] == 1) buttons[1].setBackground(Color.GREEN);
                buttons[2].setActionCommand("p1computer");
                buttons[2].setText("Computer");
                if (gamemode[1] == 0) buttons[2].setBackground(Color.GREEN);
                buttons[3].setActionCommand("p2human");
                buttons[3].setText("Human");
                if (gamemode[2] == 1) buttons[3].setBackground(Color.GREEN);
                buttons[4].setActionCommand("p2computer");
                buttons[4].setText("Computer");
                if (gamemode[2] == 0) buttons[4].setBackground(Color.GREEN);
                buttons[5].setActionCommand("fruit");
                buttons[5].setText("Fruits");
                if (gamemode[4] == 0) buttons[5].setBackground(Color.GREEN);
                buttons[6].setActionCommand("popart");
                buttons[6].setText("Popular Artists");
                if (gamemode[4] == 1) buttons[6].setBackground(Color.GREEN);
                buttons[7].setActionCommand("cpsc");
                buttons[7].setText("Computer Science");
                if (gamemode[4] == 2) buttons[7].setBackground(Color.GREEN);

                for (int i = 0; i < textFields.length; i++)
                {
                        textFields[i] = new JTextField();
                        textFields[i].setEditable(false);
                        textFields[i].setFont(fontTwo);
                        textFields[i].setBackground(Color.LIGHT_GRAY);
                }
                textFields[0].setFont(new Font("Arial Bold", 0, 38));
                textFields[0].setText("Settings");
                textFields[1].setText("Player 1:");
                textFields[2].setText("Player 2:");
                textFields[3].setText("Mistakes Allowed:");
                textFields[4].setText("Word Category:");

                components[0][0] = buttons[0];
                components[0][1] = textFields[0];
                components[1][0] = textFields[1];
                components[1][1] = buttons[1];
                components[1][2] = buttons[2];
                components[2][0] = textFields[2];
                components[2][1] = buttons[3];
                components[2][2] = buttons[4];
                components[3][0] = textFields[3];
                JSlider slider = new JSlider(0,27,gamemode[3]);
                components[3][1] = slider;
                slider.setMajorTickSpacing(5);
                slider.setMinorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                slider.createStandardLabels(1);
                slider.setBackground(Color.LIGHT_GRAY);
                slider.setValue(gamemode[4]);
                slider.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                                JSlider source = (JSlider)e.getSource();
                                if (!source.getValueIsAdjusting()) {
                                        System.out.println(source.getValue());
                                        gamemode[4] = (int)source.getValue();
                                }
                        }
                });
                //TODO add header 'difficulty' over components[3]
                components[4][0] = textFields[4];
                components[4][1] = buttons[5];
                components[4][2] = buttons[6];
                components[4][3] = buttons[7];
                //TODO more constraints here ?
                constraints.weightx = 0.7/components[0].length;
                constraints.weighty = 0.8/components.length;
                for (int i = 0; i < components.length; i++)
                {
                        if (i == 0) constraints.weighty = 0.2;
                        constraints.gridy = i;
                        for (int j = 0; j < components[i].length; j++)
                        {
                                if (j == 0) constraints.weightx = 0.3;
                                if (components[i][j] != null)
                                {
                                        constraints.gridx = j;
                                        panel.add(components[i][j], constraints);
                                }
                        }
                }
                if (gamemode[1] == 1)//TODO repaint if this is changed, perhaps not here
                {
                        components[4][0].setVisible(false);
                        components[4][1].setVisible(false);
                        components[4][2].setVisible(false);
                        components[4][3].setVisible(false);
                }
        }

        private void setupTitle()
        {
                title = new JTextField();
                title.setEditable(false);
                title.setFont(new Font("Arial Bold", 0, 98));
                //title.setPreferredSize(new Dimension(700,260));
                title.setText("Hangman");
                title.setBackground(background);
        }

        private void setupButtons()
        {
                startButton = new JButton();
                startButton.addActionListener(this);
                startButton.setActionCommand("start");
                startButton.setFont(butttonFont);
                startButton.setPreferredSize(new Dimension(500,140));
                startButton.setText("Start");

                settingsButton = new JButton();
                settingsButton.addActionListener(this);
                settingsButton.setActionCommand("settings");
                settingsButton.setFont(butttonFont);
                settingsButton.setPreferredSize(new Dimension(500,140));
                settingsButton.setText("Settings");
        }

        //there will be other methods here
    /*
    Gamemode Settings:
    Array Index:    Information Stored Here:        Equivalence to Int Value:
            0               null                        null
            1       if player1 is human/computer        0 - computer, 1 - human     note: player1 is always picker.
            2       if player2 is human/computer        0 - computer, 1 - human
            3       # of mistakes allowed               27 - zero mistakes, ... 0 - 27 mistakes
            4       word category                       0 - fruit, 1 - popular artists, 2 - computer science
            5               null so far
    */
        public int[] getGamemode()
        {
                return gamemode;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
                //set/ get actioncommand ?
                //TODO have flags so that checking actioncommand is more effecient, via char array or something
                actionCommand = e.getActionCommand();
                if (actionCommand == "start")
                {
                        //TODO delete old jframe frame.dispose()
                        game = new Game(gamemode, panel);
                }
                else if (actionCommand == "settings")
                {
                        makeSettingsPanel();
                }
                else if (actionCommand == "back")
                {
                        makeStartPanel();
                }
                else if (actionCommand == "p1human")
                {
                        gamemode[1] = 1;
                        //TODO do i want this and if so dew it
                        makeSettingsPanel();
                }
                else if (actionCommand == "p1computer")
                {
                        gamemode[1] = 0;
                        makeSettingsPanel();
                }
                else if (actionCommand == "p2human")
                {
                        gamemode[2] = 1;
                        makeSettingsPanel();
                }
                else if (actionCommand == "p2computer")
                {
                        gamemode[2] = 0;
                        makeSettingsPanel();
                }
                else if (actionCommand == "fruit")
                {
                        gamemode[4] = 0;
                        makeSettingsPanel();
                }
                else if (actionCommand == "popart")
                {
                        gamemode[4] = 1;
                        makeSettingsPanel();
                }
                else if (actionCommand == "cpsc")
                {
                        gamemode[4] = 2;
                        makeSettingsPanel();
                }
                //TODO in rikos gameoverpanel make going back to menu go back to menu

        }
}
