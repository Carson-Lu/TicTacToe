// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)
import java.awt.event.*; // Allows for events

public class MainPanel extends JPanel implements ActionListener {
    private GameScreen game = new GameScreen();
    private GameOverScreen end = new GameOverScreen(this);

    public MainPanel() {

    
        LayoutManager overlay = new OverlayLayout(this);
        setLayout(overlay);

        add(game, BorderLayout.CENTER);
        add(end, BorderLayout.CENTER);
    
    }

    public void actionPerformed(ActionEvent e) {
        game.resetGame();
        
    }

    
}
