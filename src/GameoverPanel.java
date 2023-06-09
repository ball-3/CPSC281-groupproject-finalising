
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameoverPanel extends JPanel {
    private String result;
    private String word;
    private Color color;

    public GameoverPanel(boolean clear, String w) {
        setOpaque(true);
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(1000, 650));
        
        word = w;

        if (clear) {
            result = "CLEAR";
            color = Main.c1;
        } else {
            result = "GAME OVER";
            color = Main.c5;
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0, 0, 1000, 650);

        g2.setColor(Main.c3);
        g2.setFont(new Font("MS Comic Sans", Font.BOLD, 80));
        g2.drawString(result, 150, 100);
        g2.setFont(new Font("MS Comic Sans", Font.BOLD, 40));
        g2.drawString("Answer was:", 100, 300);
        g2.drawString(word, 400, 300);

    }

}