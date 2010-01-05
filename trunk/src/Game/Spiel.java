/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abläufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import Game.GUI.*;

public class Spiel {
	
	//Bedürfnisse: 1=Nahrung, 2=Gesundheit, 3=Soziales, 4=Luxus, 5=Kinder
	private Bedürfnis[] bedürfnisse;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int avatarNr;
	private Hauptfenster mainGUI;
	private Spieloberfläche gameGUI;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfläche
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar, Hauptfenster mainGUI) {
		bedürfnisse = avatar.getBedürfnisse();
		kontostand = avatar.getKontostand();
		avatarNr = avatar.getAvatarNummer();
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfläche(this);
		zeigeSpielGUI();
	}
	
	public Bedürfnis[] getBedürfnisse() {
		return bedürfnisse;
	}

	public void setBedürfnisse(Bedürfnis[] bedürfnisse) {
		this.bedürfnisse = bedürfnisse;
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
	 * Zeigt die aktuelle Spieloberfläche im Hauptfenster an!
	 */
	public void zeigeSpielGUI() {
		mainGUI.zeigePanel(gameGUI);
	}
	
	
	//UNVOLLSTÄNDIG!!! ES FEHLEN DIE FÄLLE ZEIT UND GELD!
	public void infosUmsetzen(Information[] infos)
	{
		for(int i=0; i<infos.length; i++)
		{
			//Bedürfnisbereich
			if(infos[i].getZuÄndern()<9)
			{
				int neuerWert = 0;
				Bedürfnis referenz = null;
				switch(infos[i].getZuÄndern())
				{
				case 1: {neuerWert = bedürfnisse[1].getWert();
						referenz = bedürfnisse[1];}
				case 2: {neuerWert = bedürfnisse[1].getAbfallfaktor();
						referenz = bedürfnisse[1];}
				case 3: {neuerWert = bedürfnisse[2].getWert();
						referenz = bedürfnisse[2];}
				case 4: {neuerWert = bedürfnisse[2].getAbfallfaktor();
						referenz = bedürfnisse[2];}
				case 5: {neuerWert = bedürfnisse[3].getWert();
						referenz = bedürfnisse[3];}
				case 6: {neuerWert = bedürfnisse[3].getAbfallfaktor();
						referenz = bedürfnisse[3];}
				case 7: {neuerWert = bedürfnisse[4].getWert();
						referenz = bedürfnisse[4];}
				case 8: {neuerWert = bedürfnisse[4].getAbfallfaktor();
						referenz = bedürfnisse[4];}
				case 9: {neuerWert = bedürfnisse[5].getWert();
						referenz = bedürfnisse[5];}
				case 10: {neuerWert = bedürfnisse[5].getAbfallfaktor();
						 referenz = bedürfnisse[5];}
				}
				
				switch(infos[i].getÄnderungsart())
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
			//Hier sollten weitere If-Fälle für Zeit und Geld entstehen
		}
	}
}
