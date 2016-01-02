package Employee.Buttons;

import Employee.DatabaseModule;
import Employee.MyFrame;

import javax.swing.*;

/**
 * Created by darek on 02.01.2016.
 */
public class ClientButton extends JButton {
    protected JTextField name;
    protected JTextField surname;
    protected JTextField address;
    protected JTextField dateOfBirth;
    protected JTextField maxBorrowed;
    protected JTextField daysToReturnBook;
    protected JTextField privileges;
    protected JTextField password;

    protected MyFrame ownerFrame;
    protected DatabaseModule dbModule;

    public ClientButton(String title, MyFrame owner, DatabaseModule dbModule) {
        super(title);
        ownerFrame = owner;
        this.dbModule = dbModule;
    }

    public void linkFields(JTextField name, JTextField surname, JTextField address, JTextField dateOfBirth,
                           JTextField maxBorrowed, JTextField daysToReturnBook, JTextField privileges,
                           JTextField password) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.maxBorrowed = maxBorrowed;
        this.daysToReturnBook = daysToReturnBook;
        this.privileges = privileges;
        this.password = password;
    }

}
