package SistemaDeCompras.DocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class FilterFormat extends DocumentFilter {

    /*
    *@param length
    *       Define el número
    *@param regex
    *       La expresión regular que con la que el String debe coincidir.
    *@return {@code true} si, y solo si, el string cumple con las condiciones necesarias.
    *
    * @throws PatternSyntaxException
    *       Si la expresión regular es inválida.
    * */
    public JTextField filterFormatField(int length, String regex) {
        JTextField field = new JTextField();
        AbstractDocument doc = (AbstractDocument) field.getDocument();
        final int maxCharacters = length;

        doc.setDocumentFilter(new DocumentFilter() {

            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + text.substring(offset + length);

                if (newText.matches(regex) || newText.length() == 0) super.remove(fb, offset, length);
            }

            public void replace(FilterBypass fb, int offset, int length, String _text, AttributeSet attrs)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + _text + text.substring(offset + length);
                if(newText.length() <= maxCharacters && newText.matches(regex))
                    super.replace(fb, offset, length, _text, attrs);
                else
                    Toolkit.getDefaultToolkit().beep();
            }

            public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + str + text.substring(offset);

                if((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && newText.matches(regex))
                    super.insertString(fb, offset, str, attr);
                else
                    Toolkit.getDefaultToolkit().beep();
            }
        });

        return field;
    }

}
