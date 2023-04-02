import javax.swing.*;
import java.awt.*;

public class Main {

    static Color c1 = new Color(70,104,101,255);
    static Color c2 = new Color(126,135,114,255);
    static Color c3 = new Color(203,196,188,255);
    static Color c4 = new Color(46,46,54,255);
    static Color c5 = new Color(165,120,101,255);
    static Color c6 = new Color(202,168,105,255);

    static JFrame main;

    public static void main(String[] args) {
        main = new JFrame();

        Menu test = new Menu();
        main.add(test.getPanel());
        setupFrame();
    }

    public static void setupFrame()
    {
        main.setSize(1000,800);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
