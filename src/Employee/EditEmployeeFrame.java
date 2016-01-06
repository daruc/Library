package Employee;

import Employee.Buttons.UpdateEmployeeButton;
import Employee.DataStructures.Employee;

/**
 * Created by darek on 06.01.2016.
 */
public class EditEmployeeFrame extends AbstractEmployeeFrame {
    private Employee employee;
    private UpdateEmployeeButton button;

    public EditEmployeeFrame(String title, AdminPanel adminPanel, DatabaseModule dbModule,
                             Employee employee) {
        super(title, adminPanel, dbModule);
        this.employee = employee;
        button.setEmployee(employee);
        login.setText(employee.login);
        name.setText(employee.name);
        surname.setText(employee.surname);
        address.setText(employee.address);
        date.setText(employee.date_of_birth.toString());
        privileges.setText(String.valueOf(employee.privileges));
        password.setText(employee.password);
    }

    @Override
    public void setSaveButton() {
        button = new UpdateEmployeeButton("Akutalizuj", dbModule, this);
        button.linkFields(login, name, surname, address, date, privileges, password);
        panelButtons.add(button);
    }

    public AdminPanel getAdminPane() {
        return adminPanel;
    }
}
