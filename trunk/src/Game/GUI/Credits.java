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

import Game.Optionen;

public class Credits extends JPanel {

	private static final long serialVersionUID = 2500415931767014676L;

	private final String text = Optionen.NAME
			+ "\n\nDieses Spiel hat es sich zur Aufgabe gemacht, die Situation sozial benachteiligter Menschen in Deutschland darzustellen und auf m�gliche Auswege hinzuweisen. "
			+ "Nebenbei soll es nat�rlich auch Spa� machen. "
			+ "\n\n\n"
			+ "Mitwirkende\n\n"
			+ "Idee: Jan B�nker\n\n"
			+ "Hauptspiel: \nPhilipp Sch�rmann, Henrik Schl�sener, Jan B�nker, Hendrik Hollenborg\n\n"
			+ "Automat: \nHeinrich Reich\n\n"
			+ "Bewerbungstest: \nLukas Storp, Jan Natschke, Christian Haves\n\n"
			+ "Kniffel: \nJohannes Diekmann\n\n"
			+ "Kreuzwortr�tsel: \nMathias H�we, Daniel Stegemann, Philipp Richter\n\n"
			+ "Senso: \nHeinrich Reich\n\n"
			+ "Sudoku: \nManuela Wentker, Christian Peters, Bruno Wilken\n\n"
			+ "Tic Tac Toe: \nAmmar Al Mikael\n\n"
			+ "Grafiken: \nHendrik Hollenborg\n\n"
			+ "Besonderer Dank an: \nThomas Bittner";

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
		StyleConstants.setFontSize(style, 13);

		try {
			dokument.insertString(dokument.getLength(), text, style);
		} catch (BadLocationException badLocationException) {
		}

		tpInfos = new JTextPane(dokument);
		// tpInfos.setText(text); �bernimmt jetzt das StyledDocument
		tpInfos.setSize(490, 670);
		tpInfos.setLocation(0, 0);
		tpInfos.setBackground(Color.BLACK);
		tpInfos.setForeground(Color.WHITE);
		tpInfos.setEditable(false);
		this.add(tpInfos);

		this.setVisible(true);
	}
}
