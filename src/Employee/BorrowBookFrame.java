package Employee;

import Employee.DataStructures.Book;
import Employee.DataStructures.Client;
import Employee.TableModels.BooksTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by darek on 06.01.2016.
 */
public class BorrowBookFrame extends MyFrame {

    private Client client;

    private JLabel labelIsbn;
    private JLabel labelTitle;
    private JLabel labelAuthor;
    private JLabel labelGenre;

    private JTextField isbn;
    private JTextField title;
    private JTextField author;
    private JTextField genre;

    private JButton borrow;
    private JButton search;

    private JPanel searchPanel;
    private JPanel buttonsPanel;

    private JTable booksTable;
    private JScrollPane scrollPane;

    private void drawGUI() {
        labelIsbn = new JLabel("ISBN: ");
        labelTitle = new JLabel("Tytuł: ");
        labelAuthor = new JLabel("Autor: ");
        labelGenre = new JLabel("Gatunek: ");

        isbn = new JTextField();
        title = new JTextField();
        author = new JTextField();
        genre = new JTextField();

        borrow = new JButton("Wypożycz");
        search = new JButton("Szukaj");

        borrow.setEnabled(false);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strIsbn = isbn.getText();
                String strTitle = title.getText();
                String strGenre = genre.getText();
                String strAuthor = author.getText();

                ArrayList<Book> books = getDatabaseModule().getBooks(strIsbn,
                        strTitle, strGenre, strAuthor);

                BooksTableModel booksTableModel = new BooksTableModel(books);
                booksTable.setModel(booksTableModel);
            }
        });

        JFrame thisFrame = this;
        borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BooksTableModel model = (BooksTableModel) booksTable.getModel();
                int row = booksTable.getSelectedRow();
                Book book = model.getBook(row);

                if (book.borrowed >= book.number_of_books) {

                    JOptionPane.showMessageDialog(thisFrame, "Brak dostępnej w bibliotece książki.",
                            "Wypożyczanie nieudane",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (client.borrowed >= client.max_borrowed) {
                        JOptionPane.showMessageDialog(getContentPane(), "Czytelnik osiągnął limit wypożyczeń.",
                                "Wypożyczanie nieudane", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        getDatabaseModule().borrowBook(book.id, client.id);
                        JOptionPane.showMessageDialog(thisFrame,
                                "Wypożyczanie udane", "Wypożyczono książkę.",
                                JOptionPane.INFORMATION_MESSAGE);
                        ++client.borrowed;
                        ++book.borrowed;
                        model.updateRow(row, book);
                    }
                }
            }
        });

        booksTable = new JTable();
        booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int count = booksTable.getSelectedRowCount();
                if (count == 1) {
                    borrow.setEnabled(true);
                }
                else
                    borrow.setEnabled(false);
            }
        });

        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if (e.getClickCount() == 2) {
                    BooksTableModel model = (BooksTableModel) booksTable.getModel();
                    Book book = model.getBook(row);
                    EventQueue.invokeLater(() -> new InfoBookFrame("Szczegóły książki",
                            getDatabaseModule(), book));
                }
            }
        });

        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(booksTable);

        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        searchPanel.add(labelIsbn);
        searchPanel.add(isbn);
        searchPanel.add(labelTitle);
        searchPanel.add(title);
        searchPanel.add(labelAuthor);
        searchPanel.add(author);
        searchPanel.add(labelGenre);
        searchPanel.add(genre);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(search);
        buttonsPanel.add(borrow);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(searchPanel);
        add(buttonsPanel);
        add(scrollPane);
    }

    public BorrowBookFrame(String title, DatabaseModule dbModule, Client client) {
        super(title, dbModule);
        this.client = client;

        setSize(500, 350);
        drawGUI();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
