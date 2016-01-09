package Employee;

import Employee.Buttons.LogoutButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by darek on 29.11.2015.
 */
public class InfoPanel extends JPanel {
    private MyFrame frame;
    private Employee.DataStructures.Employee user;
    private DatabaseModule dbModule;

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

    public InfoPanel(MyFrame frame, Employee.DataStructures.Employee user, DatabaseModule dbModule) {
        super();
        this.dbModule = dbModule;
        this.user = user;
        this.frame = frame;

        informations = new JLabel("Informacje o zalogowanym pracowniku");
        informations.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginDescription = new JLabel("Login: ");
        login = new JLabel(user.login);
        nameDescription = new JLabel("Imię: ");
        name = new JLabel(user.name);
        surnameDescription = new JLabel("Nazwisko: ");
        surname = new JLabel(user.surname);
        addressDescription = new JLabel("Adres: ");
        address = new JLabel(user.address);
        dateOfBirthDescription = new JLabel("Data urodzenia: ");
        dateOfBirth = new JLabel(user.date_of_birth.toString());
        privilegesDescription = new JLabel("Poziom: ");
        privileges = new JLabel(String.valueOf(user.privileges));

        logoutButton = new LogoutButton("Wyloguj", frame);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        panel.setMaximumSize(new Dimension(500, 180));

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
