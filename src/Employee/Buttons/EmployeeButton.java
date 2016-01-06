package Employee.Buttons;

import Employee.DataStructures.Employee;

import javax.swing.*;

/**
 * Created by darek on 06.01.2016.
 */
public class EmployeeButton extends JButton {
    protected JTextField login;
    protected JTextField name;
    protected JTextField surname;
    protected JTextField address;
    protected JTextField date;
    protected JTextField privileges;
    protected JTextField password;

    public EmployeeButton(String title) {
        super(title);
    }

    public void linkFields(JTextField login, JTextField name, JTextField surname,
                           JTextField address, JTextField date, JTextField privileges,
                           JTextField password ) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.date = date;
        this.privileges = privileges;
        this.password = password;
    }
}
