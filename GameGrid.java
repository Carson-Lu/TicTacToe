import java.awt.*;
import javax.swing.*;


public class GameGrid extends JPanel{
    final static Integer WIDTH = 330;
    final static Integer HEIGHT = 430;
    
    public GameGrid() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vertical Lines
        g.drawLine(105, 75, 105, 355);
        g.drawLine(209, 75, 209, 355);

        // Horizontal Lines
        g.drawLine(5, 163, 310, 163);
        g.drawLine(5, 260, 310, 260);
    }


}
