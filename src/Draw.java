import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    private int level;

    Draw(int level) {
        this.level = level;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.white);
    }


    public void updateLevel(int level) {
        this.level = level;

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (level > -1) {
            g2.setStroke(new BasicStroke(10));
            g2.setColor(Color.black);
        }
        //sun
        if (level > 0){
            g2.setColor(Main.c6);
            g2.drawOval(50,10,60,60);
        }
        if (level > 1){
            g2.drawLine(130, 30, 160, 30);
            g2.drawLine(126, 65, 145, 75);
            g2.drawLine(90, 85, 100, 120);

        }


        //flowers
        if (level > 2){
            g2.setColor(Main.c2);

            g2.drawLine(47, 300, 47, 350);
            g2.drawLine(30, 320, 60, 320);

        }
        if (level > 3){
            g2.setColor(Main.c5);
            g2.drawRect(30, 260, 35, 35);
            g2.drawLine(47, 280, 47, 280);
        }

        if (level > 4){
            g2.setColor(Main.c2);
            g2.drawLine(447, 300, 447, 350);
            g2.drawLine(430, 320, 460, 320);
        }
        if (level > 5){
            g2.setColor(Main.c5);
            g2.drawRect(430, 260, 35, 35);
            g2.drawLine(447, 280, 447, 280);

        }


        if (level > 6) {
            // box bottom
            g2.setColor(Main.c4);
            g2.fillRect(300, 330, 90, 20);
        }
        if (level > 7) {
            // box 2
            g2.fillRect(300, 310, 90, 20);
        }
        if (level > 8) {
            // box 3
            g2.fillRect(300, 290, 90, 20);
        }
        if (level > 9) {
            // box 4
            g2.fillRect(300, 270, 90, 20);
        }
        if (level > 10) {
            // box 5
            g2.fillRect(300, 250, 90, 20);
        }

        // hanger
        if (level >11) {
            // face
            g2.drawOval(325, 100, 50, 50);
        }
        if (level >12) {
            // body
            g2.drawLine(350, 150, 350, 250);
        }

        if (level >13) {
            // hand right
            g2.drawLine(350, 150, 370, 200);
        }
        if (level > 14) {
            // hand left
            g2.drawLine(350, 150, 330, 200);
        }
        if (level > 15) {
            // with brock (5) legs 1
            g2.drawLine(350, 250, 400, 250);
        }
        if (level > 16) {
            // with brock (5) legs 2
            g2.drawLine(350, 250, 400, 220);
        }
        if (level > 17) {
            g2.drawLine(350, 50, 350, 100);

        }
        if (level > 18) {
            g2.drawLine(150, 50, 350, 50);

        }
        if (level > 19) {// not sure
            g2.drawLine(150, 50, 150, 350);

        }
        if (level > 20) {
            g2.drawLine(100, 350, 200, 350);

        }
        if (level > 21) {
            g2.drawLine(200, 50, 150, 100);

        }

        // deleting...
        if (level > 22) {
            // legs
            g2.setColor(Main.c3);
            // delete boxes
            // with brock (5) legs 1
            g2.drawLine(350, 250, 400, 250);
            // with brock (5) legs 2
            g2.drawLine(350, 250, 400, 220);
        }

        if (level > 23) {
            // box bottom
            g2.fillRect(300, 250, 90, 20);
        }
        if (level > 24) {
            // box 2
            g2.fillRect(300, 270, 90, 20);
        }
        if (level > 25) {
            // box 3
            g2.fillRect(300, 290, 90, 20);
        }

        if (level > 26) {
            // box 4
            g2.fillRect(300, 310, 90, 20);
            // box 5
            g2.fillRect(300, 330, 90, 20);
        }


        // add leg
        if (level > 22) {
            g2.setColor(Main.c4);
            g2.drawLine(350, 250, 330, 310);
        }
        if (level > 22) {
            g2.drawLine(350, 250, 370, 310);
            //game over
        }

    }

}
