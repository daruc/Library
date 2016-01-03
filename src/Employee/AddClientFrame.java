package Employee;

import Employee.Buttons.AddClientButton;

/**
 * Created by darek on 02.01.2016.
 */
public class AddClientFrame extends AbstractClientFrame {

    private AddClientButton saveButton;

    public AddClientFrame(String title, DatabaseModule dbModule) {
        super(title, dbModule);
    }

    @Override
    public void setSaveButton() {
        saveButton = new AddClientButton("Zapisz", this, getDatabaseModule());
        saveButton.linkFields(name, surname, address, dateOfBirth,
                maxBorrowed, daysToReturnBook, privileges, password);
        panelButtons.add(saveButton);
    }
}
