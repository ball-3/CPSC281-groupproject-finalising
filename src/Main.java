import javax.swing.*;

public class Main {
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
