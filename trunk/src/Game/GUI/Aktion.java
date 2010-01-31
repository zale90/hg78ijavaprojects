package Game.GUI;

import javax.swing.*;
import Game.*;
import Game.Minigames.*;

public class Aktion extends JLabel {
	private static final long serialVersionUID = 1L;
	private Information[] kosten, nutzen;
	private String name, beschreibung, konsolenausgabe;
	private Minispiel minispiel;

	public Aktion(String name, String beschreibung, String konsolenausgabe,
			Information[] kost, Information[] nutz) {
		super(name);
		this.kosten = kost;
		this.nutzen = nutz;
		this.name = name;
		this.beschreibung = beschreibung;
		this.konsolenausgabe = konsolenausgabe;

		setSize(120, 30);
		setOpaque(true);
		setBorder(BorderFactory.createEtchedBorder());
		setHorizontalAlignment(0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setKonsolenausgabe(String konsolenausgabe) {
		this.konsolenausgabe = konsolenausgabe;
	}

	public String getKonsolenausgabe() {
		return konsolenausgabe;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getBeschreibung() {
		String[] beduerfnisse = getBedürfnisveränderungen(getVeraenderungen());
		if (beduerfnisse == null || beduerfnisse.length == 0)
			return beschreibung;
		String beduerfnistext = "\n\n";
		for (int i = 0; i < beduerfnisse.length; i++) {
			beduerfnistext = beduerfnistext + beduerfnisse[i];
		}
		return beschreibung + beduerfnistext;
	}

	public Information[] getKosten() {
		return kosten;
	}

	public Information[] getNutzen() {
		return nutzen;
	}

	public Information[] getVeraenderungen() {
		if (kosten == null) {
			return nutzen;
		} else {
			if (nutzen == null) {
				return kosten;
			} else {
				Information[] veraenderung = new Information[kosten.length
						+ nutzen.length];
				for (int i = 0; i < kosten.length; i++) {
					veraenderung[i] = kosten[i];
				}
				for (int i = 0; i < nutzen.length; i++) {
					veraenderung[i + kosten.length] = nutzen[i];
				}
				return veraenderung;
			}
		}
	}

	public void setMinispiel(Minispiel minispiel) {
		this.minispiel = minispiel;
	}

	public Minispiel getMinispiel() {
		return minispiel;
	}

	public String[] getBedürfnisveränderungen(Information[] infos) {
		if (infos != null && infos.length != 0) {
			String[] rueckgabe = new String[infos.length];
			for (int i = 0; i < infos.length; i++) {
				// Bedürfnisbereich
				String datenfeld = "";
				String operator = "";
				String farbe = "(red)";
				String einheit = "\n";
				int wert = infos[i].getWert();
				if (wert >= 0)
					farbe = "(green)";
				if ((infos[i].getÄnderungsart() % 2) == 0)// infos[i].getÄnderungsart()
															// == 2 ||
															// infos[i].getÄnderungsart()
															// == 4)
				{
					operator = "+";
				}
				if (infos[i].getÄnderungsart() > 2) {
					einheit = "%";
				}
				if ((infos[i].getZuÄndern() % 2) == 0) {
					wert = -wert;
				}
				if (wert < 0) {
					operator = "";
				}

				switch (infos[i].getZuÄndern()) {
				case 1:
					datenfeld = "Nahrung: ";
					break;
				case 2:
					datenfeld = "Nahrungsverbrauch: ";
					break;
				case 3: {
					datenfeld = "Gesundheit: ";
					break;
				}
				case 4: {
					datenfeld = "Gesundheitsabfall: ";
					break;
				}
				case 5: {
					datenfeld = "Soziales: ";
					break;
				}
				case 6: {
					datenfeld = "Soziale Verarmung: ";
					break;
				}
				case 7: {
					datenfeld = "Luxus: ";
					break;
				}
				case 8: {
					datenfeld = "Luxusabfall: ";
					break;
				}
				case 9: {
					datenfeld = "Kinder: ";
					break;
				}
				case 10: {
					datenfeld = "Kinderabfall: ";
					break;
				}
				case 11:
					datenfeld = "Zeit: ";
					break;
				case 12:
					datenfeld = "Zeit pro Woche: ";
					break;
				case 13:
					datenfeld = "Geld: ";
					if (wert < 0)
						wert = wert * (Verzweigung.getSpielbereich().getSpieloberfläche().getSpiel().getFamilienmitglieder());
					break;
				case 14:
					datenfeld = "Geld pro Monat: ";
					break;
				case 15:
					datenfeld = "Chance auf Job: ";
					break;
				}
				if (Verzweigung.getSpielbereich().getSpieloberfläche()
						.getSpiel().getBedürfnisse().length > 4
						|| (infos[i].getZuÄndern() != 9 && infos[i]
								.getZuÄndern() != 10))
					rueckgabe[i] = farbe + datenfeld + operator + wert
							+ einheit;
				else
					rueckgabe[i] = "";
			}

			return rueckgabe;
		}
		return null;
	}

}
