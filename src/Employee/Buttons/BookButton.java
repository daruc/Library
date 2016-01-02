package Employee.Buttons;

import Employee.DatabaseModule;
import Employee.MyFrame;

import javax.swing.*;
import javax.xml.crypto.Data;

/**
 * Created by darek on 31.12.2015.
 */
public class BookButton extends JButton {
    protected JTextField isbn;
    protected JTextField title;
    protected JTextField author;
    protected JTextField genre;
    protected JTextField number;
    protected JTextArea description;

    protected MyFrame ownerFrame;
    protected DatabaseModule dbModule;

    public BookButton(String title, MyFrame owner) {
        super(title);
        ownerFrame = owner;
        this.dbModule = owner.getDatabaseModule();
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
