import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Menu Class as part of the Hangman Project
 *
 * Menu handles GUI for the menu and settings screens, starts an appropriate Game object when necessary,
 * and holds the code for the settings menu.
 */
public class Menu implements ActionListener
{
        Game game;
        JPanel panel;
        GridBagConstraints constraints;
        JButton startButton;
        JButton settingsButton;
        JButton backButton;
        JTextField title;
        Font butttonFont = new Font("Arial Bold", 0, 48);
        Font fontTwo = new Font("Arial Bold", 0, 16);

        String actionCommand;
        int[] gamemode = new int[/*size of number of gamemode options*/5];
        JButton[] buttons;
        String[] wordCategories;
        ThisListener listener = new ThisListener();

        public Menu()
        {
                panel = new JPanel();

                //default settings:
                gamemode[1] = 0;
                gamemode[2] = 0;
                gamemode[3] = 0;
                gamemode[4] = 0;

                wordCategories = new String[]{"Fruits", "Popular Artists", "Computer Science"};

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
                panel.setBackground(Main.c2);
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

        private void setupTitle()
        {
                title = new JTextField();
                title.setEditable(false);
                title.setFont(new Font("Arial Bold", 0, 98));
                title.setText("Hangman");
                title.setBorder(null);
                title.setBackground(Main.c2);
                title.setForeground(Main.c3);
        }

        private void setupButtons()
        {
                startButton = new JButton();
                startButton.addActionListener(this);
                startButton.setActionCommand("start");
                startButton.setFont(butttonFont);
                startButton.setPreferredSize(new Dimension(500,140));
                startButton.setText("Start");
                startButton.setBorder(null);
                startButton.setBackground(Main.c3);
                startButton.setForeground(Main.c4);

                settingsButton = new JButton();
                settingsButton.addActionListener(this);
                settingsButton.setActionCommand("settings");
                settingsButton.setFont(butttonFont);
                settingsButton.setPreferredSize(new Dimension(500,140));
                settingsButton.setText("Settings");
                settingsButton.setBorder(null);
                settingsButton.setBackground(Main.c3);
                settingsButton.setForeground(Main.c4);
        }

        private void makeSettingsPanel()
        {
                panel.setVisible(false);
                JComponent[][] components = new JComponent[5][4];       //five rows, four columns (three buttons + one title)
                constraints = new GridBagConstraints();

                panel.removeAll();
                panel.setLayout(new GridBagLayout());
                panel.setBackground(Main.c2);
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
                        buttons[i].setBackground(Main.c3);
                        buttons[i].setForeground(Main.c4);
                        buttons[i].setBorder(null);
                        buttons[i].setPreferredSize(new Dimension(200,50));
                }
                buttons[0].setPreferredSize(new Dimension(100,50));
                buttons[0].setBackground(Main.c3);
                buttons[0].setActionCommand("back");
                buttons[0].setText("Back");
                backButton = buttons[0];

                buttons[1].setActionCommand("p1human");
                buttons[1].setText("Human");
                if (gamemode[1] == 1) buttons[1].setBackground(Main.c1);

                buttons[2].setActionCommand("p1computer");
                buttons[2].setText("Computer");
                if (gamemode[1] == 0) buttons[2].setBackground(Main.c1);

                buttons[3].setActionCommand("p2human");
                buttons[3].setText("Human");
                if (gamemode[2] == 1) buttons[3].setBackground(Main.c1);

                buttons[4].setActionCommand("p2computer");
                buttons[4].setText("Computer");
                if (gamemode[2] == 0) buttons[4].setBackground(Main.c1);

                buttons[5].setVisible(false);
                buttons[6].setVisible(false);
                buttons[7].setVisible(false);

                for (int i = 0; i < textFields.length; i++)
                {
                        textFields[i] = new JTextField();
                        textFields[i].setEditable(false);
                        textFields[i].setFont(fontTwo);
                        textFields[i].setBackground(Main.c2);
                        textFields[i].setForeground(Main.c4);
                        textFields[i].setBorder(null);
                }
                textFields[0].setFont(new Font("Arial Bold", 0, 38));
                textFields[0].setText("Settings");
                textFields[1].setText("Word Picker:");
                textFields[2].setText("Word Guesser:");
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

                        JSlider slider = new JSlider(0,26,gamemode[3]);
                        slider.setPreferredSize(new Dimension(400,50));
                        slider.setMajorTickSpacing(5);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.createStandardLabels(1);
                        slider.setBackground(Main.c2);
                        slider.setForeground(Main.c4);
                        slider.setValue(gamemode[3]);
                        slider.addChangeListener(new ChangeListener() {
                                @Override
                                public void stateChanged(ChangeEvent e) {
                                        JSlider source = (JSlider)e.getSource();
                                        if (!source.getValueIsAdjusting()) {
                                                System.out.println(source.getValue());
                                                gamemode[3] = (int)source.getValue();
                                        }
                                }
                        });
                components[3][1] = slider;

                components[4][0] = textFields[4];

                        JComboBox categoryBox = new JComboBox(wordCategories);
                        categoryBox.setPreferredSize(new Dimension(200,50));
                        categoryBox.addItemListener(listener);
                        categoryBox.setSelectedIndex(gamemode[4]);
                        categoryBox.setBackground(Main.c3);
                        categoryBox.setForeground(Main.c4);
                components[4][1] = categoryBox;

                constraints.weightx = 0.7/components[0].length;
                constraints.weighty = 0.8/components.length;
                for (int i = 0; i < components.length; i++)
                {
                        if (i == 0) constraints.weighty = 0.2;
                        constraints.gridy = i;
                        for (int j = 0; j < components[i].length; j++)
                        {
                                if (j == 0) constraints.weightx = 0.3;
                                constraints.gridwidth = 1;
                                if ((i == 3 || i == 4) && j == 1) constraints.gridwidth = 2;

                                if (components[i][j] != null)
                                {
                                        constraints.gridx = j;
                                        panel.add(components[i][j], constraints);
                                }
                        }
                }
        }

    /*
    Gamemode Settings:
    Array Index:    Information Stored Here:        Equivalence to Int Value:
            0               null                        null
            1       if player1 is human/computer        0 - computer, 1 - human     note: player1 is always picker.
            2       if player2 is human/computer        0 - computer, 1 - human
            3       # of mistakes allowed               0 - zero mistakes, ... 27 - 27 mistakes
            4       word category                       0 - fruit, 1 - popular artists, 2 - computer science
            5               null so far
    */
        public int[] getGamemode()
        {
                return gamemode;
        }


        @Override
        public void actionPerformed(ActionEvent e) {

                actionCommand = e.getActionCommand();

                if (actionCommand == "start")
                {
                        gamemode[4] = listener.getItem();
                        game = new Game(gamemode, panel,backButton);
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
                        gamemode[4] = listener.getItem();
                        makeSettingsPanel();
                }
                else if (actionCommand == "p1computer")
                {
                        gamemode[1] = 0;
                        gamemode[4] = listener.getItem();
                        makeSettingsPanel();
                }
                else if (actionCommand == "p2human")
                {
                        gamemode[2] = 1;
                        gamemode[4] = listener.getItem();
                        makeSettingsPanel();
                }
                else if (actionCommand == "p2computer")
                {
                        gamemode[2] = 0;
                        gamemode[4] = listener.getItem();
                        makeSettingsPanel();
                }
        }
}

class ThisListener implements ItemListener {

        String item = "";

        public int getItem()
        {
                if (item.equals("Fruits"))      return 0;
                if (item.equals("Popular Artists"))     return 1;
                if (item.equals("Computer Science"))    return 2;
                return 0;
        }

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
                JComboBox source = (JComboBox) itemEvent.getSource();
                item = (String) source.getSelectedItem();
        }
}