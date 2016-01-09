package Employee.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 28.11.2015.
 */
public class LoginButton extends JButton {

    private LoginInterface loginInterface;

    public LoginButton(String string, LoginInterface loginInterface) {
        super(string);

        this.loginInterface = loginInterface;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginInterface.login();
            }
        });
    }

    public interface LoginInterface {
        public void login();
    }
}
