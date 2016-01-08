package Employee;

import Employee.DataStructures.Alert;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by darek on 07.01.2016.
 */
public class AlertsTableModel extends AbstractTableModel {
    ArrayList<Alert> alerts;

    public AlertsTableModel(ArrayList<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public int getRowCount() {
        return alerts.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String value = "";
        switch (columnIndex) {
            case 0:
                value = alerts.get(rowIndex).book.isbn;
                break;
            case 1:
                value = alerts.get(rowIndex).client.name + " "
                        + alerts.get(rowIndex).client.surname;
                break;
            case 2:
                value = alerts.get(rowIndex).borrow_date.toString();
                break;
            case 3:
                value = alerts.get(rowIndex).return_date.toString();
        }
        return value;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";

        switch (column) {
            case 0:
                name = "ISBN";
                break;
            case 1:
                name = "Czytelnik";
                break;
            case 2:
                name = "Data wypożyczenia";
                break;
            case 3:
                name = "Termin oddania";
                break;
        }

        return name;
    }

    public Alert getAlert(int row) { return alerts.get(row); }
}
