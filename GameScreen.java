// Importing Packages
import javax.swing.*; // User Interface
import javax.swing.plaf.DimensionUIResource;
import java.awt.*; // User interface (Abstract Windowing Toolkit)


public class GameScreen extends JPanel {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private JLabel labelTurn;
    private String turn = "O";
    private JButton[] buttons = new JButton[9];


    public GameScreen(MainPanel mPanel) {


        setMinimumSize(new Dimension(330, 330));

        // Change x and y coordinate when adding GridBagLayout components
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.BOTH;
        }

        if (shouldWeightX) {
            c.weightx = 1;
            c.weighty = 1;
        }

        setLayout(new GridBagLayout());

        // Creating Label and properties
        labelTurn = new JLabel(turn + "'s turn", SwingConstants.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; // x location
        c.gridy = 0; // y location
        c.gridwidth = 3; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(5, 5, 5, 5); // Whitespace
        add(labelTurn, c);

        // Creating buttons and properties
        for(int i = 0; i < buttons.length; i++) {
            JButton button = new JButton("");
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(true);
            button.setPreferredSize(new DimensionUIResource(50, 50));
            button.addActionListener(mPanel);
            button.addMouseListener(mPanel);
            button.setActionCommand(Integer.toString(i));
            buttons[i] = button;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = i % 3; // x location
            c.gridy = (i / 3) + 1; // y location
            c.gridwidth = 1; // width
            c.gridheight = 1; // height
            c.ipady = 0; // Extra vertical space
            c.insets = new Insets(5, 5, 5, 5); // Whitespace
            add(button, c);
        }

        // End of Buttons
    }

    // Places X or O
    public void userPlaced(String e) {
        Integer index = Integer.valueOf(e);

        // Makes button unclickable
        buttons[index].setEnabled(false);
        buttons[index].setText(turn);
        buttons[index].setBorderPainted(false);

    }

 

    // Brings game to the next state
    public void nextState() {

        // Changes the turn
        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }

        // Sets label
        labelTurn.setText(turn + "'s turn");

        determineDraw();

        // Check Vertical and horizontal for winner
        for (int i = 0; i < 3; i++) {
            String vert = (buttons[i].getText() + buttons[i + 3].getText() + buttons[i + 6].getText());
            String hori = (buttons[i*3].getText() + buttons[(i*3) + 1].getText() + buttons[(i*3) + 2].getText());

            determineWinner(vert);
            determineWinner(hori);
        }

        // Checking diagonally for winner
        String diagD = (buttons[0].getText() + buttons[4].getText() + buttons[8].getText()); // diagonally downwards 
        String diagU = (buttons[2].getText() + buttons[4].getText() + buttons[6].getText()); 


        determineWinner(diagD);
        determineWinner(diagU);
    
    }

    // Once winner has been determined, this will run. Disables all buttons and displays who won
    public void setWinner() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setBorderPainted(false);
        }

        labelTurn.setText(turn + " wins!");
    }

    // Determine if anyone has one based on current game state
    public void determineWinner(String line) {
        switch (line) {
            case "XXX":
                turn = "X";
                setWinner();
                break;
            case "OOO":
                turn = "O";
                setWinner();
                break;
        }
    }

    // Will determine draw if all boxes have been filled before someone has one, call determine winner after to check if someone has won
    public void determineDraw() {
        String boardState = "";

        for (int i = 0; i < buttons.length; i++) {
            boardState += buttons[i].getText();
        }

        if (boardState.length() == 9) {
            labelTurn.setText("Draw!");
        }
    }

    // Resets the game 
    public void resetGame() {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBorderPainted(true);
        }

        turn = "O";
    }

    
    public boolean gameEnded(JButton[] newButtons) {
        for (Integer i = 0; i < newButtons.length; i++) {
            if (newButtons[i].isEnabled()) {
                return false;
            }
        }

        return true;
    }


    // Getters
    // Gets all the buttons
    public JButton[] getButtons() {
        return buttons;
    }

    // Gets turn
    public String getTurn() {
        return turn;
    }

    public JLabel getLabel() {
        return labelTurn;
    }


    // Setters
    public void setButtons(JButton[] newButtons) {
        this.buttons = newButtons;
    }

    public void setTurn(String newTurn) {
        this.turn = newTurn;
    }

    public void setLabel(JLabel newLabel) {
        this.labelTurn = newLabel;
    }

    public void setHoverLabel() {
        
    }

}
