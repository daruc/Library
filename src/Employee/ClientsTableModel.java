package Employee;

import Employee.DataStructures.Book;
import Employee.DataStructures.Client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by darek on 02.01.2016.
 */
public class ClientsTableModel extends AbstractTableModel {
    private ArrayList<Client> clients;

    public ClientsTableModel(ArrayList<Client> clients) {
        this.clients = clients;
    }
    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch (column) {
            case 0:
                name = "Imię";
                break;
            case 1:
                name = "Nazwisko";
                break;
            case 2:
                name = "Data urodzenia";
                break;
            case 3:
                name = "Adres";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String value = "";
        switch (columnIndex) {
            case 0:
                value = clients.get(rowIndex).name;
                break;
            case 1:
                value = clients.get(rowIndex).surname;
                break;
            case 2:
                value = clients.get(rowIndex).date_of_birth.toString();
                break;
            case 3:
                value = clients.get(rowIndex).address;
                break;
        }

        return value;
    }

    public Client getClient(int index) { return clients.get(index); }

    public void removeRow(int row) {
        fireTableRowsDeleted(row, row);
        clients.remove(row);
    }

    public void updateRow(int row, Client client) {
        fireTableRowsUpdated(row, row);
        Client c = clients.get(row);
        c.name = client.name;
        c.surname = client.surname;
        c.date_of_birth = client.date_of_birth;
        c.address = client.address;
        c.borrowed = client.borrowed;
        c.max_borrowed = client.max_borrowed;
        c.days_to_return_book = client.days_to_return_book;
        c.privileges = client.privileges;
        c.password = client.password;
    }
}
