import javax.swing.JTextField;
import java.awt.*;

public class WrongInputJtext extends JTextField {
    String word;

    public WrongInputJtext(String w) {
        this.word = w;

        setText(w);
        setEditable(false);
        setBackground(Color.white);
        setFont(new Font("MS Comic Sans", Font.BOLD, 30));
        setPreferredSize(new Dimension(200, 45));


    }

    public void setWord(String word) {
        this.word = word;
        setText(word);
    }

}