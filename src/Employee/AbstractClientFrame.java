package Employee;

import Employee.Buttons.AddClientButton;
import Employee.DocumentFilters.PositiveIntegerDocumentFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 02.01.2016.
 */
public abstract class AbstractClientFrame extends MyFrame {
    protected JLabel labelLogin;
    protected JLabel labelName;
    protected JLabel labelSurname;
    protected JLabel labelAddress;
    protected JLabel labelDateOfBirth;
    protected JLabel labelMaxBorrowed;
    protected JLabel labelDaysToReturnBook;
    protected JLabel labelPrivileges;
    protected JLabel labelPassword;

    protected JTextField login;
    protected JTextField name;
    protected JTextField surname;
    protected JTextField address;
    protected JTextField dateOfBirth;
    protected JTextField maxBorrowed;
    protected JTextField daysToReturnBook;
    protected JTextField privileges;
    protected JTextField password;

    protected JButton cancelButton;

    protected JPanel panelFields;
    protected JPanel panelButtons;

    public void drawGUI() {
        labelLogin = new JLabel("Login: ");
        labelName = new JLabel("Imię: ");
        labelSurname = new JLabel("Nazwisko: ");
        labelAddress = new JLabel("Adres: ");
        labelDateOfBirth = new JLabel("Data urodzenia: ");
        labelMaxBorrowed = new JLabel("Limit wypożyczeń: ");
        labelDaysToReturnBook = new JLabel("Dni do oddania: ");
        labelPrivileges = new JLabel("Poziom: ");
        labelPassword = new JLabel("Hasło: ");

        login = new JTextField();
        name = new JTextField();
        surname = new JTextField();
        address = new JTextField();
        dateOfBirth = new JTextField();
        maxBorrowed = new JTextField();
        daysToReturnBook = new JTextField();
        privileges = new JTextField();
        password = new JTextField();

        panelFields = new JPanel();
        panelFields.setLayout(new GridLayout(0, 2));

        panelFields.add(labelLogin);
        panelFields.add(login);
        panelFields.add(labelName);
        panelFields.add(name);
        panelFields.add(labelSurname);
        panelFields.add(surname);
        panelFields.add(labelAddress);
        panelFields.add(address);
        panelFields.add(labelDateOfBirth);
        panelFields.add(dateOfBirth);
        panelFields.add(labelMaxBorrowed);
        panelFields.add(maxBorrowed);
        panelFields.add(labelDaysToReturnBook);
        panelFields.add(daysToReturnBook);
        panelFields.add(labelPrivileges);
        panelFields.add(privileges);
        panelFields.add(labelPassword);
        panelFields.add(password);

        panelFields.setSize(200, 200);
        panelFields.setMaximumSize(new Dimension(260, 280));

        cancelButton = new JButton("Zrezygnuj");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        cancelButton.setPreferredSize(new Dimension(80, 25));

        PlainDocument doc = (PlainDocument) maxBorrowed.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());

        doc = (PlainDocument) daysToReturnBook.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());

        doc = (PlainDocument) privileges.getDocument();
        doc.setDocumentFilter((new PositiveIntegerDocumentFilter()));

        panelButtons = new JPanel();
        panelButtons.add(cancelButton);

        setSaveButton();

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panelFields);
        add(panelButtons);
    }

    public AbstractClientFrame(String title, DatabaseModule dbModule) {
        super(title, dbModule);
        drawGUI();
        setSize(270, 250);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    abstract public void setSaveButton();
}
