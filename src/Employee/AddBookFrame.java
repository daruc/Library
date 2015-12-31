package Employee;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Employee.DocumentFilters.*;

/**
 * Created by darek on 03.12.2015.
 */
public class AddBookFrame extends MyFrame {

    private JPanel panel;

    private JLabel isbnDescription;
    private JLabel titleDescription;
    private JLabel authorDescription;
    private JLabel genreDescription;
    private JLabel numberOfBooksDescription;
    private JLabel descriptionDescription;

    private JTextField isbn;
    private JTextField title;
    private JTextField author;
    private JTextField genre;
    private JTextField numberOfBooks;

    private JPanel descriptionPanel;
    private JTextArea description;
    private JScrollPane descriptionScroll;

    private JPanel buttonsPanel;
    private Employee.Buttons.AddButton save;
    private JButton cancel;

    private void drawGUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setMaximumSize(new Dimension(200, 30));

        isbnDescription = new JLabel("ISBN: ");
        titleDescription = new JLabel("Tytuł: ");
        authorDescription = new JLabel("Autor: ");
        genreDescription = new JLabel("Gatunek: ");
        numberOfBooksDescription = new JLabel("Ilość książek: ");
        descriptionDescription = new JLabel("Opis: ");

        isbn = new JTextField();
        title = new JTextField();
        author = new JTextField();
        genre = new JTextField();
        numberOfBooks = new JTextField();

        description = new JTextArea();
        descriptionScroll = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        cancel = new JButton("Zrezygnuj");
        save = new Employee.Buttons.AddButton(this, "Zapisz");
        save.linkFields(isbn, title, author, genre, description, numberOfBooks);


        Font font = new Font(description.getFont().getName(), Font.TYPE1_FONT, description.getFont().getSize() + 2);
        description.setFont(font);
        description.setLineWrap(true);

        PlainDocument doc = (PlainDocument) numberOfBooks.getDocument();
        doc.setDocumentFilter(new PositiveIntegerDocumentFilter());

        doc = (PlainDocument) isbn.getDocument();
        doc.setDocumentFilter(new ISBNDocumentFilter());

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


        panel.add(isbnDescription);
        panel.add(isbn);
        panel.add(titleDescription);
        panel.add(title);
        panel.add(authorDescription);
        panel.add(author);
        panel.add(genreDescription);
        panel.add(genre);
        panel.add(numberOfBooksDescription);
        panel.add(numberOfBooks);
        panel.setMaximumSize(new Dimension(300, 300));

        descriptionPanel.add(descriptionDescription);
        descriptionPanel.add(descriptionScroll);
        buttonsPanel.add(cancel);
        buttonsPanel.add(save);

        add(panel);
        add(descriptionPanel);
        add(buttonsPanel);


    }

    public AddBookFrame(String title, DatabaseModule databaseModule) {
        super(title, databaseModule);

        drawGUI();
        setSize(270, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
