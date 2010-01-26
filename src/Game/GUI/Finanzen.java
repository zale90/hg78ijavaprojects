package Game.GUI;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.color.*;
import Game.*;

public class Finanzen extends JPanel
{
	private ArrayList<GeldBetrag> einnahmen;
	private ArrayList<GeldBetrag> ausgaben;
	private GeldBetrag rundenAusgaben;
	private int monat, zeilen;
	
	public Finanzen()
	{
		einnahmen = new ArrayList<GeldBetrag>();
		ausgaben = new ArrayList<GeldBetrag>();
		rundenAusgaben = new GeldBetrag("Ihre Ausgaben", 0);
		monat = 1;
		
		zeilenBerechnen();
		this.setSize(350, (200 + zeilen * 15));
		this.setLocation((int)(20 + (800 - this.getWidth()) / 2), (int)(60 + (500 - this.getHeight()) / 2));
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setVisible(false);
	}
	
	public void einnahmeHinzufügen(String name, int betrag)
	{
		einnahmen.add(new GeldBetrag(name, betrag));
	}
	
	public void ausgabeHinzufügen(String name, int betrag)
	{
		ausgaben.add(new GeldBetrag(name, betrag));
	}
	
	public void einnahmeEntfernen(String name)
	{
		for(GeldBetrag e : einnahmen)
		{
			if(e.getName().equals(name))
			{
				einnahmen.remove(e);
			}
		}
	}
	
	public void ausgabeEntfernen(String name)
	{
		for(GeldBetrag a : ausgaben)
		{
			if(a.getName().equals(name))
			{
				ausgaben.remove(a);
			}
		}
	}

	public int berechneEinkommen()
	{
		int einkommen = 0;
		
		for(GeldBetrag e : einnahmen)
		{
			einkommen = einkommen + e.getBetrag();
		}
		for(GeldBetrag a : ausgaben)
		{
			einkommen = einkommen - a.getBetrag();
		}
		
		return einkommen;
	}
	
	public void rundenAusgabenÄndern(int betrag)
	{
		rundenAusgaben = new GeldBetrag("Ihre Ausgaben",(rundenAusgaben.getBetrag() + betrag));
	}
	
	public void zeilenBerechnen()
	{
		int anzEinkommen = 0;
		for(@SuppressWarnings("unused") GeldBetrag e : einnahmen)
		{
			anzEinkommen++;
		}
		int anzAusgaben = 0;
		for(@SuppressWarnings("unused") GeldBetrag a : ausgaben)
		{
			anzAusgaben++;
		}
		zeilen = anzEinkommen;
		if(anzAusgaben > anzEinkommen)
		{
			zeilen = anzAusgaben;
		}
	}
	
	public void aktualisiereFinanzenGUI()
	{
		zeilenBerechnen();
		this.setSize(350, (200 + zeilen * 15));
		this.setLocation((int)(20 + (800 - this.getWidth()) / 2), (int)(60 + (500 - this.getHeight()) / 2));
		
		this.setVisible(true);
	}
}
