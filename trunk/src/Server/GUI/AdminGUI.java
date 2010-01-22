package Server.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Server.ScoreServer;

public class AdminGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6464529758980285571L;

	private static JTextPane console;
	private JButton btnReset, btnCleanConsole;

	public AdminGUI() {
		super("Administration");
		this.setSize(500, 280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(null);

		this.setIconImage(new ImageIcon("files/Icon-Europa.png").getImage());

		btnReset = new JButton("Reset Highscores");
		btnReset.setSize(150, 30);
		btnReset.setLocation(20, 20);
		btnReset.addActionListener(this);
		this.add(btnReset);

		btnCleanConsole = new JButton("Konsole löschen");
		btnCleanConsole.setSize(150, 30);
		btnCleanConsole.setLocation(200, 20);
		btnCleanConsole.addActionListener(this);
		this.add(btnCleanConsole);

		// Konsole
		console = new JTextPane();
		console.setSize(494, 172);
		console.setLocation(0, 80);
		console.setEditable(true);
		console.setBorder(new LineBorder(Color.BLACK));
		this.add(console);

		/*
		 * Scrollpane funktioniert natürlich mal wieder nicht :-/ JScrollPane
		 * spConsole = new JScrollPane(console); console.setSize(498, 150);
		 * console.setLocation(0, 100);
		 * spConsole.setVerticalScrollBarPolicy(ScrollPaneConstants
		 * .VERTICAL_SCROLLBAR_ALWAYS); this.add(spConsole);
		 */
	}

	/**
	 * Fügt den übergebenen Text in die Admin-Konsole ein.
	 * 
	 * @param text
	 *            Text der in die Konsole eingefügt werden soll.
	 */
	public static void consoleText(String text) {
		console.setText(text + "\n" + console.getText());
		console.setCaretPosition(0);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnReset) {
			ScoreServer.resetHighscores();
		} else if (evt.getSource() == btnCleanConsole) {
			console.setText("");
		}
	}

}
