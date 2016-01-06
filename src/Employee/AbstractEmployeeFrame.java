package Employee;

import Employee.DocumentFilters.PositiveIntegerDocumentFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 06.01.2016.
 */
abstract public class AbstractEmployeeFrame extends JFrame {
    protected AdminPanel adminPanel;
    protected DatabaseModule dbModule;

    //GUI
    protected JLabel labelLogin;
    protected JLabel labelName;
    protected JLabel labelSurname;
    protected JLabel labelAddress;
    protected JLabel labelDate;
    protected JLabel labelPrivileges;
    protected JLabel labelPassword;

    protected JTextField login;
    protected JTextField name;
    protected JTextField surname;
    protected JTextField address;
    protected JTextField date;
    protected JTextField privileges;
    protected JTextField password;

    protected JPanel panelFields;
    protected JPanel panelButtons;

    protected JButton cancelButton;

    private void drawGUI() {
        labelLogin = new JLabel("Login: ");
        labelName = new JLabel("Imię: ");
        labelSurname = new JLabel("Nazwisko: ");
        labelAddress = new JLabel("Adres: ");
        labelDate = new JLabel("Data urodzenia: ");
        labelPrivileges = new JLabel("Poziom: ");
        labelPassword = new JLabel("Hasło: ");

        login = new JTextField();
        name = new JTextField();
        surname = new JTextField();
        address = new JTextField();
        date = new JTextField();
        privileges = new JTextField();
        password = new JTextField();

        PlainDocument doc = (PlainDocument) privileges.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());
        privileges.setDocument(doc);

        panelFields = new JPanel();
        panelButtons = new JPanel();

        panelFields.setLayout(new GridLayout(7, 2));
        panelFields.add(labelLogin);
        panelFields.add(login);
        panelFields.add(labelName);
        panelFields.add(name);
        panelFields.add(labelSurname);
        panelFields.add(surname);
        panelFields.add(labelAddress);
        panelFields.add(address);
        panelFields.add(labelDate);
        panelFields.add(date);
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
        panelButtons.add(cancelButton);

        setSaveButton();

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panelFields);
        add(panelButtons);
    }

    public AbstractEmployeeFrame(String title, AdminPanel adminPanel, DatabaseModule dbModule) {
        super(title);
        this.adminPanel = adminPanel;
        this.dbModule = dbModule;

        drawGUI();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(270, 230);
    }

    abstract public void setSaveButton();
}
