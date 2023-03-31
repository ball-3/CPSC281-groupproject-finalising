
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameoverPanel extends JPanel {
    private String result;

    public GameoverPanel(boolean clear) {
        setPreferredSize(new Dimension(1000, 800));
        JButton restart = new JButton("menu");
        restart.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // new frame??

            }

        });
        add(restart);

        if (clear) {
            result = "CLEAR";
            setBackground(Color.CYAN);
        } else
            result = "GAME OVER";
        setBackground(Color.red);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("MS Comic Sans", Font.BOLD, 40));
        g2.drawString(result, 100, 100);

    }

}