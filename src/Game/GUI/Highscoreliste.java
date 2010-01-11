package Game.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import Game.Highscores;
import Game.Optionen;
import Game.SpielAnwendung;
import Game.TableModelHighscore;

public class Highscoreliste extends JPanel implements ActionListener {

	private static final long serialVersionUID = -767260215399434697L;
	
	private JTable tblScores;
	private JButton btnNeuesSpiel, btnBeendeSpiel;

	public Highscoreliste(Highscores scores) {
		this.setSize(1000, 700);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setBackground(null);
		
		// Credits anzeigen
		this.add(new Credits());
		
		JLabel lblÜberschrift = new JLabel("Highscores: " + scores.getAvatarName());
		lblÜberschrift.setFont(Optionen.FONT_TITLE);
		lblÜberschrift.setSize(460, 50);
		lblÜberschrift.setLocation(20, 20);
		this.add(lblÜberschrift);
		
		// Tabelle anzeigen
		tblScores = new JTable(new TableModelHighscore(scores));
		tblScores.setSize(300, 500);
		tblScores.setLocation(30, 80);
		tblScores.setShowGrid(false);
		tblScores.setRowHeight(50);
		tblScores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tblScores.sizeColumnsToFit(0);
		Font tblFont = new Font("Dialog", Font.PLAIN, 16);
		tblScores.setFont(tblFont);
		tblScores.setBackground(null);
		tblScores.setEnabled(false);
		this.add(tblScores);
		
		// Buttons anzeigen
		btnNeuesSpiel = new JButton("Neues Spiel starten");
		btnNeuesSpiel.setSize(200, 50);
		btnNeuesSpiel.setLocation(20, 600);
		btnNeuesSpiel.addActionListener(this);
		this.add(btnNeuesSpiel);
		
		btnBeendeSpiel = new JButton("Spiel beenden");
		btnBeendeSpiel.setSize(200, 50);
		btnBeendeSpiel.setLocation(250, 600);
		btnBeendeSpiel.addActionListener(this);
		btnBeendeSpiel.setEnabled(false);
		this.add(btnBeendeSpiel);
		
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
