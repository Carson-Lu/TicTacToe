// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)

public class TicTacToe extends JFrame{

    public TicTacToe() {


        add(new MainPanel());

        // Setting JFrame Properties
        setResizable(false); // Frame is not resizable
        setMinimumSize(new Dimension(330, 330)); 
        setVisible(true); // Frame is visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    
    public static void main (String[] args) {
        new TicTacToe();
    }   
}
