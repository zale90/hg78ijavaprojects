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
		String text = "Dieses Spiel hat es sich zur Aufgabe gemacht, die Situation sozial benachteiligter Menschen in Deutschland darzustellen und auf mögliche Auswege hinzuweisen. " +
				"\nNebenbei soll es natürlich auch Spaß machen." +
				"\nDir stehen 3 Charaktere mit unterschiedlichen Schwierigkeitsgraden zur Verfügung, aus denen du wählen kannst." +
				"" +
				"\nDas Spiel läuft rundenweise ab. Eine Runde im Spiel entspricht einer Woche im Leben deines Avatars." +
				"\nAm rechten Bildschirmrand siehst du Informationen zu deinem Charakter und seinen Bedürfnissen. Je voller die Balken, desto besser für dich." +
				"\nAchtung: wenn einer oder mehrere Balken leer sind und du die Runde beendest, verlierst du!" +
				"\nDie Zeitanzeige sagt dir, wie viele Aktionen du in der laufenden Runde noch ausführen kannst." +
				"\nDein Einkommen bekommst du alle vier Wochen, am Anfang eines neuen Monats." +
				"" +
				"\nAuf dem Spielfeld, einem Ausschnitt deiner Wohnung, kannst du Aktionen ausführen, die deine Bedürfnisse befriedigen, aber dich auch Zeit und häufig Geld kosten." +
				"\n" +
				"\nViel Spaß wünschen: die HG78i und der passionierte Informatiklehrer Thomas Bittner!";


		StyleContext kontext = new StyleContext();
		StyledDocument dokument = new DefaultStyledDocument(kontext);
		Style style = dokument.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);

		try {
			dokument.insertString(dokument.getLength(), text, style);
		} catch (BadLocationException badLocationException) {
		}

		JTextPane tpInfo = new JTextPane(dokument);
		tpInfo.setSize(850, 160);
		tpInfo.setLocation(85, 420);
		tpInfo.setBackground(null);
		tpInfo.setEditable(false);
		this.add(tpInfo);

		// Starten Button
		JButton btnStart = new JButton("Spiel starten!");
		btnStart.setSize(300, 50);
		btnStart.setLocation(345, 600);
		btnStart.addActionListener(this);
		this.add(btnStart);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Spiel starten bzw. Charakterauswahl anzeigen
		SpielAnwendung.zeigeCharakterauswahl();
	}

}
