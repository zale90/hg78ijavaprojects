/**
 * Stellt das eigentliche Spiel da, in dem alle
 * wesentlichen Abläufe geschehen und verarbeitet
 * werden.
 * 
 * @author Philipp S.
 * @version 17.12.2009
 */

package Game;

import java.util.*;

import javax.swing.JOptionPane;

import Game.GUI.*;

public class Spiel {
	
	private Bedürfnis[] bedürfnisse;
	private int avatarNr;
	private String avatarName;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int aktuelleRunde;
	private int punkte;
	private Hauptfenster mainGUI;
	private Spieloberfläche gameGUI;
	private ArrayList<Ereignis> erList;
	
	/**
	 * Ein neues Spielt wird anhand der Informationen
	 * eines Avatars erstellt und die Spieloberfläche
	 * wird angezeigt.
	 * 
	 * @param avatar
	 */
	public Spiel(Avatar avatar, Hauptfenster mainGUI) {
		bedürfnisse = avatar.getBedürfnisse().clone();
		kontostand = avatar.getKontostand();
		geldProMonat = avatar.getEinkommen();
		zeitProRunde = avatar.getZeitProRunde();
		avatarNr = avatar.getAvatarNummer();
		avatarName = avatar.getName();
		
		// Startwerte
		aktuelleRunde = 0;
		punkte = 0;
		zeit = zeitProRunde;
		
		// GUI
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfläche(this, avatar.getName());
		zeigeSpielGUI();
		
		// Ereignisse erzeugen
		erList = Initialisator.gibEreignisse();
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

	public int getZeitProRunde() {
		return zeitProRunde;
	}

	public int getKontostand() {
		return kontostand;
	}

	public int getGeldProMonat() {
		return geldProMonat;
	}

	public int getAvatarNr() {
		return avatarNr;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public int getPunkte() {
		return punkte;
	}

	public ArrayList<Ereignis> getErList() {
		return erList;
	}
	
	public int getAktuelleRunde() {
		return aktuelleRunde;
	}

	/**
	 * Zeigt die aktuelle Spieloberfläche im Hauptfenster an!
	 */
	public void zeigeSpielGUI() {
		mainGUI.zeigePanel(gameGUI);
	}
	
	/**
	 * Beendet die aktuelle Runde und wechselt in die nächste.
	 */
	public void naechsteRunde() {
		gameGUI.setzeAktiviert(true);
		aktuelleRunde++;
		
		// Bei neuem Monat Geld auszahlen
		if ((aktuelleRunde % 4) == 0) {
			kontostand = kontostand + geldProMonat;
		}
		
		// Bedürfnisse um Abfallfaktor reduzieren
		bedürfnisseFallen();
		
		// Ereignisse auslösen
		Ereignis er = getRandomEreignis();
		infosUmsetzen(er.ausführen());
		gameGUI.aktualisiereDaten();
		
		// Überprüfen ob Spieler verloren hat
		if(istBedürfnisAufMinimum()) {
			spielBeenden();
			return;
		}
	}
	
	/**
	 * Liefert true wenn mindestens ein Bedürfnis auf dem Minimum ist.
	 * Sind alle höher als das Minimum, liefert die Methode false.
	 * 
	 * @return true wenn mindestens ein Bedürfnis auf Minimum.
	 */
	private boolean istBedürfnisAufMinimum() {
		boolean minimum = false;
		for(int i = 0; i < bedürfnisse.length; i++) {
			if(bedürfnisse[i].istMinimum()) {
				minimum = true;
			}
		}
		return minimum;
	}
	
	/**
	 * Lässt die Bedürfnisse um den Abfallfaktor fallen.
	 */
	private void bedürfnisseFallen() {
		for(int i = 0; i < bedürfnisse.length; i++) {
			bedürfnisse[i].setWert(bedürfnisse[i].getWert()-bedürfnisse[i].getAbfallfaktor());
		}
	}
	
	/**
	 * Dialog zur Eingabe des Namens wird angezeigt.
	 * Anschließend wird das Spiel beendet und man sieht die Highscoreliste.
	 */
	private void spielBeenden() {
		String text = "Du hast das Leben leider nicht meistern können. \nDas Spiel ist deshalb für dich Leider vorbei. \n\nImmerhin hast du " + getPunkte() + " Punkte erspielt. \n\nUm in dich in die Highscoreliste einzutragen, gib hier deinen Namen ein:";
		String antwort = JOptionPane.showInputDialog(SpielAnwendung.mainGUI, text, "Verloren!", JOptionPane.PLAIN_MESSAGE);
		if(antwort == null || antwort.trim().equals("")) {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName());
		} else {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName(), new Score(antwort, getPunkte()));
		}
	}
	
	/**
	 * Liefert ein zufälliges Ereignis aus der erList zurück und löscht dieses dann.
	 * 
	 * @return zufälliges Ereignis
	 */
	public Ereignis getRandomEreignis() {
		Random rand = new Random();
		int i = rand.nextInt(erList.size());
		Ereignis er = erList.get(i);
		erList.remove(i);
		return er;
	}
		
	public void aktionAusfuehren(Aktion aktion)
	{
		infosUmsetzen(aktion.getVeraenderungen());
		if (aktion.getMinispiel() != null)
		{
			gameGUI.setzeAktiviert(false);
			aktion.getMinispiel().start(this);
		}
		gameGUI.zeigeNachrichtInKonsole(aktion.getKonsolenausgabe());
		gameGUI.aktualisiereDaten();
	}
	
	public void minispielEnde(Information[] infos)
	{
//		Information[] infoArray = new Information[1];
//		infoArray[0] = info;
		infosUmsetzen(infos);
		gameGUI.setzeAktiviert(true);
		gameGUI.aktualisiereDaten();
	}
	
	//Eigentlich voll schwachsinnig die Methode, ich bin trotzdem stolz. ;)
	public void infosUmsetzen(Information[] infos)
	{
		if(infos == null){}
		else
		{
		for(int i=0; i<infos.length; i++)
		{
			//Bedürfnisbereich
			if(infos[i].getZuÄndern()<11)
			{
				int neuerWert = 0;
				Bedürfnis referenz = null;
				switch(infos[i].getZuÄndern())
				{
				case 1: {neuerWert = bedürfnisse[0].getWert();
						referenz = bedürfnisse[0];
						break;}
				case 2: {neuerWert = bedürfnisse[0].getAbfallfaktor();
						referenz = bedürfnisse[0];
						break;}
				case 3: {neuerWert = bedürfnisse[1].getWert();
						referenz = bedürfnisse[1];
						break;}
				case 4: {neuerWert = bedürfnisse[1].getAbfallfaktor();
						referenz = bedürfnisse[1];
						break;}
				case 5: {neuerWert = bedürfnisse[2].getWert();
						referenz = bedürfnisse[2];
						break;}
				case 6: {neuerWert = bedürfnisse[2].getAbfallfaktor();
						referenz = bedürfnisse[2];
						break;}
				case 7: {neuerWert = bedürfnisse[3].getWert();
						referenz = bedürfnisse[3];
						break;}
				case 8: {neuerWert = bedürfnisse[3].getAbfallfaktor();
						referenz = bedürfnisse[3];
						break;}
				case 9: {neuerWert = bedürfnisse[4].getWert();
						referenz = bedürfnisse[4];
						break;}
				case 10: {neuerWert = bedürfnisse[4].getAbfallfaktor();
						 referenz = bedürfnisse[4];
						 break;}
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
				
				switch(infos[i].getZuÄndern())
				{
				case 1: bedürfnisse[0].setWert(neuerWert);
						break;
				case 2: bedürfnisse[0].setAbfallfaktor(neuerWert);
						break;
				case 3: bedürfnisse[1].setWert(neuerWert);
						break;
				case 4: bedürfnisse[1].setAbfallfaktor(neuerWert);
						break;
				case 5: bedürfnisse[2].setWert(neuerWert);
						break;
				case 6: bedürfnisse[2].setAbfallfaktor(neuerWert);
						break;
				case 7: bedürfnisse[3].setWert(neuerWert);
						break;		
				case 8: bedürfnisse[3].setAbfallfaktor(neuerWert);
						break;
				case 9: bedürfnisse[4].setWert(neuerWert);
				        break;
				case 10: bedürfnisse[4].setAbfallfaktor(neuerWert);
						 break;
				}
			}
			if(infos[i].getZuÄndern() == 11)
			{
				int neuerWert = zeit;
				
				switch(infos[i].getÄnderungsart())
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
			if(infos[i].getZuÄndern()>11 && infos[i].getZuÄndern()<15)
			{
				int neuerWert = 0;
				switch(infos[i].getZuÄndern())
				{
				case 12: neuerWert = zeitProRunde;
						 break;
				case 13: neuerWert = kontostand;
				         break;
				case 14: neuerWert = geldProMonat;
		         		 break;
				}
				switch(infos[i].getÄnderungsart())
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
				switch(infos[i].getZuÄndern())
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
	public void punkteBerechnen(PunkteGUI gui) 
	{
		
		int hungerP = bedürfnisse[0].getWert();
		int gesundheitP = bedürfnisse[1].getWert();
		int sozialesP = bedürfnisse[2].getWert();
		int luxusP = bedürfnisse[3].getWert();
		int geldP = (int)(kontostand/15);
		
		int punkteAlt = punkte;
		punkte = punkte + hungerP + gesundheitP + sozialesP + luxusP + geldP;
		
		gui.setzeWerte(hungerP, gesundheitP, sozialesP, luxusP, geldP, punkteAlt);
	}
}
