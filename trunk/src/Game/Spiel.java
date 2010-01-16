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

import javax.swing.JOptionPane;

import Game.GUI.*;
import Game.Minigames.Minispiel;

public class Spiel {
	
	private Bed�rfnis[] bed�rfnisse;
	private int avatarNr;
	private String avatarName;
	private int zeit;
	private int zeitProRunde;
	private int kontostand;
	private int geldProMonat;
	private int aktuelleRunde;
	private int punkte;
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
		bed�rfnisse = avatar.getBed�rfnisse().clone();
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
		gameGUI = new Spieloberfl�che(this, avatar.getName());
		zeigeSpielGUI();
		
		// Ereignisse erzeugen
		erList = Initialisator.gibEreignisse();
		ArrayList<Ereignis> kinderErList = Initialisator.gibKinderEreignisse();
		if(avatar.getAvatarNummer()> 1)
		{
			for(Ereignis er : kinderErList)
			{
				erList.add(er);
			}
		}
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
	 * Zeigt die aktuelle Spieloberfl�che im Hauptfenster an!
	 */
	public void zeigeSpielGUI() {
		mainGUI.zeigePanel(gameGUI);
	}
	
	/**
	 * Beendet die aktuelle Runde und wechselt in die n�chste.
	 */
	public void naechsteRunde() {
		// �berpr�fen ob Spieler verloren hat
		if(istBed�rfnisAufMinimum()) {
			spielBeenden();
			return;
		}
		//gameGUI.setzeAktiviert(true);
		aktuelleRunde++;
		zeit = zeitProRunde;
		
		// Bei neuem Monat Geld auszahlen
		if ((aktuelleRunde % 4) == 0) {
			kontostand = kontostand + geldProMonat;
		}
		
		// Bed�rfnisse um Abfallfaktor reduzieren
		bed�rfnisseFallen();
		gameGUI.aktualisiereDaten();
		
		// Ereignisse ausl�sen
		Ereignis er = getRandomEreignis();
		gameGUI.getEreignisfenster().fensterZeigen(er);
	}
	
	/**
	 * Liefert true wenn mindestens ein Bed�rfnis auf dem Minimum ist.
	 * Sind alle h�her als das Minimum, liefert die Methode false.
	 * 
	 * @return true wenn mindestens ein Bed�rfnis auf Minimum.
	 */
	private boolean istBed�rfnisAufMinimum() {
		boolean minimum = false;
		for(int i = 0; i < bed�rfnisse.length; i++) {
			if(bed�rfnisse[i].istMinimum()) {
				minimum = true;
			}
		}
		return minimum;
	}
	
	/**
	 * L�sst die Bed�rfnisse um den Abfallfaktor fallen.
	 */
	private void bed�rfnisseFallen() {
		for(int i = 0; i < bed�rfnisse.length; i++) {
			bed�rfnisse[i].setWert(bed�rfnisse[i].getWert()-bed�rfnisse[i].getAbfallfaktor());
		}
	}
	
	/**
	 * Dialog zur Eingabe des Namens wird angezeigt.
	 * Anschlie�end wird das Spiel beendet und man sieht die Highscoreliste.
	 */
	private void spielBeenden() {
		String text = "Du hast das Leben leider nicht meistern k�nnen. \nDas Spiel ist deshalb f�r dich Leider vorbei. \n\nImmerhin hast du " + getPunkte() + " Punkte erspielt. \n\nUm in dich in die Highscoreliste einzutragen, gib hier deinen Namen ein:";
		String antwort = JOptionPane.showInputDialog(SpielAnwendung.mainGUI, text, "Verloren!", JOptionPane.PLAIN_MESSAGE);
		if(antwort == null || antwort.trim().equals("")) {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName());
		} else {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName(), new Score(antwort, getPunkte()));
		}
	}
	
	/**
	 * Liefert ein zuf�lliges Ereignis aus der erList zur�ck und l�scht dieses dann.
	 * 
	 * @return zuf�lliges Ereignis
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
		ArrayList<String> probs = getProblemBeduerfnisse(aktion.getVeraenderungen());
		if (probs != null && probs.size() != 0)
		{
			ArrayList<String> probleme = new ArrayList<String>();
			for (String prob: probs)
			{
				if (prob.equals("Zeit"))
				{    
					probleme.add("Zeit");
				}
				if (prob.equals("Geld"))
				{    
					probleme.add("Geld");
				}
			}
			if (probleme.size() != 0)
			{
				String gruendeFormatiert = "";
				for (int i = 0; i < probleme.size(); i++)
				{
					gruendeFormatiert = gruendeFormatiert + probleme.get(i);
					if ((i+2) == probs.size())
						gruendeFormatiert = gruendeFormatiert + " und ";
					else if ((i+1) != probs.size())
						gruendeFormatiert = gruendeFormatiert + ", ";
				}
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(SpielAnwendung.mainGUI, "Diese Aktion kannst du nicht ausf�hren, da du nicht genug " + gruendeFormatiert + " hast.", 
                        "Aktion nicht ausf�hrbar", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[0]);
				return;
			}
			else
			{
				String gruendeFormatiert = "";
				for (int i = 0; i < probs.size(); i++)
				{
					gruendeFormatiert = gruendeFormatiert + probs.get(i);
					if ((i+2) == probs.size())
						gruendeFormatiert = gruendeFormatiert + " und ";
					else if ((i+1) != probs.size())
						gruendeFormatiert = gruendeFormatiert + ", ";
				}
				Object[] options = {"Ja", "Nein"};
				if (JOptionPane.showOptionDialog(SpielAnwendung.mainGUI, "Wenn du diese Aktion ausf�hrst werden die Bed�rfnisse " + gruendeFormatiert + " auf ihr Minimum sinken.\nWillst du diese Aktion wirklich ausf�hren?", 
                        "Warnung", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[0]) == JOptionPane.NO_OPTION)
						return;
			}
		}
		infosUmsetzen(aktion.getVeraenderungen());
		if (aktion.getMinispiel() != null)
		{
			gameGUI.setzeAktiviert(false);
			minispielStarten(aktion.getMinispiel());
		}
		gameGUI.zeigeNachrichtInKonsole(aktion.getKonsolenausgabe());
		gameGUI.aktualisiereDaten();
	}
	public ArrayList<String> getProblemBeduerfnisse(Information[] infos)
	{
			if (infos != null && infos.length != 0)
			{
				ArrayList<String> rueckgabe = new ArrayList<String>();
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
								double tmp = neuerWert*infos[i].getWert()/100;
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
						case 1: if (bed�rfnisse[0].getMin() >= neuerWert)
									rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 2: if (bed�rfnisse[0].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 3: if (bed�rfnisse[1].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 4: if (bed�rfnisse[1].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 5: if (bed�rfnisse[2].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 6: if (bed�rfnisse[2].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 7: if (bed�rfnisse[3].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;		
						case 8: if (bed�rfnisse[3].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
								break;
						case 9: if (bed�rfnisse[4].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
						        break;
						case 10: if (bed�rfnisse[4].getMin() >= neuerWert)
							rueckgabe.add(bed�rfnisse[0].getName());
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
								neuerWert = tmp;
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
						if (neuerWert < 0)
							rueckgabe.add("Zeit");
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
						case 12: if(neuerWert < 0)
									rueckgabe.add("Zeit pro Runde");
								 break;
						case 13: if (neuerWert < 0)
									rueckgabe.add("Geld");
								 break;
						case 14: if (neuerWert < 0)
									rueckgabe.add("Einkommen");
								 break;
						}
					}
				}
				return rueckgabe;
			}
			return null;
	}
	
	/**
	 * Wird direkt vor beenden eines Minispiels aufgerufen um dessen Informationen zu verarbeiten und
	 * das Hauptspiel wieder freizugeben.
	 * 
	 * @param infos Informations-Array, das alle Informationen beinhaltet, die umgesetzt werden sollen.
	 * @param konsolennachricht Nachricht die in der Konsole des Spiels angezeigt werden soll.
	 */
	public void minispielEnde(Information[] infos, String konsolennachricht) {
		// Nachricht auf Konsole, wenn vorhaden
		if (konsolennachricht != null && konsolennachricht.trim() != "")
			gameGUI.zeigeNachrichtInKonsole(konsolennachricht);
		
		// Infos verarbeiten
		infosUmsetzen(infos);
		gameGUI.setzeAktiviert(true);
		gameGUI.aktualisiereDaten();
	}
	
	/**
	 * Wird direkt vor beenden eines Minispiels aufgerufen um dessen Informationen zu verarbeiten und
	 * das Hauptspiel wieder freizugeben.
	 * 
	 * @param infos Informations-Array, das alle Informationen beinhaltet, die umgesetzt werden sollen.
	 */
	public void minispielEnde(Information[] infos) {
		minispielEnde(infos, null);
	}
	
	/**
	 * Setzt alle Informationen des �bergebenen Informations-Arrays um.
	 * 
	 * @param infos Infos die umgesetzt werden sollen.
	 */
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
						double tmp = neuerWert*infos[i].getWert()/100;
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
	
	/**
	 * Addiert in jeder Runde die neu erhaltenen Punkte auf die bereits vorhandenen.
	 * 
	 * @param gui PunkteGUI in dem die Daten angezeigt werden sollen.
	 */
	public void punkteBerechnen(PunkteGUI gui) 
	{
		
		int hungerP = bed�rfnisse[0].getWert();
		int gesundheitP = bed�rfnisse[1].getWert();
		int sozialesP = bed�rfnisse[2].getWert();
		int luxusP = bed�rfnisse[3].getWert();
		int geldP = (int)(kontostand/15);
		
		int punkteAlt = punkte;
		punkte = punkte + hungerP + gesundheitP + sozialesP + luxusP + geldP;
		
		gui.setzeWerte(hungerP, gesundheitP, sozialesP, luxusP, geldP, punkteAlt);
	}
	public void minispielStarten(Minispiel minispiel)
	{
		minispiel.start(this);
	}
}
