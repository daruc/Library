package Employee;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Employee.DocumentFilters.ISBNDocumentFilter;
import Employee.*;

/**
 * Created by darek on 29.11.2015.
 */
public class BooksPanel extends JPanel {
    private MyFrame frame;
    private String user;
    private DatabaseModule dbModule;

    private JPanel sidebarPanel;
    private JLabel searchDescription;

    private JPanel searchPanel;
    private JLabel isbnDescription;
    private JTextField isbn;
    private JLabel titleDescription;
    private JTextField title;
    private JLabel genreDescription;
    private JTextField genre;
    private JLabel authorDesciption;
    private JTextField author;

    private JButton searchButton;
    private JLabel managementDescription;

    private JPanel managementPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton borrowButton;

    private JScrollPane scrollPane;
    private JTable booksTable;
    private BooksTableModel booksTableModel;


    public BooksPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        this.frame = frame;
        this.user = user;
        this.dbModule = dbModule;

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        searchDescription = new JLabel("Wyszukiwanie");
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(4, 2));
        isbnDescription = new JLabel("ISBN ");
        titleDescription = new JLabel("Tytuł ");
        genreDescription = new JLabel("Gatunek ");
        authorDesciption = new JLabel("Autor ");
        isbn = new JTextField();
        title = new JTextField();
        genre = new JTextField();
        author = new JTextField();
        searchPanel.add(isbnDescription);
        searchPanel.add(isbn);
        searchPanel.add(titleDescription);
        searchPanel.add(title);
        searchPanel.add(genreDescription);
        searchPanel.add(genre);
        searchPanel.add(authorDesciption);
        searchPanel.add(author);

        searchButton = new JButton("Szukaj");

        managementDescription = new JLabel("Zarządzanie");
        managementPanel = new JPanel();
        managementPanel.setLayout(new GridLayout(2, 2));
        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        editButton = new JButton("Edytuj");
        borrowButton = new JButton("Wypożycz");

        booksTable = new JTable();
        booksTable.getTableHeader().setReorderingAllowed(false);

        //Temporary tableModel
        booksTable.setModel(new BooksTableModel(new ArrayList<Employee.DataStructures.Book>()));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> new AddBookFrame("Dodaj książkę", dbModule));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strIsbn = isbn.getText();
                String strTitle = title.getText();
                String strGenre = genre.getText();
                String strAuthor = author.getText();

                ArrayList<Employee.DataStructures.Book> books = dbModule.getBooks(strIsbn,
                        strTitle, strGenre, strAuthor);

                booksTableModel = new BooksTableModel(books);
                booksTable.setModel(booksTableModel);
            }
        });

        PlainDocument doc = (PlainDocument) isbn.getDocument();
        doc.setDocumentFilter(new ISBNDocumentFilter());

        managementPanel.add(addButton);
        managementPanel.add(deleteButton);
        managementPanel.add(editButton);
        managementPanel.add(borrowButton);

        searchDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        managementDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebarPanel.add(searchDescription);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(searchPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(searchButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 190)));
        sidebarPanel.add(managementDescription);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(managementPanel);


        scrollPane = new JScrollPane(booksTable);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(sidebarPanel);
        add(scrollPane);
    }
}
