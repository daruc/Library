package Employee;

import Employee.Buttons.UpdateClientButton;
import Employee.DataStructures.Client;

/**
 * Created by darek on 02.01.2016.
 */
public class EditClientFrame extends AbstractClientFrame {
    private ClientsPanel panel;
    private Client client;
    private UpdateClientButton saveButton;
    private MyFrame ownerFrame;

    private void fillFileds() {
        name.setText(client.name);
        surname.setText(client.surname);
        address.setText(client.address);
        dateOfBirth.setText(client.date_of_birth.toString());
        maxBorrowed.setText(String.valueOf(client.max_borrowed));
        daysToReturnBook.setText(String.valueOf(client.days_to_return_book));
        privileges.setText(String.valueOf(client.privileges));
        password.setText(client.password);

        saveButton.setClient(client);
        saveButton.setClientPanel(panel);
        saveButton.setOwnerFrame(this);
    }

    public EditClientFrame(String title, DatabaseModule databaseModule,
                           ClientsPanel panel, MyFrame owner, Client client) {
        super(title, databaseModule);
        this.panel = panel;
        this.client = client;
        this.ownerFrame = owner;
        fillFileds();
    }

    @Override
    public void setSaveButton() {
        saveButton = new UpdateClientButton("Aktualizuj", getDatabaseModule());

        saveButton.linkFields(name, surname, address, dateOfBirth,
                maxBorrowed, daysToReturnBook, privileges, password);
        panelButtons.add(saveButton);
    }

    public ClientsPanel getClientPanel() {
        return panel;
    }
}
