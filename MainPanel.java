// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)
import java.awt.event.*; // Allows for events

public class MainPanel extends JPanel implements ActionListener, MouseListener {
    final static Color O_COLOR = Color.RED;
    final static Color X_COLOR = Color.BLUE;

    private GameScreen game = new GameScreen(this);
    private GameGrid grid = new GameGrid();

    public MainPanel() {
        LayoutManager overlay = new OverlayLayout(this);
        setLayout(overlay);
        
        add(game, overlay);
        add(grid, overlay);
    }

    public void actionPerformed(ActionEvent e) {
        String uInput = e.getActionCommand();

        // User pressed "Copy board" button
        if (uInput.equals("copy")) {
            game.writeToClipboard(game.boardToText());

        // User pressed Reset Game button
        } else if (uInput.equals("Reset")) {
            game.disableReset();
            game.setLabelColor(Color.BLACK);
            game.resetGame();
            
        // User placed item on tictactoe game board
        } else {
            game.userPlaced(uInput);
            game.nextState();

        }
 
        if (game.hasGameEnded(game.getButtons())) {
            if (game.getTurn().equals("X")) {
                game.setLabelColor(X_COLOR);
            } else {
                game.setLabelColor(O_COLOR);
            }
            game.enableReset();
        } 
        game.updateColours();
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

    // Hover feature for game board where it shows "X" or "O" on button depending on turn
    @Override
    public void mouseEntered(MouseEvent e) {
        String turn = game.getTurn();

        JButton b = (JButton) e.getSource();

        if (b.isEnabled()) {
            b.setText(turn);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

            if (turn.equals("X")) {
                b.setForeground(X_COLOR);
            } else {
                b.setForeground(O_COLOR);
            }

        }
    }

    // Completes hover feature, removes "X" or "O" once mouse exits button
    @Override
    public void mouseExited(MouseEvent e) {
        JButton b = (JButton) e.getSource();

        if (b.isEnabled()) {
            b.setText("");
        }
    }
}
