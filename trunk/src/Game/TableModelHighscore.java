package Game;

import javax.swing.table.AbstractTableModel;

public class TableModelHighscore extends AbstractTableModel {
	
	private static final long serialVersionUID = 4450620686592935031L;
	
	private Highscores scores;
	
	public TableModelHighscore(Highscores scores) {
		this.scores = scores;
	}
	
	public int getColumnCount() {
		return 2;
	}
	
	public int getRowCount() {
		if(scores.getScoresSize() > 10) {
			return 10;
		} else {
			return scores.getScoresSize();
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Score sc = scores.getScore(rowIndex);
		if(columnIndex == 0) {
			return sc.getValue();
		} else {
			return sc.getName();
		}
	}
	
	public String getColumnName(int i) {
		if(i == 0) {
			return "Punkte";
		} else {
			return "Name";
		}
	}

}
