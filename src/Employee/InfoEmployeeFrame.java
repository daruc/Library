package Employee;

import Employee.DataStructures.Employee;
import Employee.DatabaseModule;
import Employee.MyFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by darek on 06.01.2016.
 */
public class InfoEmployeeFrame extends MyFrame {
    private Employee employee;

    private JLabel labelLogin;
    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelAddress;
    private JLabel labelDate;
    private JLabel labelPrivileges;

    private JLabel login;
    private JLabel name;
    private JLabel surname;
    private JLabel address;
    private JLabel date;
    private JLabel privileges;

    private JPanel panelLogin;
    private JPanel panelName;
    private JPanel panelSurname;
    private JPanel panelAddress;
    private JPanel panelDate;
    private JPanel panelPrivileges;

    private void drawGUI() {
        labelLogin = new JLabel("Login: ");
        labelName = new JLabel("Imię: ");
        labelSurname = new JLabel("Nazwisko: ");
        labelAddress = new JLabel("Adres: ");
        labelDate = new JLabel("Data urodzenia: ");
        labelPrivileges = new JLabel("Poziom: ");

        login = new JLabel(employee.login);
        name = new JLabel(employee.name);
        surname = new JLabel(employee.surname);
        address = new JLabel(employee.address);
        date = new JLabel(employee.date_of_birth.toString());
        privileges = new JLabel(String.valueOf(employee.privileges));

        Font font = new Font(login.getFont().getName(), Font.BOLD, login.getFont().getSize());
        labelLogin.setFont(font);
        labelName.setFont(font);
        labelSurname.setFont(font);
        labelAddress.setFont(font);
        labelDate.setFont(font);
        labelPrivileges.setFont(font);

        panelLogin = new JPanel();
        panelName = new JPanel();
        panelSurname = new JPanel();
        panelAddress = new JPanel();
        panelDate = new JPanel();
        panelPrivileges = new JPanel();

        panelLogin.setBorder(new EmptyBorder(10,10,10,10));
        panelName.setBorder(new EmptyBorder(10,10,10,10));
        panelSurname.setBorder(new EmptyBorder(10,10,10,10));
        panelAddress.setBorder(new EmptyBorder(10,10,10,10));
        panelDate.setBorder(new EmptyBorder(10,10,10,10));
        panelPrivileges.setBorder(new EmptyBorder(10,10,10,10));

        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.X_AXIS));
        panelName.setLayout(new BoxLayout(panelName, BoxLayout.X_AXIS));
        panelSurname.setLayout(new BoxLayout(panelSurname, BoxLayout.X_AXIS));
        panelAddress.setLayout(new BoxLayout(panelAddress, BoxLayout.X_AXIS));
        panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
        panelPrivileges.setLayout(new BoxLayout(panelPrivileges, BoxLayout.X_AXIS));

        panelLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelName.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSurname.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrivileges.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelLogin.add(labelLogin);
        panelLogin.add(login);
        panelName.add(labelName);
        panelName.add(name);
        panelSurname.add(labelSurname);
        panelSurname.add(surname);
        panelAddress.add(labelAddress);
        panelAddress.add(address);
        panelDate.add(labelDate);
        panelDate.add(date);
        panelPrivileges.add(labelPrivileges);
        panelPrivileges.add(privileges);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panelLogin);
        add(panelName);
        add(panelSurname);
        add(panelAddress);
        add(panelDate);
        add(panelPrivileges);
    }

    public InfoEmployeeFrame(String title, DatabaseModule databaseModule,
                             Employee employee) {
        super(title, databaseModule);
        this.employee = employee;

        drawGUI();
        setVisible(true);
        setSize(300, 240);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
