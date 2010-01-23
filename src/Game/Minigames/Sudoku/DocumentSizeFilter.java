package Game.Minigames.Sudoku;

import javax.swing.text.*;
import java.awt.*;
import javax.swing.*;

public class DocumentSizeFilter extends DocumentFilter
{
    private final int maxCharacters;
    private final String pattern;
    
    public DocumentSizeFilter(final int maxChars, final String pattern)
    {
        maxCharacters = maxChars;
        this.pattern = pattern;
    }
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException
    {
        if (str.matches(pattern) && (fb.getDocument().getLength() + str.length() - length) <= maxCharacters)
        {
            super.replace(fb, offs, length, str, a);
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
