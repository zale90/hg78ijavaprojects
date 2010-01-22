package Game.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import Game.*;

public class Ereignisfenster extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8375152556875337533L;
	private Spiel spiel;
	private Spieloberfläche gameGUI;
	private Ereignis er;
	private JLabel ueberschrift;
	private JTextPane text;
	private JButton ok, ja, nein;
	private StyleContext kontext;
	private StyledDocument dokument;
	private Style style;

	public Ereignisfenster(Spiel spiel, Spieloberfläche gameGUI) {
		this.spiel = spiel;
		this.gameGUI = gameGUI;

		this.setSize(300, 150);
		this.setLocation(270, 235);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setVisible(false);

		ueberschrift = new JLabel();
		ueberschrift.setSize(280, 20);
		ueberschrift.setLocation(10, 5);
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(ueberschrift);

		kontext = new StyleContext();
		dokument = new DefaultStyledDocument(kontext);
		style = dokument.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
		try {
			dokument.insertString(dokument.getLength(), "", style);
		} catch (BadLocationException badLocationException) {
		}

		text = new JTextPane(dokument);
		text.setSize(280, 80);
		text.setLocation(10, 30);
		text.setEditable(false);
		text.setBackground(null);
		this.add(text);

		ok = new JButton("Weiter");
		ok.setSize(90, 30);
		ok.setLocation(105, 110);
		ok.setFont(new Font("Arial", Font.BOLD, 16));
		ok.setVisible(false);
		ok.addActionListener(this);
		this.add(ok);

		ja = new JButton("Ja");
		ja.setSize(70, 30);
		ja.setLocation(60, 110);
		ja.setFont(new Font("Arial", Font.BOLD, 16));
		ja.setVisible(false);
		ja.addActionListener(this);
		this.add(ja);

		nein = new JButton("Nein");
		nein.setSize(70, 30);
		nein.setLocation(170, 110);
		nein.setFont(new Font("Arial", Font.BOLD, 16));
		nein.setVisible(false);
		nein.addActionListener(this);
		this.add(nein);
	}

	public void fensterZeigen(Ereignis er) {
		this.er = er;
		ueberschrift.setText(er.getName());
		text.setText("");
		try {
			dokument.insertString(dokument.getLength(), er.getText(), style);
		} catch (BadLocationException badLocationEsception) {
		}
		text.setStyledDocument(dokument);

		if (er.getTyp()) {
			ja.setVisible(true);
			nein.setVisible(true);
		} else {
			ok.setVisible(true);
		}

		this.setVisible(true);

		fordereFokus();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == ok) {
			ok.setVisible(false);
			this.setVisible(false);
			spiel.infosUmsetzen(er.getJa());
			gameGUI.aktualisiereDaten();
			if (er.getMinispiel() != null)
				spiel.minispielStarten(er.getMinispiel());
			else
				gameGUI.setzeAktiviert(true);
		}
		if (arg0.getSource() == ja) {
			ja.setVisible(false);
			nein.setVisible(false);
			this.setVisible(false);
			spiel.infosUmsetzen(er.getJa());
			gameGUI.aktualisiereDaten();
			gameGUI.setzeAktiviert(true);
		}
		if (arg0.getSource() == nein) {
			ja.setVisible(false);
			nein.setVisible(false);
			this.setVisible(false);
			spiel.infosUmsetzen(er.getNein());
			gameGUI.aktualisiereDaten();
			gameGUI.setzeAktiviert(true);
		}
	}

	/**
	 * Fokussiert den Ja- bzw. Weiter-Button.
	 */
	private void fordereFokus() {
		if (er.getTyp()) {
			ja.requestFocusInWindow();
		} else {
			ok.requestFocusInWindow();
		}
	}
}
