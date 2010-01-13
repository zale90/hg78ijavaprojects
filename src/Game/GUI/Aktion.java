package Game.GUI;

import javax.swing.*;
import Game.*;
import Game.GUI.Minigames.*;

public class Aktion extends JLabel {
	private static final long serialVersionUID = 1L;
	private Information[] veraenderungen;
	private String name, beschreibung, konsolenausgabe;
	private Minispiel minispiel;
	
	public Aktion(String name, String beschreibung, String konsolenausgabe, Information[] ver)
	{
		super(name);
		setVeraenderungen(ver);
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
		return beschreibung;
	}
	public Information[] getVeraenderungen()
	{
		return veraenderungen;
	}

	public void setVeraenderungen(Information[] veraenderungen) {
		this.veraenderungen = veraenderungen;
	}

	public void setMinispiel(Minispiel minispiel) {
		this.minispiel = minispiel;
	}

	public Minispiel getMinispiel() {
		return minispiel;
	}
	
}
