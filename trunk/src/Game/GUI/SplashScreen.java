package Game.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import Game.Optionen;
import Game.SpielAnwendung;

public class SplashScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = -261524941549037200L;

	/**
	 * Erstellt einen neuen Splashscreen von dem aus man dann zur
	 * Charakterauswahl wechseln kann.
	 */
	public SplashScreen() {

		this.setSize(995, 672);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);

		// Überschrift anzeigen
		JLabel lblHeading = new JLabel(Optionen.NAME);
		lblHeading.setSize(925, 50);
		lblHeading.setLocation(30, 20);
		lblHeading.setFont(Optionen.FONT_TITLE);
		lblHeading.setHorizontalAlignment(0);
		this.add(lblHeading);

		// Logo anzeigen
		JLabel lblLogo = new JLabel(new ImageIcon("files/SpielLogo.png"));
		lblLogo.setSize(300, 300);
		lblLogo.setLocation(342, 100);
		this.add(lblLogo);

		// Info-Text anzeigen
		String text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam "
				+ "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est "
				+ "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et "
				+ "dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.";
		text = text + "\n\n" + text;

		StyleContext kontext = new StyleContext();
		StyledDocument dokument = new DefaultStyledDocument(kontext);
		Style style = dokument.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);

		try {
			dokument.insertString(dokument.getLength(), text, style);
		} catch (BadLocationException badLocationException) {
		}

		JTextPane tpInfo = new JTextPane(dokument);
		tpInfo.setSize(650, 140);
		tpInfo.setLocation(185, 420);
		tpInfo.setBackground(null);
		tpInfo.setEditable(false);
		this.add(tpInfo);

		// Starten Button
		JButton btnStart = new JButton("Spiel starten!");
		btnStart.setSize(300, 50);
		btnStart.setLocation(345, 580);
		btnStart.addActionListener(this);
		this.add(btnStart);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Spiel starten bzw. Charakterauswahl anzeigen
		SpielAnwendung.zeigeCharakterauswahl();
	}

}
