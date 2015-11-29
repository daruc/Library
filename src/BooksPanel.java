import javax.swing.*;
import java.awt.*;

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
    private BooksTable booksTable;


    public BooksPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        this.frame = frame;
        this.user = user;
        this.dbModule = dbModule;

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        searchDescription = new JLabel("Wyszukiwanie");
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 2));
        titleDescription = new JLabel("Tytuł ");
        genreDescription = new JLabel("Gatunek ");
        authorDesciption = new JLabel("Autor ");
        title = new JTextField();
        genre = new JTextField();
        author = new JTextField();
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

        booksTable = new BooksTable(dbModule);
        scrollPane = new JScrollPane(booksTable);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(sidebarPanel);
        add(scrollPane);
    }
}
