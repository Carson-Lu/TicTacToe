// Importing Packages
import javax.swing.*; // User Interface

import java.awt.*; // User interface (Abstract Windowing Toolkit)

public class GameOverScreen extends JPanel{

    private JButton reset = new JButton("Play Again");

    public GameOverScreen(MainPanel mPanel) {
        //setBackground(new Color(213, 134, 145, 123));
        disableReset();
        reset.addActionListener(mPanel);

        add(reset, BorderLayout.CENTER);


    }


    public void disableReset() {
        reset.setOpaque(false);
        reset.setContentAreaFilled(false);
        reset.setBorderPainted(false);
        reset.setEnabled(false);
        reset.setVisible(false);

    }


    public void enableReset() {
        reset.setOpaque(true);
        reset.setContentAreaFilled(true);
        reset.setBorderPainted(true);
        reset.setEnabled(true);
        reset.setVisible(true);

    }

}
