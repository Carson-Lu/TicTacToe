// Importing Packages
import javax.swing.*; // User Interface
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.*; // User interface (Abstract Windowing Toolkit)


public class GameScreen extends JPanel {

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    final static Integer BUTTONWIDTH = 90;
    final static Integer BUTTONHEIGHT = BUTTONWIDTH;
    final static Integer WIDTH = 330;
    private JLabel labelTurn;
    private String turn = "O";
    private JButton[] buttons = new JButton[9];
    private JButton buttonShare, buttonReset;


    public GameScreen(MainPanel mPanel) {

        setOpaque(false);

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

        buttonShare = new JButton("<html>Share <br> Board</html>");
        buttonShare.setMaximumSize(new Dimension(50, 50));
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0; // x location
        c.gridy = 0; // y location
        c.gridwidth = 1; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(0, 0, 0, 0); // Padding if desired
        add(buttonShare, c);

        // Creating Label and properties
        labelTurn = new JLabel(turn + "'s turn", SwingConstants.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1; // x location
        c.gridy = 0; // y location
        c.gridwidth = 1; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(0, 0, 0, 0); // Padding if desired
        add(labelTurn, c);

        buttonReset = new JButton("<html><center> Play <br> Again </center><html>");
        buttonReset.setMaximumSize(new Dimension(50, 50));
        buttonReset.addActionListener(mPanel);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2; // x location
        c.gridy = 0; // y location
        c.gridwidth = 1; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(0, 0, 0, 0); // Padding if desired
        add(buttonReset, c);

        // Creating buttons and properties
        for(int i = 0; i < buttons.length; i++) {
            JButton button = new JButton("");
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new DimensionUIResource(BUTTONWIDTH, BUTTONHEIGHT));
            button.setMinimumSize(new DimensionUIResource(BUTTONWIDTH, BUTTONHEIGHT));
            button.setMaximumSize(new DimensionUIResource(BUTTONWIDTH, BUTTONHEIGHT));
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
            c.insets = new Insets(0,0,0,0); 
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
        updateLabel(turn);

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
        }

        turn = "O";
        updateLabel(turn);
    }

    
    public boolean hasGameEnded(JButton[] newButtons) {
        for (Integer i = 0; i < newButtons.length; i++) {
            if (newButtons[i].isEnabled()) {
                return false;
            }
        }

        return true;
    }

    public void updateLabel(String turn) {
        labelTurn.setText(this.turn + "'s turn");
    }

    public void updateColours() {
        for (Integer i = 0; i < buttons.length; i++) {
            buttons[i].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return null;
                }
            });
        }

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

    public void setLabelColor(Color c) {
        labelTurn.setForeground(c);
    }

    
    public void disableReset() {
        buttonReset.setOpaque(false);
        buttonReset.setContentAreaFilled(false);
        buttonReset.setBorderPainted(false);
        buttonReset.setEnabled(false);
        buttonReset.setVisible(false);

    }

    public void enableReset() {
        buttonReset.setOpaque(true);
        buttonReset.setContentAreaFilled(true);
        buttonReset.setBorderPainted(true);
        buttonReset.setEnabled(true);
        buttonReset.setVisible(true);

    }


}

