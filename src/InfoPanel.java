import javax.swing.*;
import java.awt.*;

/**
 * Created by darek on 29.11.2015.
 */
public class InfoPanel extends JPanel {
    private MyFrame frame;
    private String user;
    private DatabaseModule dbModule;

    private String[] userInfo;

    private JPanel panel;

    private JLabel informations;
    private JLabel loginDescription;
    private JLabel login;
    private JLabel nameDescription;
    private JLabel name;
    private JLabel surnameDescription;
    private JLabel surname;
    private JLabel addressDescription;
    private JLabel address;
    private JLabel dateOfBirthDescription;
    private JLabel dateOfBirth;
    private JLabel privilegesDescription;
    private JLabel privileges;

    private LogoutButton logoutButton;

    public InfoPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        super();
        this.dbModule = dbModule;
        this.user = user;
        this.frame = frame;

        try {
            userInfo = dbModule.getEmployee(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


        informations = new JLabel("Informacje o zalogowanym pracowniku");
        loginDescription = new JLabel("Login: ");
        login = new JLabel(userInfo[0]);
        nameDescription = new JLabel("Imię: ");
        name = new JLabel(userInfo[1]);
        surnameDescription = new JLabel("Nazwisko: ");
        surname = new JLabel(userInfo[2]);
        addressDescription = new JLabel("Adres: ");
        address = new JLabel(userInfo[3]);
        dateOfBirthDescription = new JLabel("Data urodzenia: ");
        dateOfBirth = new JLabel(userInfo[4]);
        privilegesDescription = new JLabel("Poziom: ");
        privileges = new JLabel(userInfo[5]);

        logoutButton = new LogoutButton("Wyloguj", frame);
        logoutButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        Font font = login.getFont();
        font = new Font(font.getName(), font.getStyle(), font.getSize()+8);
        informations.setFont(font);
        font = new Font(font.getName(), font.getStyle(), font.getSize()-4);
        loginDescription.setFont(font);
        login.setFont(font);
        nameDescription.setFont(font);
        name.setFont(font);
        surnameDescription.setFont(font);
        surname.setFont(font);
        addressDescription.setFont(font);
        address.setFont(font);
        dateOfBirthDescription.setFont(font);
        dateOfBirth.setFont(font);
        privilegesDescription.setFont(font);
        privileges.setFont(font);


        panel = new JPanel();
        panel.setMaximumSize(new Dimension(300, 180));

        panel.setLayout(new GridLayout(6, 2));
        panel.add(loginDescription);
        panel.add(login);
        panel.add(nameDescription);
        panel.add(name);
        panel.add(surnameDescription);
        panel.add(surname);
        panel.add(addressDescription);
        panel.add(address);
        panel.add(dateOfBirthDescription);
        panel.add(dateOfBirth);
        panel.add(privilegesDescription);
        panel.add(privileges);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(informations);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(panel);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(logoutButton);
    }
}
