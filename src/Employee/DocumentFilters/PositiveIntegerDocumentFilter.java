package Employee.DocumentFilters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class PositiveIntegerDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        Document document = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(document.getText(0, document.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }

    }

    public boolean test(String string) {
        boolean isPositiveInteger = true;
        for (int i = 0; i < string.length(); ++i) {
            char ch = string.charAt(i);
            if (!Character.isDigit(ch)) {
                isPositiveInteger = false;
                break;
            }
        }

        return isPositiveInteger;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        Document document = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(document.getText(0, document.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
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
            super.remove(fb, offset, length);
        }
    }
}
