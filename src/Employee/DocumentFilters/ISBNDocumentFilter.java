package Employee.DocumentFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * Created by darek on 29.12.2015.
 */
public class ISBNDocumentFilter extends PositiveIntegerDocumentFilter{
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        Document document = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(document.getText(0, document.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            if (super.test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

    }

    public boolean test(String string) {
        boolean isPartOfISBN = true;
        if (string.length() > 13)
            isPartOfISBN = false;

        return isPartOfISBN;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        Document document = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(document.getText(0, document.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
            if (super.test(sb.toString()))
                super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document document = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(document.getText(0, document.getLength()));
        sb.delete(offset, offset + length);

        if (test(sb.toString())) {
            if (super.test(sb.toString()))
                super.remove(fb, offset, length);
        }
    }
}
