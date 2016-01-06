package Employee;

import Employee.Buttons.AddEmployeeButton;

import javax.swing.*;

/**
 * Created by darek on 06.01.2016.
 */
public class AddEmployeeFrame extends AbstractEmployeeFrame {
    private AddEmployeeButton saveButton;

    public AddEmployeeFrame(String title, AdminPanel adminPanel, DatabaseModule dbModule) {
        super(title, adminPanel, dbModule);


    }

    @Override
    public void setSaveButton() {
        saveButton = new AddEmployeeButton("Zapisz", dbModule, this);
        saveButton.setPreferredSize(cancelButton.getPreferredSize());
        saveButton.linkFields(login, name, surname,
                address, date, privileges, password);
        panelButtons.add(saveButton);
    }

    public AdminPanel getAdminPane() { return adminPanel; }
}
