import javax.swing.JComponent;
import java.awt.*;

public class CharacterBox extends JComponent {

    private char boxWord;
    private boolean isGuessed = false;

    public CharacterBox(char c) {
        setPreferredSize(new Dimension(47, 47));
        boxWord = c;
        if (c == ' ')
            isGuessed = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Main.c5);
        System.out.print(isGuessed + " c = " + boxWord + ", ");
        if (isGuessed) {
            g2.setFont(new Font("Verdana", Font.BOLD, 30));
            g2.setColor(Main.c1);
            g2.drawString(String.valueOf(boxWord), 15, 30);
        } else {
            g2.fillRect(0, 0, 45, 45);

        }
    }

    public void setGuessedTrue() {
        this.isGuessed = true;
    }

}
