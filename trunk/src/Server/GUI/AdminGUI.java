package Server.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Server.ScoreServer;

public class AdminGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6464529758980285571L;

	private static JTextPane console;
	private JButton btnReset, btnCleanConsole, btnDelScore;

	public AdminGUI() {
		super("Administration");
		this.setSize(500, 280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(null);

		this.setIconImage(new ImageIcon("files/Icon-Europa.png").getImage());

		btnReset = new JButton("Reset Highscores");
		btnReset.setSize(130, 30);
		btnReset.setLocation(20, 20);
		btnReset.addActionListener(this);
		this.add(btnReset);

		btnCleanConsole = new JButton("Konsole löschen");
		btnCleanConsole.setSize(130, 30);
		btnCleanConsole.setLocation(180, 20);
		btnCleanConsole.addActionListener(this);
		this.add(btnCleanConsole);

		btnDelScore = new JButton("Score löschen");
		btnDelScore.setSize(130, 30);
		btnDelScore.setLocation(335, 20);
		btnDelScore.addActionListener(this);
		this.add(btnDelScore);

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
		} else if (evt.getSource() == btnDelScore) {
			deleteScore();
		}
	}

	private void deleteScore() {
		JTextField tfScore = new JTextField(20);
		JTextField tfList = new JTextField(20);
		Object[] options = {
				new JLabel("Nummer des Scores:"), 
				tfScore,
				new JLabel("Nummer des Liste (1-3):"), 
				tfList,
				"OK", 
				"Abbrechen",
		};
		int btnReturn = JOptionPane.showOptionDialog(
						this,
						"Bitte geben Sie die Nummer des Scores\nund die Nummer des Liste an,\naus der ein Score gelöscht werde soll:",
						"Score löschen", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if(btnReturn == 4) {
			int scoreNr = Integer.valueOf(tfScore.getText());
			int listNr = Integer.valueOf(tfList.getText());
			ScoreServer.deleteScore(scoreNr, listNr);
		}
	}

}
