package Employee;

import javax.swing.*;
import Employee.DataStructures.Book;

import java.util.ArrayList;

/**
 * Created by darek on 29.11.2015.
 */
public class BooksTable extends JTable {
    private DatabaseModule databaseModule;
    private ArrayList<Book> books = null;

    public BooksTable(DatabaseModule databaseModule) {
        this.databaseModule = databaseModule;
    }

    public void fill(ArrayList<Book> books) {
        this.books = books;
    }

}
