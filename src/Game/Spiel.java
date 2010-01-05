/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abl�ufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import Game.GUI.*;

public class Spiel {
	
	//Bed�rfnisse: 1=Nahrung, 2=Gesundheit, 3=Soziales, 4=Luxus, 5=Kinder
	private Bed�rfnis[] bed�rfnisse;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int avatarNr;
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
		bed�rfnisse = avatar.getBed�rfnisse();
		kontostand = avatar.getKontostand();
		avatarNr = avatar.getAvatarNummer();
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfl�che(this);
		zeigeSpielGUI();
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
	
	
	//UNVOLLST�NDIG!!! ES FEHLEN DIE F�LLE ZEIT UND GELD!
	public void infosUmsetzen(Information[] infos)
	{
		for(int i=0; i<infos.length; i++)
		{
			//Bed�rfnisbereich
			if(infos[i].getZu�ndern()<9)
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
			//Hier sollten weitere If-F�lle f�r Zeit und Geld entstehen
		}
	}
}
