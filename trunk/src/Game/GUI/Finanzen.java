package Game.GUI;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.*;

public class Finanzen extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4104080309063213464L;
	private ArrayList<GeldBetrag> einnahmen;
	private ArrayList<GeldBetrag> ausgaben;
	private GeldBetrag rundenAusgaben;
	private int monat, zeilen;
	private Spiel spiel;
	private Spieloberfläche gameGUI;
	private JButton ok;
	private Color gruen, rot;
	
	public Finanzen(Spiel spiel)
	{
		this.spiel = spiel;
		einnahmen = new ArrayList<GeldBetrag>();
		ausgaben = new ArrayList<GeldBetrag>();
		rundenAusgaben = new GeldBetrag("Ausgaben", 0);
		monat = 1;
		
		gruen = new Color(65, 115, 50);
		rot = Color.RED;
		
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
	
	public void einnahmeHinzufügen(ArrayList<GeldBetrag> einnahmen)
	{
		for(GeldBetrag e: einnahmen)
		{
			this.einnahmen.add(e);
		}
	}
	
	public void ausgabeHinzufügen(String name, int betrag)
	{
		ausgaben.add(new GeldBetrag(name, betrag));
	}
	
	public void ausgabeHinzufügen(ArrayList<GeldBetrag> ausgaben)
	{
		for(GeldBetrag a: ausgaben)
		{
			this.ausgaben.add(a);
		}
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
		
		int verschubR = 0;
		int verschubZ = zeilen * 15;
		if(monat != 1)
			 verschubR = 20;
		int verschubG = verschubZ + verschubR;
		this.setSize(350, (155 + verschubG));
		this.setLocation((int)(20 + (800 - this.getWidth()) / 2), (int)(60 + (500 - this.getHeight()) / 2));
		
		JLabel ueberschrift = new JLabel("Monat " + monat);
		ueberschrift.setSize(340, 30);
		ueberschrift.setLocation(5, 0);
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(ueberschrift);
		
		JLabel lErwirtschaftet = new JLabel("Bilanz letzter Monat: " );
		lErwirtschaftet.setSize(340, 20);
		lErwirtschaftet.setLocation(5, 30);
		if(monat == 1)
			lErwirtschaftet.setVisible(false);
		this.add(lErwirtschaftet);
		
		JLabel iErwirtschaftet = new JLabel(rundenAusgaben.getBetrag() + " €");
		iErwirtschaftet.setSize(70,20);
		iErwirtschaftet.setLocation(130, 30);
		if(rundenAusgaben.getBetrag() > 0)
			iErwirtschaftet.setForeground(gruen);
		if(rundenAusgaben.getBetrag() < 0)
			iErwirtschaftet.setForeground(rot);
		if(monat == 1)
			iErwirtschaftet.setVisible(false);
		this.add(iErwirtschaftet);
		
		JLabel lEinnahmen = new JLabel("Einnahmen");
		lEinnahmen.setSize(140, 20);
		lEinnahmen.setLocation(15,(30 + verschubR));
		lEinnahmen.setFont(new Font("Arial", Font.PLAIN, 16));
		lEinnahmen.setForeground(gruen);
		this.add(lEinnahmen);
		
		int zeile = 0;
		int gesamt = 0;
		for(GeldBetrag e : einnahmen)
		{
			JLabel einnahmeName = new JLabel(e.getName());
			einnahmeName.setSize(120,15);
			einnahmeName.setLocation(15, (55 + verschubR + zeile));
			this.add(einnahmeName);
			
			JLabel einnahmeWert = new JLabel(Integer.toString(e.getBetrag()) + " €");
			einnahmeWert.setSize(40, 15);
			einnahmeWert.setLocation(115, (55 + verschubR + zeile));
			einnahmeWert.setHorizontalAlignment(JLabel.RIGHT);
			this.add(einnahmeWert);
			
			gesamt = gesamt + e.getBetrag();
			zeile = zeile + 15;
		}
		
		JLabel gEinnahmen = new JLabel("Gesamt");
		gEinnahmen.setSize(140,20);
		gEinnahmen.setLocation(15, (60 + verschubG));
		gEinnahmen.setFont(new Font("Arial", Font.PLAIN, 16));
		this.add(gEinnahmen);
		
		JLabel gEinnahmen2 = new JLabel(Integer.toString(gesamt) + " €");
		gEinnahmen2.setSize(140,20);
		gEinnahmen2.setLocation(15, (60 + verschubG));
		gEinnahmen2.setFont(new Font("Arial", Font.PLAIN, 16));
		gEinnahmen2.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gEinnahmen2);
		
		JLabel lAusgaben = new JLabel("Ausgaben");
		lAusgaben.setSize(140, 20);
		lAusgaben.setLocation(195,(30 + verschubR));
		lAusgaben.setFont(new Font("Arial", Font.PLAIN, 16));
		lAusgaben.setForeground(rot);
		this.add(lAusgaben);
		
		zeile = 0;
		gesamt = 0;
		for(GeldBetrag a : ausgaben)
		{
			JLabel ausgabeName = new JLabel(a.getName());
			ausgabeName.setSize(120,15);
			ausgabeName.setLocation(195, (55 + verschubR + zeile));
			this.add(ausgabeName);
			
			JLabel ausgabeWert = new JLabel(Integer.toString(a.getBetrag()) + " €");
			ausgabeWert.setSize(40, 15);
			ausgabeWert.setLocation(295, (55 + verschubR + zeile));
			ausgabeWert.setHorizontalAlignment(JLabel.RIGHT);
			this.add(ausgabeWert);
			
			gesamt = gesamt + a.getBetrag();
			zeile = zeile + 15;
		}
		
		JLabel gAusgaben = new JLabel("Gesamt");
		gAusgaben.setSize(140,20);
		gAusgaben.setLocation(195, (60 + verschubG));
		gAusgaben.setFont(new Font("Arial", Font.PLAIN, 16));
		this.add(gAusgaben);
		
		JLabel gAusgaben2 = new JLabel(Integer.toString(gesamt) + " €");
		gAusgaben2.setSize(140,20);
		gAusgaben2.setLocation(195, (60 + verschubG));
		gAusgaben2.setFont(new Font("Arial", Font.PLAIN, 16));
		gAusgaben2.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gAusgaben2);
		
		JLabel lErhalten = new JLabel("Du erhälst:");
		lErhalten.setSize(340, 20);
		lErhalten.setLocation(5,(95 + verschubG));
		lErhalten.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(lErhalten);
		
		JLabel wErhalten = new JLabel(Integer.toString(berechneEinkommen()) + " €");
		wErhalten.setSize(100,20);
		wErhalten.setLocation(100, (95 + verschubG));
		wErhalten.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(wErhalten);
		
		ok = new JButton("Weiter");
		ok.setSize(90, 30);
		ok.setLocation(130, 120 + verschubG);
		ok.setFont(new Font("Arial", Font.BOLD, 16));
		ok.setForeground(new Color(0, 0, 0));
		ok.setHorizontalAlignment(JLabel.CENTER);
		ok.addActionListener(this);
		this.add(ok);
		
		this.setVisible(true);
		ok.requestFocusInWindow();
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
				rundenAusgaben = new GeldBetrag("Ausgaben", 0);
				spiel.zahleGehalt();
				spiel.datenAktualisieren();
			}
			else
			{
				this.setVisible(false);
				this.removeAll();
				monat++;
				rundenAusgaben = new GeldBetrag("Ausgaben", 0);
				spiel.naechsteRunde();
			}
	}
}
