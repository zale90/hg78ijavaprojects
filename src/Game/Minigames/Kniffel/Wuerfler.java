package Game.Minigames.Kniffel;

import java.util.Random;

public class Wuerfler implements Runnable {
	final int minMillSec = 2000, maxMillSec = 6000, maxWarteZeit = 500;
	int position;
	Kniffel spiel;
	int laufzeit;

	public Wuerfler(int wuerfelPosition, Kniffel spiel) {
		this.spiel = spiel;
		this.position = wuerfelPosition;
		laufzeit = (int) (Math.random() * (maxMillSec - minMillSec) + minMillSec);
	}

	public void run() {
		while (laufzeit > 0) {
			wuerfel();

			int sleep = (int) (((double) maxWarteZeit / -maxMillSec) * laufzeit + maxWarteZeit);
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			laufzeit -= sleep;
		}
		spiel.wuerfelGefallen(position);
	}

	private void wuerfel() {
		Random zufall = new Random();
		int nächsteZahl = zufall.nextInt(spiel.zahlen.length);
		spiel.wuerfel[position].setIcon(spiel.zahlen[nächsteZahl]);
		spiel.wuerfel[position].updateUI();
	}

}
