// Importing Packages
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)

public class TicTacToe extends JFrame{

    final static Integer WIDTH = 330;
    final static Integer HEIGHT = 400;

    public TicTacToe() {
        add(new MainPanel());

        // Setting JFrame Properties
        setDefaultLookAndFeelDecorated(true);
        setResizable(false); // Frame is not resizable
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT)); 
        setVisible(true); // Frame is visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe();
            }
        });
    }   
}
