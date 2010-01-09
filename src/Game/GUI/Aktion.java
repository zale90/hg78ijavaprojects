package Game.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Game.*;

public class Aktion extends JButton {
	private static final long serialVersionUID = 1L;
	private Information[] nutzen, kosten; // veraenderungen aufgeteilt in kosten und nutzen, um diese eventuell irgendwo anzuzeigen oder so
	private String name, beschreibung, konsolenausgabe;
	
	public Aktion(String name, String beschreibung, String konsolenausgabe, Information[] nutz, Information[] kost)
	{
		super(name);
		nutzen = nutz;
		kosten = kost;
		this.name = name;
		this.beschreibung = beschreibung;
		this.konsolenausgabe = konsolenausgabe;
		
		setSize(80, 20);
		setBackground(Color.green);
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

	public void setNutzen(Information[] nutzen) {
		this.nutzen = nutzen;
	}

	public Information[] getNutzen() {
		return nutzen;
	}

	public void setKosten(Information[] kosten) {
		this.kosten = kosten;
	}

	public Information[] getKosten() {
		return kosten;
	}
	public Information[] getVeraenderungen() // für spiel, wenn die Veränderungen umgesetzt werden. Da muss nicht mehr getrennt werden.
	{
		if (kosten == null)
		{
			if (nutzen == null)
				return null;
			else
			{
				return nutzen;
			}
		}
		if (nutzen == null)
		{
			return kosten;
		}
		Information[] veraenderungen = new Information[kosten.length+nutzen.length];
		for (int i = 0; i < kosten.length; i++)
		{
			veraenderungen[i] = kosten[i];
		}
		for (int i = 0; i < nutzen.length; i++)
		{
			veraenderungen[i] = nutzen[i];
		}
		return veraenderungen;
	}
	
}
