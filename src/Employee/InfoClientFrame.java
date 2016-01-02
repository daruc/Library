package Employee;

import Employee.DataStructures.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by darek on 02.01.2016.
 */
public class InfoClientFrame extends MyFrame {
    private Client client;

    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelAddress;
    private JLabel labelBirth;
    private JLabel labelMaxBorrowed;
    private JLabel labelBorrowed;
    private JLabel labelDaysToReturnBook;
    private JLabel labelPrivileges;

    private JLabel name;
    private JLabel surname;
    private JLabel address;
    private JLabel birth;
    private JLabel maxBorrowed;
    private JLabel borrowed;
    private JLabel daysToReturnBooks;
    private JLabel privileges;

    private JPanel panelName;
    private JPanel panelSurname;
    private JPanel panelAddress;
    private JPanel panelBirth;
    private JPanel panelMaxBorrowed;
    private JPanel panelBorrowed;
    private JPanel panelDaysToReturnBook;
    private JPanel panelPrivileges;

    public void drawGUI() {
        labelName = new JLabel("Imię: ");
        labelSurname = new JLabel("Nazwisko: ");
        labelAddress = new JLabel("Adres: ");
        labelBirth = new JLabel("Data urodzenia: ");
        labelMaxBorrowed = new JLabel("Limit wypożyczeń: ");
        labelBorrowed = new JLabel("Wypożyczonych: ");
        labelDaysToReturnBook = new JLabel("Dni do oddania: ");
        labelPrivileges = new JLabel("Poziom: ");

        name = new JLabel(client.name);
        surname = new JLabel(client.surname);
        address = new JLabel(client.address);
        birth = new JLabel(client.date_of_birth.toString());
        maxBorrowed = new JLabel(Integer.toString(client.max_borrowed));
        borrowed = new JLabel(Integer.toString(client.borrowed));
        daysToReturnBooks = new JLabel(Integer.toString(client.days_to_return_book));
        privileges = new JLabel(Integer.toString(client.privileges));

        panelName = new JPanel();
        panelSurname = new JPanel();
        panelAddress = new JPanel();
        panelBirth = new JPanel();
        panelMaxBorrowed = new JPanel();
        panelBorrowed = new JPanel();
        panelDaysToReturnBook = new JPanel();
        panelPrivileges = new JPanel();

        panelName.setBorder(new EmptyBorder(10,10,10,10));
        panelSurname.setBorder(new EmptyBorder(10,10,10,10));
        panelAddress.setBorder(new EmptyBorder(10,10,10,10));
        panelBirth.setBorder(new EmptyBorder(10,10,10,10));
        panelMaxBorrowed.setBorder(new EmptyBorder(10,10,10,10));
        panelBorrowed.setBorder(new EmptyBorder(10,10,10,10));
        panelDaysToReturnBook.setBorder(new EmptyBorder(10,10,10,10));
        panelPrivileges.setBorder(new EmptyBorder(10,10,10,10));

        panelName.setLayout(new BoxLayout(panelName, BoxLayout.X_AXIS));
        panelSurname.setLayout(new BoxLayout(panelSurname, BoxLayout.X_AXIS));
        panelAddress.setLayout(new BoxLayout(panelAddress, BoxLayout.X_AXIS));
        panelBirth.setLayout(new BoxLayout(panelBirth, BoxLayout.X_AXIS));
        panelMaxBorrowed.setLayout(new BoxLayout(panelMaxBorrowed, BoxLayout.X_AXIS));
        panelBorrowed.setLayout(new BoxLayout(panelBorrowed, BoxLayout.X_AXIS));
        panelDaysToReturnBook.setLayout(new BoxLayout(panelDaysToReturnBook, BoxLayout.X_AXIS));
        panelPrivileges.setLayout(new BoxLayout(panelPrivileges, BoxLayout.X_AXIS));

        panelName.setBorder(new EmptyBorder(10,10,10,10));
        panelSurname.setBorder(new EmptyBorder(10,10,10,10));
        panelAddress.setBorder(new EmptyBorder(10,10,10,10));
        panelBirth.setBorder(new EmptyBorder(10,10,10,10));
        panelMaxBorrowed.setBorder(new EmptyBorder(10,10,10,10));
        panelBorrowed.setBorder(new EmptyBorder(10,10,10,10));
        panelDaysToReturnBook.setBorder(new EmptyBorder(10,10,10,10));
        panelPrivileges.setBorder(new EmptyBorder(10,10,10,10));

        panelName.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSurname.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBirth.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMaxBorrowed.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBorrowed.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDaysToReturnBook.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrivileges.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelName.add(labelName);
        panelName.add(name);
        panelSurname.add(labelSurname);
        panelSurname.add(surname);
        panelAddress.add(labelAddress);
        panelAddress.add(address);
        panelBirth.add(labelBirth);
        panelBirth.add(birth);
        panelMaxBorrowed.add(labelMaxBorrowed);
        panelMaxBorrowed.add(maxBorrowed);
        panelBorrowed.add(labelBorrowed);
        panelBorrowed.add(borrowed);
        panelDaysToReturnBook.add(labelDaysToReturnBook);
        panelDaysToReturnBook.add(daysToReturnBooks);
        panelPrivileges.add(labelPrivileges);
        panelPrivileges.add(privileges);

        Font font = new Font(name.getFont().getName(), Font.BOLD, name.getFont().getSize());
        labelName.setFont(font);
        labelSurname.setFont(font);
        labelAddress.setFont(font);
        labelBirth.setFont(font);
        labelMaxBorrowed.setFont(font);
        labelBorrowed.setFont(font);
        labelDaysToReturnBook.setFont(font);
        labelPrivileges.setFont(font);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panelName);
        add(panelSurname);
        add(panelAddress);
        add(panelBirth);
        add(panelMaxBorrowed);
        add(panelBorrowed);
        add(panelDaysToReturnBook);
        add(panelPrivileges);
    }

    public InfoClientFrame(String title, DatabaseModule dbModule, Client client) {
        super(title, dbModule);
        this.client = client;

        drawGUI();
        setVisible(true);
        setSize(300, 310);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
