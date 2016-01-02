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
public class AddClientFrame extends MyFrame {

    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelAddress;
    private JLabel labelDateOfBirth;
    private JLabel labelMaxBorrowed;
    private JLabel labelDaysToReturnBook;
    private JLabel labelPrivileges;
    private JLabel labelPassword;

    private JTextField name;
    private JTextField surname;
    private JTextField address;
    private JTextField dateOfBirth;
    private JTextField maxBorrowed;
    private JTextField daysToReturnBook;
    private JTextField privileges;
    private JTextField password;

    private JButton cancelButton;
    private AddClientButton saveButton;

    private JPanel panelFields;
    private JPanel panelButtons;

    public void drawGUI() {
        labelName = new JLabel("Imię: ");
        labelSurname = new JLabel("Nazwisko: ");
        labelAddress = new JLabel("Adres: ");
        labelDateOfBirth = new JLabel("Data urodzenia: ");
        labelMaxBorrowed = new JLabel("Limit wypożyczeń: ");
        labelDaysToReturnBook = new JLabel("Dni do oddania: ");
        labelPrivileges = new JLabel("Poziom: ");
        labelPassword = new JLabel("Hasło: ");

        name = new JTextField();
        surname = new JTextField();
        address = new JTextField();
        dateOfBirth = new JTextField();
        maxBorrowed = new JTextField();
        daysToReturnBook = new JTextField();
        privileges = new JTextField();
        password = new JTextField();

        panelFields = new JPanel();
        panelFields.setLayout(new GridLayout(8, 2));

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
        saveButton = new AddClientButton("Zapisz", this, getDatabaseModule());

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        saveButton.linkFields(name, surname, address, dateOfBirth,
                maxBorrowed, daysToReturnBook, privileges, password);

        saveButton.setPreferredSize(new Dimension(80, 25));
        cancelButton.setPreferredSize(new Dimension(80, 25));

        PlainDocument doc = (PlainDocument) maxBorrowed.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());

        doc = (PlainDocument) daysToReturnBook.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());

        doc = (PlainDocument) privileges.getDocument();
        doc.setDocumentFilter((new PositiveIntegerDocumentFilter()));

        panelButtons = new JPanel();
        panelButtons.add(cancelButton);
        panelButtons.add(saveButton);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panelFields);
        add(panelButtons);

    }
    public AddClientFrame(String title, DatabaseModule dbModule) {
        super(title, dbModule);
        drawGUI();
        setSize(270, 230);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
