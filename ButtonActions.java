// Importing Packages
import java.awt.event.*; // Allows for events

public class ButtonActions implements ActionListener {



    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        System.out.println(e.getSource());


    
    }
}
    
