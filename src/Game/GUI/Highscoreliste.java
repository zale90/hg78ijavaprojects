package Game.GUI;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import Game.Highscores;
import Game.Optionen;
import Game.TableModelHighscore;

public class Highscoreliste extends JPanel {

	private static final long serialVersionUID = -767260215399434697L;
	
	private JTable tblScores;

	public Highscoreliste(Highscores scores) {
		this.setSize(1000, 700);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);
		
		// Credits anzeigen
		this.add(new Credits());
		
		JLabel lbl‹berschrift = new JLabel("Highscores");
		lbl‹berschrift.setFont(Optionen.FONT_TITLE);
		lbl‹berschrift.setSize(400, 50);
		lbl‹berschrift.setLocation(20, 20);
		this.add(lbl‹berschrift);
		
		// Tabelle anzeigen
		tblScores = new JTable(new TableModelHighscore(scores));
		tblScores.setSize(300, 500);
		tblScores.setLocation(50, 100);
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
	
}
