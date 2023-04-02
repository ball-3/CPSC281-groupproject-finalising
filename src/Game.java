import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    Word word;
    String inputString;
    Hangman man;
    int[] gamemode;

    JTextField textField;

    public Game(int[] gamemode, JPanel panel)
    {
        this.gamemode = gamemode;

        if (gamemode[1] == 0)     //picker is computer
        {
            word = new Word(gamemode[3], gamemode[4]);
            startGame(true);
        }

        else if (gamemode[1] == 1)      //picker is human
        {
            makePickWordPanel(panel);
        }
    }

    private void startGame(boolean isComputerPicker)
    {
        if (gamemode[2] == 0)      //guesser is computer
        {

            man = new Hangman(word, true, isComputerPicker);
        }

        else if (gamemode[2] == 1)      //guesser is human
        {
            man = new Hangman(word,false, isComputerPicker);
        }
        formatHangman();
    }

    //TODO fix this, this is basically just copied off the makeshift settings from menu for the hangman
    private void formatHangman()
    {
        man.setSize(1000,650);      //TODO is this the final sizee ? no but should be fixed (removed) with the frame fixing
        man.setVisible(true);
    }

    private void makePickWordPanel(JPanel panel)
    {
        Font fontTwo = new Font("Arial Bold", 0, 36);
        panel.setVisible(false);
        //TODO make components array more dynamic ?
        //TODO make buttons array more dynamic ?
        GridBagConstraints constraints = new GridBagConstraints();

        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JTextField title = new JTextField();
        title.setFont(new Font("Arial Bold", 0, 46));
        title.setEditable(false);
        title.setText("Please Enter a Word to be Guessed: ");
        title.setBackground(Color.LIGHT_GRAY);

        textField = new JTextField(15);
        textField.setFont(fontTwo);
        textField.setPreferredSize(new Dimension(800,80));

        JButton button = new JButton();
        button.addActionListener(new thisListener());
        button.setFont(fontTwo);
        button.setText("Submit");
        button.setPreferredSize(new Dimension(200,50));

        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        constraints.gridx = 1;
        //constraints.gridy = 0;
        constraints.weightx = 0.65;
        constraints.weighty = 0.25;
        panel.add(title, constraints);
        //constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.25;
        panel.add(textField,constraints);
        //constraints.gridy = 2;
        constraints.weightx = 0.25;
        constraints.weighty = 0.2;
        panel.add(button,constraints);

        panel.setVisible(true);
    }

    private class thisListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            inputString = textField.getText();
            word = new Word(gamemode[3], inputString);
            startGame(false);
        }
    }
}
