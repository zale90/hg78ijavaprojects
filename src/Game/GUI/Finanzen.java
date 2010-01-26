package Game.GUI;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.*;

public class Finanzen extends JPanel implements ActionListener
{
	private ArrayList<GeldBetrag> einnahmen;
	private ArrayList<GeldBetrag> ausgaben;
	private GeldBetrag rundenAusgaben;
	private int monat, zeilen;
	private Spiel spiel;
	private Spieloberfläche gameGUI;
	private JButton ok;
	
	public Finanzen(Spiel spiel)
	{
		this.spiel = spiel;
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
	
	public void setGameGUI(Spieloberfläche gameGUI)
	{
		this.gameGUI = gameGUI;
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
		
		JLabel ueberschrift = new JLabel("Monat " + monat + ":");
		ueberschrift.setSize(340, 30);
		ueberschrift.setLocation(5, 0);
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(ueberschrift);
		
		ok = new JButton("Weiter");
		ok.setSize(90, 30);
		ok.setLocation(55, 150);
		ok.setFont(new Font("Arial", Font.BOLD, 16));
		ok.setForeground(new Color(0, 0, 0));
		ok.setHorizontalAlignment(JLabel.CENTER);
		ok.addActionListener(this);
		this.add(ok);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok)
			if(monat == 1)
			{
				this.setVisible(false);
				this.removeAll();
				gameGUI.setzeAktiviert(true);
				monat++;
			}
			else
			{
				this.setVisible(false);
				this.removeAll();
				monat++;
				spiel.naechsteRunde();
			}
	}
}
