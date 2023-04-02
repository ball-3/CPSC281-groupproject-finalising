import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Game Class as part of the Hangman Project
 *
 * Game gets the picker to picker a word, either via interactive screen or by creating a new Word object.
 * Game then creates a new Hangman object
 */
public class Game {

    Word word;
    String inputString;
    Hangman man;
    int[] gamemode;

    JTextField textField;
    JButton back;

    public Game(int[] gamemode, JPanel panel, JButton back)
    {
        this.gamemode = gamemode;
        this.back = back;

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

    private void startGame(boolean iCP)
    {
        if (gamemode[2] == 0)      //guesser is computer
        {

            man = new Hangman(word, true, iCP);
        }

        else if (gamemode[2] == 1)      //guesser is human
        {
            man = new Hangman(word,false, iCP);
        }
        formatHangman();
    }

    private void formatHangman()
    {
        man.setSize(1000,650);
        man.setVisible(true);
    }

    private void makePickWordPanel(JPanel panel)
    {
        Font fontTwo = new Font("Arial Bold", 0, 36);
        panel.setVisible(false);
        GridBagConstraints constraints = new GridBagConstraints();

        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Main.c2);

        JTextField title = new JTextField();
        title.setFont(new Font("Arial Bold", 0, 46));
        title.setEditable(false);
        title.setText("Please Enter a Word to be Guessed: ");
        title.setBackground(Main.c2);
        title.setForeground(Main.c3);
        title.setBorder(null);

        textField = new JTextField(15);
        textField.setFont(fontTwo);
        textField.setPreferredSize(new Dimension(800,80));
        textField.setBorder(null);
        textField.setBackground(Main.c3);
        textField.setForeground(Main.c4);

        JButton button = new JButton();
        button.addActionListener(new thisListener(back));
        button.setFont(fontTwo);
        button.setText("Submit");
        button.setBackground(Main.c3);
        button.setForeground(Main.c2);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(200,50));

        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        constraints.gridx = 1;
        //constraints.gridy = 0;        created errors in formatting for some reason so left out
        constraints.weightx = 0.65;
        constraints.weighty = 0.25;
        panel.add(title, constraints);
        //constraints.gridy = 1;        created errors in formatting for some reason so left out
        constraints.weightx = 0.5;
        constraints.weighty = 0.25;
        panel.add(textField,constraints);
        //constraints.gridy = 2;        created errors in formatting for some reason so left out
        constraints.weightx = 0.25;
        constraints.weighty = 0.2;
        panel.add(button,constraints);

        panel.setVisible(true);
    }

    /**
     * Custom listener class that starts a game (with help of Game)
     * and returns frame to home screen when an action is performed.
     */
    private class thisListener implements ActionListener
    {
        JButton back;

        public thisListener(JButton back)
        {
            this.back = back;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            inputString = textField.getText();
            word = new Word(gamemode[3], inputString);
            startGame(false);
            back.doClick();
        }
    }
}
