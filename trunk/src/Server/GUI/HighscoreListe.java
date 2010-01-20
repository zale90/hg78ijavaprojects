package Server.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import Game.Optionen;
import Game.SpielAnwendung;
import Server.Highscores;
import Server.TableModelHighscore;

public class HighscoreListe extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -767260215399434697L;
	
	private JTable tblScores;
	private JButton btnNeuesSpiel, btnBeendeSpiel;

	public HighscoreListe(Highscores scores) {
		
		this.setSize(300, 700);
		this.setLayout(null);
		this.setBackground(null);
		
		JLabel lbl‹berschrift = new JLabel(scores.getAvatarName());
		lbl‹berschrift.setFont(Optionen.FONT_TITLE);
		lbl‹berschrift.setSize(290, 50);
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

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnNeuesSpiel) {
			SpielAnwendung.zeigeCharakterauswahl();
		} else if(evt.getSource() == btnBeendeSpiel) {
			System.exit(0);
		}
	}
}
