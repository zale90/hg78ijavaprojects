package Game.GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Credits extends JPanel {
	
	private static final long serialVersionUID = 2500415931767014676L;
	
	private final String text = "" +
			"Hier findet ihr die Cedits!\n" +
			"";
	
	private JTextPane tpInfos;
	
	public Credits() {
		this.setSize(494, 672);
		this.setLocation(500, 0);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		this.setBorder(new LineBorder(Color.BLACK, 2));
		
		// Textfeld anzeigen
		StyleContext kontext = new StyleContext();
	    StyledDocument dokument = new DefaultStyledDocument(kontext);
	    Style style = dokument.getStyle(StyleContext.DEFAULT_STYLE);
	    StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
	    StyleConstants.setFontSize(style, 20);
	      
	    try 
	    {
	    	dokument.insertString(dokument.getLength(), text, style);
	    } 
	    catch (BadLocationException badLocationException) {}
		
		tpInfos = new JTextPane(dokument);
		//tpInfos.setText(text); Übernimmt jetzt das StyledDocument
		tpInfos.setSize(490, 670);
		tpInfos.setLocation(0, 0);
		tpInfos.setBackground(Color.BLACK);
		tpInfos.setForeground(Color.WHITE);
		tpInfos.setEditable(false);
		this.add(tpInfos);
		
		
		this.setVisible(true);
	}
}
