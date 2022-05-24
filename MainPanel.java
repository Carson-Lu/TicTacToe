// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)
import java.awt.event.*; // Allows for events

public class MainPanel extends JPanel implements ActionListener, MouseListener {
    private GameScreen game = new GameScreen(this);
    private GameOverScreen end = new GameOverScreen(this);

    public MainPanel() {

    
        LayoutManager overlay = new OverlayLayout(this);
        setLayout(overlay);

        add(game, BorderLayout.CENTER);
        add(end, BorderLayout.CENTER);
    
    }

    public void actionPerformed(ActionEvent e) {
        String uInput = e.getActionCommand();
        end.disableReset();
        

        if (uInput.equals("Play Again")) {
            game.resetGame();
        } else {
            game.userPlaced(uInput);
            game.nextState();
        }

        if (game.gameEnded(game.getButtons())) {
            end.enableReset();
        } 

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String turn = game.getTurn();

        JButton j = (JButton) e.getSource();

        if (j.isEnabled()) {
            j.setText(turn);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton j = (JButton) e.getSource();

        if (j.isEnabled()) {
            j.setText("");
        }
        
        
    }

    
}
