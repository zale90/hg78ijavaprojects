package Game;

import java.util.*;

import javax.swing.JOptionPane;

import Game.GUI.*;
import Game.Minigames.Minispiel;

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
	private int bewerbungsfaktor;
	private Hauptfenster mainGUI;
	private Spieloberfläche gameGUI;
	private Finanzen finanzen;
	private ArrayList<Ereignis> erList;
	private ArrayList<Integer> grenzenObenUndUnten, grenzeUnten, keineGrenze,
			braucheWert, braucheFaktor;

	/**
	 * Ein neues Spielt wird anhand der Informationen eines Avatars erstellt und
	 * die Spieloberfläche wird angezeigt.
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
		bewerbungsfaktor = 1;
		finanzen = new Finanzen(this);

		// GUI
		this.mainGUI = mainGUI;
		gameGUI = new Spieloberfläche(this, avatar.getName(), finanzen);
		zeigeSpielGUI();
		finanzen.setGameGUI(gameGUI);

		// Ereignisse erzeugen
		erList = Initialisator.gibEreignisse();
		ArrayList<Ereignis> kinderErList = Initialisator.gibKinderEreignisse();
		if (avatar.getAvatarNummer() > 1) {
			for (Ereignis er : kinderErList) {
				erList.add(er);
			}
		}

		grenzenErzeugen();

		gameGUI.setzeAktiviert(false);
		finanzen.aktualisiereFinanzenGUI();
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

	public int getBewerbungsfaktor() {
		return bewerbungsfaktor;
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
		// Überprüfen ob Spieler verloren hat
		if (istBedürfnisAufMinimum()) {
			String text = "Du hast das Leben leider nicht meistern können. \nDas Spiel ist deshalb für dich Leider vorbei. \n\nImmerhin hast du "
					+ getPunkte() + " Punkte erspielt.";
			spielBeenden(text, "Verloren!");
			return;
		}
		// Überprüfen ob Maximale Rundezahl erreicht ist
		if (aktuelleRunde == Optionen.ANZAHL_RUNDEN) {
			String text = "Du hast es geschafft!\nDu hast " + getPunkte()
					+ " erspielt!";
			spielBeenden(text, "Geschafft!");
			return;
		}
		// gameGUI.setzeAktiviert(true);
		aktuelleRunde++;
		zeit = zeitProRunde;

		// Bei neuem Monat Geld auszahlen
		if ((aktuelleRunde % 4) == 0) {
			kontostand = kontostand + geldProMonat;
		}

		// Bedürfnisse um Abfallfaktor reduzieren
		bedürfnisseFallen();
		gameGUI.aktualisiereDaten();

		// Ereignisse auslösen
		Ereignis er = getRandomEreignis();
		gameGUI.getEreignisfenster().fensterZeigen(er);
	}

	/**
	 * Liefert true wenn mindestens ein Bedürfnis auf dem Minimum ist. Sind alle
	 * höher als das Minimum, liefert die Methode false.
	 * 
	 * @return true wenn mindestens ein Bedürfnis auf Minimum.
	 */
	private boolean istBedürfnisAufMinimum() {
		boolean minimum = false;
		for (int i = 0; i < bedürfnisse.length; i++) {
			if (bedürfnisse[i].istMinimum()) {
				minimum = true;
			}
		}
		return minimum;
	}

	/**
	 * Lässt die Bedürfnisse um den Abfallfaktor fallen.
	 */
	private void bedürfnisseFallen() {
		for (int i = 0; i < bedürfnisse.length; i++) {
			bedürfnisse[i].setWert(bedürfnisse[i].getWert()
					- bedürfnisse[i].getAbfallfaktor());
		}
	}

	/**
	 * Dialog zur Eingabe des Namens wird angezeigt. Anschließend wird das Spiel
	 * beendet und man sieht die Highscoreliste.
	 */
	private void spielBeenden(String text, String title) {
		text += "\n\nUm in dich in die Highscoreliste einzutragen, gib hier deinen Namen ein:";
		String antwort = JOptionPane.showInputDialog(SpielAnwendung.mainGUI,
				text, title, JOptionPane.PLAIN_MESSAGE);
		if (antwort == null || antwort.trim().equals("")) {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName());
		} else {
			SpielAnwendung.beendeSpiel(getAvatarNr(), getAvatarName(),
					new Score(antwort, getPunkte()));
		}
	}

	/**
	 * Liefert ein zufälliges Ereignis aus der erList zurück und löscht dieses
	 * dann.
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

	public void aktionAusfuehren(Aktion aktion) {
		ArrayList<String> probs = getProblemBeduerfnisse(aktion
				.getVeraenderungen());
		if (probs != null && probs.size() != 0) {
			ArrayList<String> probleme = new ArrayList<String>();
			for (String prob : probs) {
				if (prob.equals("Zeit")) {
					probleme.add("Zeit");
				}
				if (prob.equals("Geld")) {
					probleme.add("Geld");
				}
			}
			if (probleme.size() != 0) {
				String gruendeFormatiert = "";
				for (int i = 0; i < probleme.size(); i++) {
					gruendeFormatiert = gruendeFormatiert + probleme.get(i);
					if ((i + 2) == probleme.size())
						gruendeFormatiert = gruendeFormatiert + " und ";
					else if ((i + 1) != probleme.size())
						gruendeFormatiert = gruendeFormatiert + ", ";
				}
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(SpielAnwendung.mainGUI,
						"Diese Aktion kannst du nicht ausführen, da du nicht genug "
								+ gruendeFormatiert + " hast.",
						"Aktion nicht ausführbar", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE, null, options,
						options[0]);
				return;
			} else {
				String gruendeFormatiert = "Wenn du diese Aktion ausführst werden die Bedürfnisse ";
				if (probs.size() < 2)
					gruendeFormatiert = "Wenn du diese Aktion ausführst wird das Bedürfnis ";
				for (int i = 0; i < probs.size(); i++) {
					gruendeFormatiert = gruendeFormatiert + probs.get(i);
					if ((i + 2) == probs.size())
						gruendeFormatiert = gruendeFormatiert + " und ";
					else if ((i + 1) != probs.size())
						gruendeFormatiert = gruendeFormatiert + ", ";
				}

				Object[] options = { "Ja", "Nein" };
				if (JOptionPane
						.showOptionDialog(
								SpielAnwendung.mainGUI,
								gruendeFormatiert
										+ " auf Null sinken.\nWillst du diese Aktion wirklich ausführen?",
								"Warnung", JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options,
								options[0]) == JOptionPane.NO_OPTION)
					return;
			}
		}
		infosUmsetzen(aktion.getVeraenderungen());
		if (aktion.getMinispiel() != null) {
			gameGUI.setzeAktiviert(false);
			minispielStarten(aktion.getMinispiel());
		}
		gameGUI.zeigeNachrichtInKonsole(aktion.getKonsolenausgabe());
		gameGUI.aktualisiereDaten();
	}

	public ArrayList<String> getProblemBeduerfnisse(Information[] infos) {
		if (infos != null && infos.length != 0) {
			ArrayList<String> rueckgabe = new ArrayList<String>();
			for (int i = 0; i < infos.length; i++) {
				if (!(bedürfnisse.length < 5 && (infos[i].getZuÄndern() == Information.AENDERN_KINDER || infos[i]
						.getZuÄndern() == Information.AENDERN_KINDER_FAKTOR))) {
					// Bedürfnisbereich
					if (grenzenObenUndUnten.contains(infos[i].getZuÄndern())) {
						int neuerWert = 0;
						Bedürfnis referenz = null;
						if (braucheWert.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 1) / 2;
							neuerWert = bedürfnisse[index].getWert();
							referenz = bedürfnisse[index];
						}
						if (braucheFaktor.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 2) / 2;
							neuerWert = bedürfnisse[index].getAbfallfaktor();
							referenz = bedürfnisse[index];
						}

						switch (infos[i].getÄnderungsart()) {
						case 1: {
							if (infos[i].getWert() >= referenz.getMin()
									&& infos[i].getWert() <= referenz.getMax())
								neuerWert = infos[i].getWert();
							else {
								if (infos[i].getWert() < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 2: {
							int tmp = neuerWert + infos[i].getWert();
							if (tmp >= referenz.getMin()
									&& tmp <= referenz.getMax())
								neuerWert = tmp;
							else {
								if (tmp < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 3: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							if (tmp2 >= referenz.getMin()
									&& tmp2 <= referenz.getMax())
								neuerWert = tmp2;
							else {
								if (tmp2 < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 4: {
							double tmp = neuerWert * infos[i].getWert();
							int tmp2 = (int) tmp; // so grob
							tmp2 = neuerWert + tmp2;
							if (tmp2 >= referenz.getMin()
									&& tmp2 <= referenz.getMax())
								neuerWert = tmp2;
							else {
								if (tmp2 < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						}
						if (braucheWert.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 1) / 2;
							if (bedürfnisse[index].getMin() >= neuerWert)
								rueckgabe.add(bedürfnisse[index].getName());
						}
						if (braucheFaktor.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 2) / 2;
							if (bedürfnisse[index].getMin() >= neuerWert)
								rueckgabe.add(bedürfnisse[index].getName());
						}
					}
					if (grenzeUnten.contains(infos[i].getZuÄndern())) {
						int neuerWert = zeit;

						switch (infos[i].getÄnderungsart()) {
						case 1: {
							if (infos[i].getWert() > 0)
								neuerWert = infos[i].getWert();
							else {
								neuerWert = 0;
							}
							break;
						}
						case 2: {
							int tmp = neuerWert + infos[i].getWert();
							neuerWert = tmp;
							break;
						}
						case 3: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							if (tmp2 > 0)
								neuerWert = tmp2;
							else {
								neuerWert = 0;
							}
							break;
						}
						case 4: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							tmp2 = neuerWert + tmp2;
							if (tmp2 > 0)
								neuerWert = tmp2;
							else {
								neuerWert = 0;
							}
							break;
						}
						}
						if (neuerWert < 0)
							rueckgabe.add("Zeit");
					}
					if (keineGrenze.contains(infos[i].getZuÄndern())) {
						int neuerWert = 0;
						switch (infos[i].getZuÄndern()) {
						case 12:
							neuerWert = zeitProRunde;
							break;
						case 13:
							neuerWert = kontostand;
							break;
						case 14:
							neuerWert = geldProMonat;
							break;
						}
						switch (infos[i].getÄnderungsart()) {
						case 1:
							neuerWert = infos[i].getWert();
							break;
						case 2:
							neuerWert = infos[i].getWert() + neuerWert;
							break;
						case 3:
							neuerWert = (int) (neuerWert * infos[i].getWert() / 100);
							break;
						case 4:
							neuerWert = neuerWert
									+ (int) (neuerWert * infos[i].getWert() / 100);
							break;
						}
						switch (infos[i].getZuÄndern()) {
						case 12:
							if (neuerWert < 0)
								rueckgabe.add("Zeit pro Runde");
							break;
						case 13:
							if (neuerWert < 0)
								rueckgabe.add("Geld");
							break;
						case 14:
							if (neuerWert < 0)
								rueckgabe.add("Einkommen");
							break;
						}
					}
				}
			}
			return rueckgabe;
		}
		return null;
	}

	/**
	 * Wird direkt vor beenden eines Minispiels aufgerufen um dessen
	 * Informationen zu verarbeiten und das Hauptspiel wieder freizugeben.
	 * 
	 * @param infos
	 *            Informations-Array, das alle Informationen beinhaltet, die
	 *            umgesetzt werden sollen.
	 * @param konsolennachricht
	 *            Nachricht die in der Konsole des Spiels angezeigt werden soll.
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
	 * Wird direkt vor beenden eines Minispiels aufgerufen um dessen
	 * Informationen zu verarbeiten und das Hauptspiel wieder freizugeben.
	 * 
	 * @param infos
	 *            Informations-Array, das alle Informationen beinhaltet, die
	 *            umgesetzt werden sollen.
	 */
	public void minispielEnde(Information[] infos) {
		minispielEnde(infos, null);
	}

	/**
	 * Setzt alle Informationen des übergebenen Informations-Arrays um.
	 * 
	 * @param infos
	 *            Infos die umgesetzt werden sollen.
	 */
	public void infosUmsetzen(Information[] infos) {
		if (infos == null) {
		} else {
			for (int i = 0; i < infos.length; i++) {
				if (!(bedürfnisse.length < 5 && (infos[i].getZuÄndern() == Information.AENDERN_KINDER || infos[i]
						.getZuÄndern() == Information.AENDERN_KINDER_FAKTOR))) {
					// Bedürfnisbereich
					if (grenzenObenUndUnten.contains(infos[i].getZuÄndern())) {
						int neuerWert = 0;
						Bedürfnis referenz = null;
						if (braucheWert.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 1) / 2;
							neuerWert = bedürfnisse[index].getWert();
							referenz = bedürfnisse[index];
						}
						if (braucheFaktor.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 2) / 2;
							neuerWert = bedürfnisse[index].getAbfallfaktor();
							referenz = bedürfnisse[index];
						}

						switch (infos[i].getÄnderungsart()) {
						case 1: {
							if (infos[i].getWert() >= referenz.getMin()
									&& infos[i].getWert() <= referenz.getMax())
								neuerWert = infos[i].getWert();
							else {
								if (infos[i].getWert() < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 2: {
							int tmp = neuerWert + infos[i].getWert();
							if (tmp >= referenz.getMin()
									&& tmp <= referenz.getMax())
								neuerWert = tmp;
							else {
								if (tmp < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 3: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							if (tmp2 >= referenz.getMin()
									&& tmp2 <= referenz.getMax())
								neuerWert = tmp2;
							else {
								if (tmp2 < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						case 4: {
							double tmp = neuerWert * infos[i].getWert();
							int tmp2 = (int) tmp; // so grob
							tmp2 = neuerWert + tmp2;
							if (tmp2 >= referenz.getMin()
									&& tmp2 <= referenz.getMax())
								neuerWert = tmp2;
							else {
								if (tmp2 < referenz.getMin())
									neuerWert = referenz.getMin();
								else
									neuerWert = referenz.getMax();
							}
							break;
						}
						}

						if (braucheWert.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 1) / 2;
							bedürfnisse[index].setWert(neuerWert);
						}
						if (braucheFaktor.contains(infos[i].getZuÄndern())) {
							int index = (infos[i].getZuÄndern() - 2) / 2;
							bedürfnisse[index].setAbfallfaktor(neuerWert);
						}
					}
					if (grenzeUnten.contains(infos[i].getZuÄndern())) {
						int neuerWert = 0;
						if (infos[i].getZuÄndern() == Information.AENDERN_ZEIT)
							neuerWert = zeit;
						if (infos[i].getZuÄndern() == Information.AENDERN_BEWERBUNGSFAKTOR)
							neuerWert = bewerbungsfaktor;

						switch (infos[i].getÄnderungsart()) {
						case 1: {
							if (infos[i].getWert() > 0)
								neuerWert = infos[i].getWert();
							else {
								neuerWert = 0;
							}
							break;
						}
						case 2: {
							int tmp = neuerWert + infos[i].getWert();
							if (tmp > 0)
								neuerWert = tmp;
							else {
								neuerWert = 0;
							}
							break;
						}
						case 3: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							if (tmp2 > 0)
								neuerWert = tmp2;
							else {
								neuerWert = 0;
							}
							break;
						}
						case 4: {
							double tmp = neuerWert * infos[i].getWert() / 100;
							int tmp2 = (int) tmp; // so grob
							tmp2 = neuerWert + tmp2;
							if (tmp2 > 0)
								neuerWert = tmp2;
							else {
								neuerWert = 0;
							}
							break;
						}
						}

						if (infos[i].getZuÄndern() == Information.AENDERN_ZEIT)
							zeit = neuerWert;
						if (infos[i].getZuÄndern() == Information.AENDERN_BEWERBUNGSFAKTOR)
							bewerbungsfaktor = neuerWert;
					}
					if (keineGrenze.contains(infos[i].getZuÄndern())) {
						int neuerWert = 0;
						int vorher = kontostand;
						switch (infos[i].getZuÄndern()) {
						case 12:
							neuerWert = zeitProRunde;
							break;
						case 13:
							neuerWert = kontostand;
							break;
						case 14:
							neuerWert = geldProMonat;
							break;
						}
						switch (infos[i].getÄnderungsart()) {
						case 1:
							neuerWert = infos[i].getWert();
							break;
						case 2:
							neuerWert = infos[i].getWert() + neuerWert;
							break;
						case 3:
							neuerWert = (int) (neuerWert * infos[i].getWert() / 100);
							break;
						case 4:
							neuerWert = neuerWert
									+ (int) (neuerWert * infos[i].getWert() / 100);
							break;
						}
						switch (infos[i].getZuÄndern()) {
						case 12:
							zeitProRunde = neuerWert;
							break;
						case 13:
							kontostand = neuerWert;
							break;
						case 14:
							geldProMonat = neuerWert;
							break;
						}
						int nachher = kontostand;
						finanzen.rundenAusgabenÄndern(nachher-vorher);
					}
				}
			}
		}
	}

	/**
	 * Addiert in jeder Runde die neu erhaltenen Punkte auf die bereits
	 * vorhandenen.
	 * 
	 * @param gui
	 *            PunkteGUI in dem die Daten angezeigt werden sollen.
	 */
	public void punkteBerechnen(PunkteGUI gui) {

		int hungerP = bedürfnisse[0].getWert();
		int gesundheitP = bedürfnisse[1].getWert();
		int sozialesP = bedürfnisse[2].getWert();
		int luxusP = bedürfnisse[3].getWert();
		int kinderP = 0;
		if (avatarNr != 1) {
			kinderP = bedürfnisse[4].getWert();
		}
		int geldP = (int) (kontostand / 15);

		int punkteAlt = punkte;
		punkte = punkte + hungerP + gesundheitP + sozialesP + luxusP + geldP;
		if (avatarNr != 0) {
			punkte = punkte + kinderP;
		}

		gui.setzeWerte(hungerP, gesundheitP, sozialesP, luxusP, kinderP, geldP,
				punkteAlt);
	}

	public void minispielStarten(Minispiel minispiel) {
		minispiel.start(this);
	}

	public void zeigeFinanzen() {
		if ((aktuelleRunde + 1) % 4 == 0)
			finanzen.aktualisiereFinanzenGUI();
		else
			naechsteRunde();
	}

	public void grenzenErzeugen() {
		grenzenObenUndUnten = new ArrayList<Integer>();
		grenzenObenUndUnten.add(Information.AENDERN_NAHRUNG);
		grenzenObenUndUnten.add(Information.AENDERN_NAHRUNGS_FAKTOR);
		grenzenObenUndUnten.add(Information.AENDERN_GESUNDHEIT);
		grenzenObenUndUnten.add(Information.AENDERN_GESUNDHEITS_FAKTOR);
		grenzenObenUndUnten.add(Information.AENDERN_SOZIALES);
		grenzenObenUndUnten.add(Information.AENDERN_SOZIALES_FAKTOR);
		grenzenObenUndUnten.add(Information.AENDERN_LUXUS);
		grenzenObenUndUnten.add(Information.AENDERN_LUXUS_FAKTOR);
		grenzenObenUndUnten.add(Information.AENDERN_KINDER);
		grenzenObenUndUnten.add(Information.AENDERN_KINDER_FAKTOR);

		grenzeUnten = new ArrayList<Integer>();
		grenzeUnten.add(Information.AENDERN_ZEIT);
		grenzeUnten.add(Information.AENDERN_BEWERBUNGSFAKTOR);

		keineGrenze = new ArrayList<Integer>();
		keineGrenze.add(Information.AENDERN_ZEIT_PRO_RUNDE);
		keineGrenze.add(Information.AENDERN_GELD);
		keineGrenze.add(Information.AENDERN_GELD_PRO_MONAT);

		braucheWert = new ArrayList<Integer>();
		braucheWert.add(Information.AENDERN_NAHRUNG);
		braucheWert.add(Information.AENDERN_GESUNDHEIT);
		braucheWert.add(Information.AENDERN_SOZIALES);
		braucheWert.add(Information.AENDERN_LUXUS);
		braucheWert.add(Information.AENDERN_KINDER);

		braucheFaktor = new ArrayList<Integer>();
		braucheFaktor.add(Information.AENDERN_NAHRUNGS_FAKTOR);
		braucheFaktor.add(Information.AENDERN_GESUNDHEITS_FAKTOR);
		braucheFaktor.add(Information.AENDERN_SOZIALES_FAKTOR);
		braucheFaktor.add(Information.AENDERN_LUXUS_FAKTOR);
		braucheFaktor.add(Information.AENDERN_KINDER_FAKTOR);
	}
}
