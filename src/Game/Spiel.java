/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abl�ufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import java.util.*;
import Game.GUI.*;

public class Spiel {
	
	private Bed�rfnis[] bed�rfnisse;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int avatarNr;
	private int aktuelleRunde;
	private int score;
	private Hauptfenster mainGUI;
	private Spieloberfl�che gameGUI;
	private ArrayList<Ereignis> erList;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfl�che
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar, Hauptfenster mainGUI) {
		aktuelleRunde = 0;
		bed�rfnisse = avatar.getBed�rfnisse().clone();
		kontostand = avatar.getKontostand();
		geldProMonat = avatar.getEinkommen();
		zeitProRunde = avatar.getZeitProRunde();
		avatarNr = avatar.getAvatarNummer();
		score = 0;
		zeit = zeitProRunde;
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfl�che(this, avatar.getName());
		zeigeSpielGUI();
		erList = Initialisator.gibEreignisse();
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
//		for(int i = 0; i < bed�rfnisse.length; i++) {
//			bed�rfnisse[i].setWert(bed�rfnisse[i].getWert()-bed�rfnisse[i].getAbfallfaktor());
//			if (bed�rfnisse[i].getWert() < bed�rfnisse[i].getMin())
//			{
//				// Man hat verloren; was tun?
//			}
//		}
		
		// Ereignis ausf�hren
		Ereignis er = getRandomEreignis();
		infosUmsetzen(er.ausf�hren());
		
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
		Random rand = new Random();
		int i = rand.nextInt(erList.size());
		Ereignis er = erList.get(i);
		erList.remove(i);
		return er;
	}
	
	
	
	
	//===================================================
	//UNVOLLST�NDIG!!! ES FEHLEN DIE F�LLE ZEIT UND GELD!
	//===================================================
	
	public void infosUmsetzen(Information[] infos)
	{
		if(infos == null){}
		else
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
				case 1: {neuerWert = bed�rfnisse[0].getWert();
						referenz = bed�rfnisse[0];
						break;}
				case 2: {neuerWert = bed�rfnisse[0].getAbfallfaktor();
						referenz = bed�rfnisse[0];
						break;}
				case 3: {neuerWert = bed�rfnisse[1].getWert();
						referenz = bed�rfnisse[1];
						break;}
				case 4: {neuerWert = bed�rfnisse[1].getAbfallfaktor();
						referenz = bed�rfnisse[1];
						break;}
				case 5: {neuerWert = bed�rfnisse[2].getWert();
						referenz = bed�rfnisse[2];
						break;}
				case 6: {neuerWert = bed�rfnisse[2].getAbfallfaktor();
						referenz = bed�rfnisse[2];
						break;}
				case 7: {neuerWert = bed�rfnisse[3].getWert();
						referenz = bed�rfnisse[3];
						break;}
				case 8: {neuerWert = bed�rfnisse[3].getAbfallfaktor();
						referenz = bed�rfnisse[3];
						break;}
				case 9: {neuerWert = bed�rfnisse[4].getWert();
						referenz = bed�rfnisse[4];
						break;}
				case 10: {neuerWert = bed�rfnisse[4].getAbfallfaktor();
						 referenz = bed�rfnisse[4];
						 break;}
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
						break;
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
						break;
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
						break;
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
						break;
					}
				}
				
				switch(infos[i].getZu�ndern())
				{
				case 1: bed�rfnisse[0].setWert(neuerWert);
						break;
				case 2: bed�rfnisse[0].setAbfallfaktor(neuerWert);
						break;
				case 3: bed�rfnisse[1].setWert(neuerWert);
						break;
				case 4: bed�rfnisse[1].setAbfallfaktor(neuerWert);
						break;
				case 5: bed�rfnisse[2].setWert(neuerWert);
						break;
				case 6: bed�rfnisse[2].setAbfallfaktor(neuerWert);
						break;
				case 7: bed�rfnisse[3].setWert(neuerWert);
						break;		
				case 8: bed�rfnisse[3].setAbfallfaktor(neuerWert);
						break;
				case 9: bed�rfnisse[4].setWert(neuerWert);
				        break;
				case 10: bed�rfnisse[4].setAbfallfaktor(neuerWert);
						 break;
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
						break;
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
						break;
					}
				case 3:
					{
						double tmp = neuerWert*infos[i].getWert()/100;
						int tmp2 = (int)tmp; //so grob
						if(tmp2>0)
							neuerWert = tmp2;
						else
						{
							neuerWert = 0;
						}
						break;
					}
				case 4:
					{
						double tmp = neuerWert*infos[i].getWert()/100;
						int tmp2 = (int)tmp; //so grob
						tmp2 = neuerWert + tmp2;
						if(tmp2>0)
							neuerWert = tmp2;
						else
						{
							neuerWert = 0;
						}
						break;
					}
				}
				zeit = neuerWert;
			}
			if(infos[i].getZu�ndern()>11 && infos[i].getZu�ndern()<15)
			{
				int neuerWert = 0;
				switch(infos[i].getZu�ndern())
				{
				case 12: neuerWert = zeitProRunde;
						 break;
				case 13: neuerWert = kontostand;
				         break;
				case 14: neuerWert = geldProMonat;
		         		 break;
				}
				switch(infos[i].get�nderungsart())
				{
				case 1: neuerWert = infos[i].getWert();
						break;
				case 2: neuerWert = infos[i].getWert() + neuerWert;
						break;
				case 3: neuerWert = (int)(neuerWert * infos[i].getWert() / 100);
						break;
				case 4: neuerWert = neuerWert + (int)(neuerWert * infos[i].getWert() /100);
						break;
				}
				switch(infos[i].getZu�ndern())
				{
				case 12: zeitProRunde = neuerWert;
						 break;
				case 13: kontostand = neuerWert;
						 break;
				case 14: geldProMonat = neuerWert;
						 break;
				}
			}
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
