package Employee;

import javax.swing.table.AbstractTableModel;
import Employee.DataStructures.Book;

import java.util.ArrayList;

/**
 * Created by darek on 29.12.2015.
 */
public class BooksTableModel extends AbstractTableModel {
    private ArrayList<Book> books;

    public BooksTableModel(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch (column) {
            case 0:
                name = "ISBN";
                break;
            case 1:
                name = "Tytuł";
                break;
            case 2:
                name = "Autor";
                break;
            case 3:
                name = "Gatunek";
                break;
            case 4:
                name = "Liczba";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String result = "";
        switch (columnIndex) {
            case 0:
                result = books.get(rowIndex).isbn;
                break;
            case 1:
                result = books.get(rowIndex).title;
                break;
            case 2:
                result = books.get(rowIndex).author;
                break;
            case 3:
                result = books.get(rowIndex).genre;
                break;
            case 4:
                result = Integer.toString(books.get(rowIndex).borrowed);
                result += '/';
                result += Integer.toString(books.get(rowIndex).number_of_books);
                break;
        }
        return result;
    }

    public Book getBook(int index) {
        return books.get(index);
    }
}
