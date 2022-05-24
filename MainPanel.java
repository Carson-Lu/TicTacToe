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

        JButton b = (JButton) e.getSource();

        if (b.isEnabled()) {
            b.setText(turn);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

            if (turn.equals("X")) {
                b.setForeground(Color.BLUE);
            } else {
                b.setForeground(Color.RED);
            }

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton b = (JButton) e.getSource();

        if (b.isEnabled()) {
            b.setText("");
        }
        
        
    }

    
}
