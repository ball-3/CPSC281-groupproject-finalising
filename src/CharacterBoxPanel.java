import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CharacterBoxPanel extends JPanel {
    ArrayList<CharacterBox> boxes = new ArrayList<CharacterBox>();

    public CharacterBoxPanel(String w) {
        super(new FlowLayout());
        setBackground(Color.white);

        for (int i = 0; i < w.length(); i++) {
            CharacterBox box = new CharacterBox(w.charAt(i));
            boxes.add(box);
            add(box);
            // add(new JButton());
        }

    }

    public void updateCharBoxes(String w) {
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) != '0') {
                boxes.get(i).setGuessedTrue();
                boxes.get(i).repaint();
            }

        }

    }

}