package Game;

import javax.swing.table.AbstractTableModel;

public class TableModelHighscore extends AbstractTableModel {

	private static final long serialVersionUID = 4450620686592935031L;

	private Highscores scores;

	/**
	 * Erstellt f�r die �bergebenen Highscores ein neues (Abstract)TableModel.
	 * 
	 * @param scores
	 *            Highscores aus denen das Model erstellt werden soll.
	 */
	public TableModelHighscore(Highscores scores) {
		this.scores = scores;
	}

	/**
	 * Liefert die Anzahl der Spalten zur�ck.
	 */
	public int getColumnCount() {
		return 2;
	}

	/**
	 * Liefert die Anzahl der Zeilen zur�ck. Dabei wird h�chstens 10
	 * zur�ckgeliefert. Bei niedrigeren Werten wird der aktuelle Wert
	 * zur�ckgegeben.
	 */
	public int getRowCount() {
		if (scores.getScoresSize() > 10) {
			return 10;
		} else {
			return scores.getScoresSize();
		}
	}

	/**
	 * Gibt den Inhalt der Tabelle an der �bergebenen Stelle zur�ck.
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Score sc = scores.getScore(rowIndex);
		if (columnIndex == 0) {
			return sc.getValue();
		} else {
			return sc.getName();
		}
	}

	/**
	 * Gibt den Namen der gew�nschten Spalte zur�ck. Entweter Punkte oder Name.
	 */
	public String getColumnName(int i) {
		if (i == 0) {
			return "Punkte";
		} else {
			return "Name";
		}
	}

}
