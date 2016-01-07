package Employee;

import Employee.DataStructures.BorrowedBook;
import Employee.DataStructures.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by darek on 07.01.2016.
 */
public class ReturnBookFrame extends MyFrame {
    private Client client;

    private JButton returnBook;
    private JTable borrowedBooks;

    private JScrollPane scrollPane;

    private void drawGUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        returnBook = new JButton("Oddaj ksiażkę");
        returnBook.setAlignmentX(Component.CENTER_ALIGNMENT);

        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = borrowedBooks.getSelectedRow();
                BorrowedTableModel model = (BorrowedTableModel) borrowedBooks.getModel();
                BorrowedBook book = model.getBorrowedBook(row);
                model.removeRow(row);
                getDatabaseModule().returnBook(book.id);

                --client.borrowed;
            }
        });

        ArrayList<BorrowedBook> books = getDatabaseModule().getBorrowedBooks(client.id);
        BorrowedTableModel model = new BorrowedTableModel(books);
        borrowedBooks = new JTable(model);
        scrollPane = new JScrollPane(borrowedBooks);

        add(returnBook);
        add(scrollPane);
}

    public ReturnBookFrame(String title, DatabaseModule dbModule, Client client) {
        super(title, dbModule);
        this.client = client;

        setSize(500, 350);
        drawGUI();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
