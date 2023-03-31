import javax.swing.*;

public class Game {

    Word word;
    Hangman man;

    public Game(int[] gamemode)
    {
        //TODO delete these notes
        if (gamemode[1] == 0)     //picker is computer
        {
            word = new Word(gamemode[3], gamemode[4]);
        }

        else if (gamemode[1] == 1)      //picker is human
        {
            //can we add a prompt so user can pick a word here ?
            //like a f r a m  e or something
        }

        if (gamemode[2] == 0)      //guesser is computer
        {
            man = new Hangman(word, true);
        }

        else if (gamemode[2] == 1)      //guesser is human
        {
            man = new Hangman(word,false);
        }
        formatHangman();
    }

    //TODO fix this, this is basically just copied off the makeshift settings from menu for the hangman
    private void formatHangman()
    {
        man.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        man.setSize(1000,650);      //TODO is this the final sizee ? no but should be fixed (removed) with the frame fixing
        man.setVisible(true);
    }

}
