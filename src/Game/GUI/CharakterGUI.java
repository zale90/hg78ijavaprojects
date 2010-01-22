package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import Game.*;

public class CharakterGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5;
	private Avatar avatar;
	private JLabel name, bild;
	private JTextPane beschreibung;
	private JButton wahl;

	/**
	 * Panel mit der Übersicht über einen Avatar. Zeigt den Namen, ein Bild, die
	 * Beschreibung sowie einen Button mit dem sich das Spiel mit dem jeweiligen
	 * Avatar starten lässt.
	 * 
	 * @param avatar
	 *            Avatar für den das Panel erstellt werden soll.
	 * @param x
	 *            X-Koordinate an der das Panel angezeigt werden soll.
	 * @param y
	 *            Y-Koordinate an der das Panel angezeigt werden soll.
	 */
	public CharakterGUI(Avatar avatar, int x, int y) {

		this.setSize(320, 550);
		this.setLocation(x, y);
		this.setBackground(null);
		this.setLayout(null);

		name = new JLabel(avatar.getName());
		name.setFont(Optionen.FONT_BIGGER);
		name.setSize(300, 50);
		name.setLocation(7, 0);
		name.setHorizontalAlignment(0);
		this.add(name);

		bild = new JLabel(new ImageIcon(Optionen.ICON_PATH_AVATAR
				+ avatar.getName() + ".jpg"));
		bild.setSize(200, 200);
		bild.setLocation(60, 50);
		bild.setBorder(new LineBorder(Color.BLACK, 1));
		this.add(bild);

		StyleContext kontext = new StyleContext();
		StyledDocument dokument = new DefaultStyledDocument(kontext);
		Style style = dokument.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);

		try {
			dokument.insertString(dokument.getLength(), avatar
					.getBeschreibung(), style);
		} catch (BadLocationException badLocationException) {
		}

		beschreibung = new JTextPane(dokument);
		beschreibung.setBackground(null);
		// beschreibung.setText(avatar.getBeschreibung());
		beschreibung.setSize(250, 200);
		beschreibung.setLocation(35, 270);
		beschreibung.setEditable(false);
		beschreibung.setCaretPosition(0);
		this.add(beschreibung);

		wahl = new JButton("Spiele " + name.getText());
		wahl.setSize(300, 50);
		wahl.setLocation(15, 480);
		wahl.setHorizontalAlignment(0);
		wahl.addActionListener(this);
		this.add(wahl);

		this.avatar = avatar;

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == wahl) {
			SpielAnwendung.starteSpiel(getAvatar());
		}
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Avatar getAvatar() {
		return avatar;
	}
}