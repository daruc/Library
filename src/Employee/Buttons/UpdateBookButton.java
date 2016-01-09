package Employee.Buttons;

import Employee.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Employee.DataStructures.Book;
import Employee.TableModels.BooksTableModel;

/**
 * Created by darek on 31.12.2015.
 */
public class UpdateBookButton extends BookButton
    implements ActionListener {

    public UpdateBookButton(EditBookFrame owner, String title) {
        super(title, owner);
        addActionListener(this);
    }

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
        String targetIsbn = ((EditBookFrame) ownerFrame).getBook().isbn;
        int targetId = ((EditBookFrame) ownerFrame).getBook().id;
        boolean success = dbModule.updateBook(targetIsbn,
                strIsbn, strTitle, strAuthor, strGenre, strNumber, strDescription, targetId);

        if (success) {
            ownerFrame.setVisible(false);
            BooksPanel booksPanel = ((EditBookFrame) ownerFrame).getBooksPanel();
            BooksTableModel model = (BooksTableModel) booksPanel.getTable().getModel();
            Book newBook = new Book();
            newBook.isbn = strIsbn;
            newBook.title = strTitle;
            newBook.author = strAuthor;
            newBook.genre = strGenre;
            newBook.description = strDescription;
            newBook.number_of_books = Integer.valueOf(strNumber);
            model.updateRow(booksPanel.getTable().getSelectedRow(), newBook);
            ownerFrame.dispose();
            JOptionPane.showMessageDialog(ownerFrame, "Aktualizowano książkę.", "Zapisano",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(ownerFrame, "Sprawdź poprawność danych.", "Zapisywanie nieudane",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
