package Employee;

import Employee.DataStructures.BorrowedBook;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by darek on 07.01.2016.
 */
public class BorrowedTableModel extends AbstractTableModel {
    private ArrayList<BorrowedBook> books;

    public BorrowedTableModel(ArrayList<BorrowedBook> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String value = "";
        switch (columnIndex) {
            case 0:
                value = books.get(rowIndex).isbn;
                break;
            case 1:
                value = books.get(rowIndex).borrowed.toString();
                break;
            case 2:
                value = books.get(rowIndex).return_date.toString();
                break;
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
                name = "Data wypożyczenia";
                break;
            case 2:
                name = "Termin oddania";
                break;
        }
        return name;
    }

    public BorrowedBook getBorrowedBook(int row) { return books.get(row); }

    public void removeRow(int index) {
        fireTableRowsDeleted(index, index);
        books.remove(index);
    }
}
