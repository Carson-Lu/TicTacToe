// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)
import java.awt.event.*; // Allows for events

public class GameScreen extends JPanel implements ActionListener {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private JButton[] buttons = new JButton[9];
    private String turn = "O";
    private JLabel labelTurn;


    public GameScreen() {



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

        labelTurn = new JLabel(turn + "'s turn", SwingConstants.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; // x location
        c.gridy = 0; // y location
        c.gridwidth = 3; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(5, 5, 5, 5); // Whitespace
        add(labelTurn, c);


        for(int i = 0; i < buttons.length; i++) {
            JButton button = new JButton("");
            button.addActionListener(this);
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


    public void actionPerformed(ActionEvent e) {
        String uInput = e.getActionCommand();
        Integer index = Integer.valueOf(uInput);

        // System.out.println(e.getActionCommand());

        // Makes button unclickable
        buttons[index].setEnabled(false);
        buttons[index].setText(turn);

        nextState();



    }

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


    public void setWinner() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }

        labelTurn.setText(turn + " wins!");
    }

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



    public void determineDraw() {
        String boardState = "";

        for (int i = 0; i < buttons.length; i++) {
            boardState += buttons[i].getText();
        }
        System.out.println(boardState);
        System.out.println(boardState.length());

        if (boardState.length() == 9) {
            labelTurn.setText("Draw!");
        }
    }

    // Getters
    public JButton[] getState() {
        return buttons;
    }

    public String getTurn() {
        return turn;
    }

    public static void resetGame(JButton[] buttons, String turn) {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            
        }

        turn = "O";
    }



    



}
