/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abl�ufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import java.util.ArrayList;
import Game.GUI.*;

public class Spiel {
	
	//Bed�rfnisse: 0=Nahrung, 1=Gesundheit, 2=Soziales, 3=Luxus, 4=Kinder
	private Bed�rfnis[] bed�rfnisse;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int avatarNr;
	private int aktuelleRunde;
	private int score;
	private ArrayList<Integer> genutzteEreignisse;
	private Hauptfenster mainGUI;
	private Spieloberfl�che gameGUI;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfl�che
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar, Hauptfenster mainGUI) {
		aktuelleRunde = 0;
		bed�rfnisse = avatar.getBed�rfnisse();
		kontostand = avatar.getKontostand();
		geldProMonat = avatar.getEinkommen();
		zeitProRunde = avatar.getZeitProRunde();
		avatarNr = avatar.getAvatarNummer();
		score = 0;
		zeit = zeitProRunde;
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfl�che(this, avatar.getName());
		zeigeSpielGUI();
		genutzteEreignisse = new ArrayList<Integer>();
	}
	
	public Bed�rfnis[] getBed�rfnisse() {
		return bed�rfnisse;
	}

	public void setBed�rfnisse(Bed�rfnis[] bed�rfnisse) {
		this.bed�rfnisse = bed�rfnisse;
	}

	public int getZeit() {
		return zeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	public int getZeitProRunde() {
		return zeitProRunde;
	}

	public void setZeitProRunde(int zeitProRunde) {
		this.zeitProRunde = zeitProRunde;
	}

	public int getKontostand() {
		return kontostand;
	}

	public void setKontostand(int kontostand) {
		this.kontostand = kontostand;
	}

	public int getGeldProMonat() {
		return geldProMonat;
	}

	public void setGeldProMonat(int geldProMonat) {
		this.geldProMonat = geldProMonat;
	}

	public int getAvatarNr() {
		return avatarNr;
	}

	public void setAvatarNr(int avatarNr) {
		this.avatarNr = avatarNr;
	}

	/**
	 * Zeigt die aktuelle Spieloberfl�che im Hauptfenster an!
	 */
	public void zeigeSpielGUI() {
		mainGUI.zeigePanel(gameGUI);
	}
	
	public void naechsteRunde()
	{
		scoresAddieren();
		aktuelleRunde = aktuelleRunde + 1;
		if ((aktuelleRunde % 4) == 0)
		{
			kontostand = kontostand + geldProMonat;
		}
		for(int i = 0; i < bed�rfnisse.length; i++) {
			bed�rfnisse[i].setWert(bed�rfnisse[i].getWert()-bed�rfnisse[i].getAbfallfaktor());
			if (bed�rfnisse[i].getWert() < bed�rfnisse[i].getMin())
			{
				// Man hat verloren; was tun?
			}
		}
		
		// Ereignis ausf�hren
		Ereignis er = getRandomEreignis();
		er.ausf�hren();
		
	}
	
	public int getAktuelleRunde()
	{
		return aktuelleRunde;
	}
	
	/**
	 * Erstellt neue Ereignisse, bis eins kommt, welches noch nie vorhanden war!
	 * @return Ereignis
	 */
	public Ereignis getRandomEreignis() {
		Ereignis er = null;
		while(er == null || genutzteEreignisse.contains(er.getNummer())) {
			er = new Ereignis();
		}
		genutzteEreignisse.add(er.getNummer());
		return er;
	}
	
	
	
	
	//===================================================
	//UNVOLLST�NDIG!!! ES FEHLEN DIE F�LLE ZEIT UND GELD!
	//===================================================
	
	public void infosUmsetzen(Information[] infos)
	{
		for(int i=0; i<infos.length; i++)
		{
			//Bed�rfnisbereich
			if(infos[i].getZu�ndern()<11)
			{
				int neuerWert = 0;
				Bed�rfnis referenz = null;
				switch(infos[i].getZu�ndern())
				{
				case 1: {neuerWert = bed�rfnisse[1].getWert();
						referenz = bed�rfnisse[1];}
				case 2: {neuerWert = bed�rfnisse[1].getAbfallfaktor();
						referenz = bed�rfnisse[1];}
				case 3: {neuerWert = bed�rfnisse[2].getWert();
						referenz = bed�rfnisse[2];}
				case 4: {neuerWert = bed�rfnisse[2].getAbfallfaktor();
						referenz = bed�rfnisse[2];}
				case 5: {neuerWert = bed�rfnisse[3].getWert();
						referenz = bed�rfnisse[3];}
				case 6: {neuerWert = bed�rfnisse[3].getAbfallfaktor();
						referenz = bed�rfnisse[3];}
				case 7: {neuerWert = bed�rfnisse[4].getWert();
						referenz = bed�rfnisse[4];}
				case 8: {neuerWert = bed�rfnisse[4].getAbfallfaktor();
						referenz = bed�rfnisse[4];}
				case 9: {neuerWert = bed�rfnisse[5].getWert();
						referenz = bed�rfnisse[5];}
				case 10: {neuerWert = bed�rfnisse[5].getAbfallfaktor();
						 referenz = bed�rfnisse[5];}
				}
				
				switch(infos[i].get�nderungsart())
				{
				case 1:
					{
						if(infos[i].getWert()>=referenz.getMin() && infos[i].getWert()<=referenz.getMax())
							neuerWert = infos[i].getWert();
						else
						{
							if(infos[i].getWert()<referenz.getMin())
								neuerWert = referenz.getMin();
							else
								neuerWert = referenz.getMax();
						}
					}
				case 2:
					{
						int tmp = neuerWert + infos[i].getWert();
						if(tmp>=referenz.getMin() && tmp<=referenz.getMax())
							neuerWert = tmp;
						else
						{
							if(tmp<referenz.getMin())
								neuerWert = referenz.getMin();
							else
								neuerWert = referenz.getMax();
						}
					}
				case 3:
					{
						double tmp = neuerWert*infos[i].getWert();
						int tmp2 = (int)tmp; //so grob
						if(tmp2>=referenz.getMin() && tmp2<=referenz.getMax())
							neuerWert = tmp2;
						else
						{
							if(tmp2<referenz.getMin())
								neuerWert = referenz.getMin();
							else
								neuerWert = referenz.getMax();
						}
					}
				case 4:
					{
						double tmp = neuerWert*infos[i].getWert();
						int tmp2 = (int)tmp; //so grob
						tmp2 = neuerWert + tmp2;
						if(tmp2>=referenz.getMin() && tmp2<=referenz.getMax())
							neuerWert = tmp2;
						else
						{
							if(tmp2<referenz.getMin())
								neuerWert = referenz.getMin();
							else
								neuerWert = referenz.getMax();
						}
					}
				}	
			}
			if(infos[i].getZu�ndern() == 11)
			{
				int neuerWert = zeit;
				
				switch(infos[i].get�nderungsart())
				{
				case 1:
					{
						if(infos[i].getWert()>0)
							neuerWert = infos[i].getWert();
						else
						{
							neuerWert = 0;
						}
					}
				case 2:
					{
						int tmp = neuerWert + infos[i].getWert();
						if(tmp > 0)
							neuerWert = tmp;
						else
						{
							neuerWert = 0;
						}
					}
				case 3:
					{
						double tmp = neuerWert*infos[i].getWert();
						int tmp2 = (int)tmp; //so grob
						if(tmp2>0)
							neuerWert = tmp2;
						else
						{
							neuerWert = 0;
						}
					}
				case 4:
					{
						double tmp = neuerWert*infos[i].getWert();
						int tmp2 = (int)tmp; //so grob
						tmp2 = neuerWert + tmp2;
						if(tmp2>0)
							neuerWert = tmp2;
						else
						{
							neuerWert = 0;
						}
					}
				}
			}
			if(infos[i].getZu�ndern()>11 && infos[i].getZu�ndern()<15)
			{
				
			}
		}
	}
	
	//Addiert jede Runde Punkte auf den Punktestand neu auf.
	public void scoresAddieren() {
		for(Bed�rfnis b: bed�rfnisse) {
			score += b.getWert();
		}
		score += kontostand/15;
	}
}