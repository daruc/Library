package Employee.Buttons;

import Employee.*;
import Employee.DataStructures.Client;
import Employee.TableModels.ClientsTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by darek on 02.01.2016.
 */
public class UpdateClientButton extends ClientButton
    implements ActionListener {

    private Client client;
    private MyFrame ownerFrame;
    ClientsPanel panel;


    public UpdateClientButton(String title, DatabaseModule dbModule) {
        super(title, dbModule);
        addActionListener(this);
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void setClientPanel(ClientsPanel clientsPanel) { panel = clientsPanel; }
    public void setOwnerFrame(MyFrame frame) { ownerFrame = frame; }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strName = "";
        String strSurname = "";
        String strAddress = "";
        String strDateOfBirth = "";
        String strMaxBorrowed = "";
        String strDaysToReturnBook = "";
        String strPrivileges = "";
        String strPassword = "";
        String strLogin = "";

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

        boolean successs = dbModule.updateClient(strName, strSurname,
                strAddress, strDateOfBirth, strMaxBorrowed,
                strDaysToReturnBook, strPrivileges, strPassword, client.borrowed, client.id, strLogin);
        if (successs) {
            ClientsTableModel model = (ClientsTableModel) panel.getTable().getModel();
            int row = panel.getTable().getSelectedRow();

            Client newClient = new Client();
            newClient.name = strName;
            newClient.surname = strSurname;
            newClient.address = strAddress;
            newClient.date_of_birth = Date.valueOf(strDateOfBirth);
            newClient.max_borrowed = Integer.valueOf(strMaxBorrowed);
            newClient.borrowed = client.borrowed;
            newClient.days_to_return_book = Integer.valueOf(strDaysToReturnBook);
            newClient.password = strPassword;
            newClient.id = client.id;
            newClient.privileges = Integer.valueOf(strPrivileges);
            newClient.login = strLogin;

            model.updateRow(row, newClient);

            ownerFrame.setVisible(false);
            ownerFrame.dispose();
            JOptionPane.showMessageDialog(ownerFrame, "Zaktualizowano klienta.", "Zapisano",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(ownerFrame, "Sprawdź poprawność danych.", "Aktualizacja nieudana",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
