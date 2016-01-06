package Employee.Buttons;
import Employee.AddEmployeeFrame;
import Employee.AdminPanel;
import Employee.DatabaseModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 06.01.2016.
 */
public class AddEmployeeButton extends EmployeeButton
    implements ActionListener {
    private DatabaseModule dbModule;
    private JFrame ownerFrame;

    public AddEmployeeButton(String title, DatabaseModule dbModule, JFrame ownerFrame) {
        super(title);
        this.dbModule = dbModule;
        this.ownerFrame = ownerFrame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strLogin = "";
        String strName = "";
        String strSurname = "";
        String strAddress = "";
        String strDate = "";
        String strPrivileges = "";
        String strPassword = "";

        if (login != null) {
            strLogin = login.getText();
        }
        if (name != null) {
            strName = name.getText();
        }
        if (surname != null) {
            strSurname = surname.getText();
        }
        if (address != null) {
            strAddress = address.getText();
        }
        if (date != null) {
            strDate = date.getText();
        }
        if (privileges != null) {
            strPrivileges = privileges.getText();
        }
        if (password != null) {
            strPassword = password.getText();
        }

        boolean success = dbModule.addEmployee(strLogin, strName, strSurname,
                strAddress, strDate, strPrivileges, strPassword);
        if (success) {
            ownerFrame.setVisible(false);
            ownerFrame.dispose();
            ((AddEmployeeFrame)ownerFrame).getAdminPane().refreshTable();
            JOptionPane.showMessageDialog(ownerFrame, "Dodano nowego pracownika.", "Zapisano",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(ownerFrame, "Sprawdź poprawność danych.", "Zapisywanie nieudane",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
