package Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 29.11.2015.
 */
public class LogoutButton extends JButton {

    public LogoutButton(String string, MyFrame parent) {
        super(string);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(false);
                EventQueue.invokeLater(() -> new LoginFrame("System biblioteczny", parent.getDatabaseModule()));
                parent.dispose();
            }
        });
    }

}
