package Server.GUI;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import Game.Optionen;
import Server.Highscores;
import Server.TableModelHighscore;

public class HighscoreListe extends JPanel {

	private static final long serialVersionUID = -767260215399434697L;

	private JTable tblScores;

	public HighscoreListe(Highscores scores) {

		this.setSize(300, 700);
		this.setLayout(null);
		this.setBackground(null);

		JLabel lbl‹berschrift = new JLabel(scores.getAvatarName());
		lbl‹berschrift.setFont(Optionen.FONT_TITLE);
		lbl‹berschrift.setSize(360, 50);
		lbl‹berschrift.setLocation(10, 20);
		this.add(lbl‹berschrift);

		// Tabelle anzeigen
		tblScores = new JTable(new TableModelHighscore(scores));
		tblScores.setSize(290, 500);
		tblScores.setLocation(10, 80);
		tblScores.setShowGrid(false);
		tblScores.setRowHeight(50);
		tblScores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tblScores.sizeColumnsToFit(0);
		Font tblFont = new Font("Dialog", Font.PLAIN, 16);
		tblScores.setFont(tblFont);
		tblScores.setBackground(null);
		tblScores.setEnabled(false);
		this.add(tblScores);

		this.setVisible(true);

	}

	public void aktualisiereTabelle() {
		tblScores.repaint();
	}
}
