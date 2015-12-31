package Employee.Buttons;

import Employee.DatabaseModule;
import Employee.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton extends JButton {
    private DatabaseModule dbModule;
    private MyFrame ownerFrame;

    private JTextField isbn;
    private JTextField title;
    private JTextField author;
    private JTextField genre;
    private JTextField number;
    private JTextArea description;

    public AddButton(MyFrame owner, String text) {
        super(text);
        this.ownerFrame = owner;
        this.dbModule = owner.getDatabaseModule();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strIsbn = "";
                String strTitle = "";
                String strAuthor = "";
                String strGenre = "";
                String strDescription = "";
                String strNumber = "";
                if (isbn != null)
                    strIsbn = isbn.getText().toString();
                if (title != null)
                    strTitle = title.getText().toString();
                if (author != null)
                    strAuthor = author.getText().toString();
                if (genre != null)
                    strGenre = genre.getText().toString();
                if (description != null)
                    strDescription = description.getText().toString();
                if (number != null)
                    strNumber = number.getText().toString();
                boolean success = dbModule.addBook(strIsbn, strTitle, strAuthor, strGenre, strNumber, strDescription);

                if (success) {
                    ownerFrame.setVisible(false);
                    ownerFrame.dispose();
                    JOptionPane.showMessageDialog(ownerFrame, "Dodano nową książkę.", "Zapisano",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(ownerFrame, "Sprawdź poprawność danych.", "Zapisywanie nieudane",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public void linkFields(JTextField isbn, JTextField title, JTextField author,
                           JTextField genre, JTextArea description, JTextField number) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.number = number;
    }
}
