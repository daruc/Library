package Employee;

import Employee.Buttons.LoginButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 28.11.2015.
 */
public class LoginFrame extends MyFrame implements LoginButton.LoginInterface {

    private JLabel titleLabel;

    @Override
    public void login() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (getDatabaseModule().checkLoginAndPassword(login, password)) {
            setVisible(false);
            EventQueue.invokeLater(() -> new MainFrame("System biblioteczny", login, getDatabaseModule()));
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy login lub hasło.", "Logowanie nieudane",
                    JOptionPane.OK_OPTION);
        }
    }

    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private LoginButton loginButton;
    private JButton exitButton;

    private void buildGUI() {
        titleLabel = new JLabel("System biblioteczny - ekran logowania");
        loginLabel = new JLabel("Login");
        passwordLabel = new JLabel("Hasło");
        loginField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new LoginButton("Wejdź", this);
        exitButton = new JButton("Opuść program");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        Font font = new Font(loginButton.getFont().getName(), Font.BOLD, loginButton.getFont().getSize());
        loginButton.setFont(font);

        font = new Font(titleLabel.getFont().getName(), Font.BOLD, titleLabel.getFont().getSize() + 5);
        titleLabel.setFont(font);


        setLayout(null);
        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;

        jPanel.add(loginLabel, c);

        c.gridy = 2;
        jPanel.add(passwordLabel, c);

        c.gridx = 2;
        c.gridy = 1;
        jPanel.add(loginField, c);

        c.gridy = 2;
        jPanel.add(passwordField, c);

        c.gridy = 3;
        jPanel.add(loginButton, c);
        c.gridy = 4;
        jPanel.add(exitButton, c);

        jPanel.setLocation(80, 80);
        jPanel.setSize(200, 100);
        titleLabel.setSize(350, 50);
        titleLabel.setLocation(50, 10);

        add(titleLabel);
        add(jPanel);
        setResizable(false);
        setSize(430, 250);
    }

    public LoginFrame(String title, DatabaseModule dbModule) throws HeadlessException {
        super(title, dbModule);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildGUI();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
