package Employee;

import javax.swing.table.AbstractTableModel;
import Employee.DataStructures.Employee;

import java.util.ArrayList;

/**
 * Created by darek on 06.01.2016.
 */
public class EmployeesTableModel extends AbstractTableModel {
    private ArrayList<Employee> employees;

    public EmployeesTableModel(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String employee = "";

        switch (columnIndex) {
            case 0:
                employee = employees.get(rowIndex).login;
                break;
            case 1:
                employee = employees.get(rowIndex).name;
                break;
            case 2:
                employee = employees.get(rowIndex).surname;
                break;
            case 3:
                employee = String.valueOf(employees.get(rowIndex).privileges);
                break;
        }

        return employee;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";

        switch (column) {
            case 0:
                name = "Login";
                break;
            case 1:
                name = "Imię";
                break;
            case 2:
                name = "Nazwisko";
                break;
            case 3:
                name = "Poziom";
                break;
        }

        return name;
    }

    public Employee getEmployee(int index) {return employees.get(index); }

    public void removeRow(int row) {
        fireTableRowsDeleted(row, row);
        employees.remove(row);
    }

    public void updateRow(int row, Employee employee) {
        fireTableRowsUpdated(row, row);
        Employee e = employees.get(row);
        e.id = employee.id;
        e.name = employee.name;
        e.surname = employee.surname;
        e.address = employee.address;
        e.date_of_birth = employee.date_of_birth;
        e.login = employee.login;
        e.password = employee.password;
        e.privileges = employee.privileges;
    }
}
