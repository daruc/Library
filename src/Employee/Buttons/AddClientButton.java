package Employee.Buttons;

import Employee.DatabaseModule;
import Employee.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darek on 02.01.2016.
 */
public class AddClientButton extends ClientButton
    implements ActionListener{
    public AddClientButton(String title, MyFrame owner, DatabaseModule dbModule) {
        super(title, owner, dbModule);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strLogin = "";
        String strName = "";
        String strSurname = "";
        String strAddress = "";
        String strDateOfBirth = "";
        String strMaxBorrowed = "";
        String strDaysToReturnBook = "";
        String strPrivileges = "";
        String strPassword = "";

        if (login != null)
            strLogin = login.getText();
        if (name != null)
            strName = name.getText();
        if (surname != null)
            strSurname = surname.getText();
        if (address != null)
            strAddress = address.getText();
        if (dateOfBirth != null)
            strDateOfBirth = dateOfBirth.getText();
        if (maxBorrowed != null)
            strMaxBorrowed = maxBorrowed.getText();
        if (daysToReturnBook != null)
            strDaysToReturnBook = daysToReturnBook.getText();
        if (privileges != null)
            strPrivileges = privileges.getText();
        if (password != null)
            strPassword = password.getText();
        if (login != null)
            strLogin = login.getText();

        boolean successs = dbModule.addClient(strName, strSurname,
                strAddress, strDateOfBirth, strMaxBorrowed,
                strDaysToReturnBook, strPrivileges, strPassword, strLogin);
        if (successs) {
            ownerFrame.setVisible(false);
            ownerFrame.dispose();
            JOptionPane.showMessageDialog(ownerFrame, "Dodano nowego klienta.", "Zapisano",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(ownerFrame, "Sprawdź poprawność danych.", "Zapisywanie nieudane",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
