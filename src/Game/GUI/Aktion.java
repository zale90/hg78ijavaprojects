package Game.GUI;

import javax.swing.*;
import Game.*;
import Game.GUI.Minigames.*;

public class Aktion extends JLabel {
	private static final long serialVersionUID = 1L;
	private Information[] kosten, nutzen;
	private String name, beschreibung, konsolenausgabe;
	private Minispiel minispiel;
	
	public Aktion(String name, String beschreibung, String konsolenausgabe, Information[] kost, Information[] nutz)
	{
		super(name);
		this.kosten = kost;
		this.nutzen= nutz;
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
	public Information[] getKosten()
	{
		return kosten;
	}
	public Information[] getNutzen()
	{
		return nutzen;
	}
	public Information[] getVeraenderungen()
	{
		if (kosten == null)
		{
			return nutzen;
		}
		else
		{
			if (nutzen == null)
			{
				return kosten;
			}
			else
			{
				Information[] veraenderung = new Information[kosten.length + nutzen.length];
				for (int i = 0; i < kosten.length; i++)
				{
					veraenderung[i] = kosten[i];
				}
				for (int i = 0; i < nutzen.length; i++)
				{
					veraenderung[i+kosten.length] = nutzen[i];
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
	
}
