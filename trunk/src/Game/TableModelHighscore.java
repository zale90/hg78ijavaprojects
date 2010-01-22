package Game;

import javax.swing.table.AbstractTableModel;

public class TableModelHighscore extends AbstractTableModel {

	private static final long serialVersionUID = 4450620686592935031L;

	private Highscores scores;

	/**
	 * Erstellt für die übergebenen Highscores ein neues (Abstract)TableModel.
	 * 
	 * @param scores
	 *            Highscores aus denen das Model erstellt werden soll.
	 */
	public TableModelHighscore(Highscores scores) {
		this.scores = scores;
	}

	/**
	 * Liefert die Anzahl der Spalten zurück.
	 */
	public int getColumnCount() {
		return 2;
	}

	/**
	 * Liefert die Anzahl der Zeilen zurück. Dabei wird höchstens 10
	 * zurückgeliefert. Bei niedrigeren Werten wird der aktuelle Wert
	 * zurückgegeben.
	 */
	public int getRowCount() {
		if (scores.getScoresSize() > 10) {
			return 10;
		} else {
			return scores.getScoresSize();
		}
	}

	/**
	 * Gibt den Inhalt der Tabelle an der übergebenen Stelle zurück.
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
	 * Gibt den Namen der gewünschten Spalte zurück. Entweter Punkte oder Name.
	 */
	public String getColumnName(int i) {
		if (i == 0) {
			return "Punkte";
		} else {
			return "Name";
		}
	}

}
