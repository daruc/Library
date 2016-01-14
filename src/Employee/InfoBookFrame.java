package Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Employee.DataStructures.Book;

/**
 * Created by darek on 31.12.2015.
 */
public class InfoBookFrame extends MyFrame {
    private Book book;

    private JLabel labelIsbn;
    private JLabel labelTitle;
    private JLabel labelAuthor;
    private JLabel labelGenre;
    private JLabel labelDescription;
    private JLabel labelNumberOfBooks;
    private JLabel labelBorrowed;

    private JLabel isbn;
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JTextArea description;
    private JScrollPane descriptionScroll;
    private JLabel numberOfBooks;
    private JLabel borrowed;

    private JPanel isbnPanel;
    private JPanel titlePanel;
    private JPanel authorPanel;
    private JPanel genrePanel;
    private JPanel descriptionPanel;
    private JPanel numberOfBooksPanel;
    private JPanel borrowedPanel;

    private boolean extended;

    public void drawGUI() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        isbnPanel = new JPanel();
        labelIsbn = new JLabel("ISBN: ");
        isbn = new JLabel(book.isbn);
        isbnPanel.setLayout(new BoxLayout(isbnPanel, BoxLayout.X_AXIS));
        isbnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        isbnPanel.setBorder(new EmptyBorder(10,10,10,10));
        isbnPanel.add(labelIsbn);
        isbnPanel.add(isbn);

        titlePanel = new JPanel();
        labelTitle = new JLabel("Tytuł: ");
        title = new JLabel(book.title);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.setBorder(new EmptyBorder(10,10,10,10));
        titlePanel.add(labelTitle);
        titlePanel.add(title);

        authorPanel = new JPanel();
        labelAuthor = new JLabel("Autor: ");
        author = new JLabel(book.author);
        authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.X_AXIS));
        authorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        authorPanel.setBorder(new EmptyBorder(10,10,10,10));
        authorPanel.add(labelAuthor);
        authorPanel.add(author);

        genrePanel = new JPanel();
        labelGenre = new JLabel("Gatunek: ");
        genre = new JLabel(book.genre);
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.X_AXIS));
        genrePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genrePanel.setBorder(new EmptyBorder(10,10,10,10));
        genrePanel.add(labelGenre);
        genrePanel.add(genre);

        descriptionPanel = new JPanel();
        labelDescription = new JLabel("Opis: ");
        description = new JTextArea(book.description);
        description.setLineWrap(true);
        description.setEditable(false);
        description.setFont(isbn.getFont());
        description.setWrapStyleWord(true);
        descriptionScroll = new JScrollPane(description);
        descriptionScroll.setSize(120, 140);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
        descriptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.setBorder(new EmptyBorder(10,10,10,10));
        descriptionPanel.add(labelDescription);
        descriptionPanel.add(descriptionScroll);

        numberOfBooksPanel = new JPanel();
        labelNumberOfBooks = new JLabel("Liczba książek: ");
        numberOfBooks = new JLabel(Integer.toString(book.number_of_books));
        numberOfBooksPanel.setLayout(new BoxLayout(numberOfBooksPanel, BoxLayout.X_AXIS));
        numberOfBooksPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        numberOfBooksPanel.setBorder(new EmptyBorder(10,10,10,10));
        numberOfBooksPanel.add(labelNumberOfBooks);
        numberOfBooksPanel.add(numberOfBooks);

        borrowedPanel = new JPanel();
        labelBorrowed = new JLabel("Wypożyczonych: ");
        borrowed = new JLabel(Integer.toString(book.borrowed));
        borrowedPanel.setLayout(new BoxLayout(borrowedPanel, BoxLayout.X_AXIS));
        borrowedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        borrowedPanel.setBorder(new EmptyBorder(10,10,10,10));
        borrowedPanel.add(labelBorrowed);
        borrowedPanel.add(borrowed);

        Font font = new Font(isbn.getFont().getName(), Font.BOLD, isbn.getFont().getSize());
        labelIsbn.setFont(font);
        labelTitle.setFont(font);
        labelAuthor.setFont(font);
        labelGenre.setFont(font);
        labelDescription.setFont(font);
        labelNumberOfBooks.setFont(font);
        labelBorrowed.setFont(font);

        add(isbnPanel);
        add(titlePanel);
        add(authorPanel);
        add(genrePanel);
        add(descriptionPanel);

        if (extended) {
            add(numberOfBooksPanel);
            add(borrowedPanel);
        }
    }

    public InfoBookFrame(String title, DatabaseModule dbModule, Book book, boolean ext) {
        super(title, dbModule);
        this.book = book;
        extended = ext;

        drawGUI();
        if (extended)
            setSize(270, 330);
        else
            setSize(270, 290);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
