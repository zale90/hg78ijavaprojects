package Game.GUI;

import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Game.*;

public class PunkteGUI extends JWindow implements ActionListener
{
	private static final long serialVersionUID = 1626731797661003093L;
	JButton ok;
	Spiel spiel;

	public PunkteGUI(int hunger, int gesundheit, int soziales, int luxus, int geld, int alt, int gesamt, Spiel spiel)
	{
		this.spiel = spiel;
		
		this.setSize(200,350);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		JLabel ueberschrift = new JLabel("Punkteabrechnung");
		ueberschrift.setSize(195,30);
		ueberschrift.setLocation(5,0);
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(ueberschrift);
		
		JTextPane einleitung = new JTextPane();
		einleitung.setText("In der letzten Runde haben Sie folgende Punkte erspielt:");
		einleitung.setSize(195, 50);
		einleitung.setBackground(null);
		einleitung.setEditable(false);
		einleitung.setLocation(3, 30);
		this.add(einleitung);
		
		JLabel bedürfnis = new JLabel("Bedürfnisse");
		bedürfnis.setSize(195,20);
		bedürfnis.setLocation(5,145);
		bedürfnis.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(bedürfnis);
		
		JLabel[] bedürfnisse = new JLabel[4];
		int ort = 85;
		for(int i = 0; i < 4; i++)
		{
			bedürfnisse[i] = new JLabel();
			bedürfnisse[i].setSize(95,15);
			bedürfnisse[i].setLocation(5, ort);
			this.add(bedürfnisse[i]);
			ort = ort + 15;
		}
		bedürfnisse[0].setText("Hunger");
		bedürfnisse[1].setText("Gesundheit");
		bedürfnisse[2].setText("Soziales");
		bedürfnisse[3].setText("Luxus");
		
		JLabel[] bPunkte = new JLabel[4];
		ort = 85;
		for(int i = 0; i < 4; i++)
		{
			bPunkte[i] = new JLabel();
			bPunkte[i].setSize(95,15);
			bPunkte[i].setLocation(100, ort);
			bPunkte[i].setHorizontalAlignment(JLabel.RIGHT);
			this.add(bPunkte[i]);
			ort = ort + 15;
		}
		bPunkte[0].setText(Integer.toString(hunger));
		bPunkte[1].setText(Integer.toString(gesundheit));
		bPunkte[2].setText(Integer.toString(soziales));
		bPunkte[3].setText(Integer.toString(luxus));
		
		JLabel bSumme = new JLabel(Integer.toString(hunger + gesundheit + soziales + luxus));
		bSumme.setSize(95,20);
		bSumme.setLocation(100,145);
		bSumme.setHorizontalAlignment(JLabel.RIGHT);
		bSumme.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(bSumme);
		
		JLabel lGeld = new JLabel("Geld");
		lGeld.setSize(95,20);
		lGeld.setLocation(5,170);
		lGeld.setFont(new Font("Arial", Font.BOLD, 14));
		this.add(lGeld);
		
		JLabel gPunkt = new JLabel(Integer.toString(geld));
		gPunkt.setSize(95,20);
		gPunkt.setLocation(100,170);
		gPunkt.setFont(new Font("Arial", Font.BOLD, 14));
		gPunkt.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gPunkt);
		
		JLabel lGesamt = new JLabel("Gesamt");
		lGesamt.setSize(95,20);
		lGesamt.setLocation(5,200);
		lGesamt.setFont(new Font("Arial", Font.BOLD, 16));
		this.add(lGesamt);
		
		JLabel gesPunkt = new JLabel(Integer.toString(hunger + gesundheit + soziales + luxus + geld));
		gesPunkt.setSize(95,20);
		gesPunkt.setLocation(100,200);
		gesPunkt.setFont(new Font("Arial", Font.BOLD, 16));
		gesPunkt.setHorizontalAlignment(JLabel.RIGHT);
		this.add(gesPunkt);
		
		JLabel lVorher = new JLabel("Punkte vorher");
		lVorher.setSize(95,20);
		lVorher.setLocation(5, 220);
		lVorher.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(lVorher);
		
		JLabel pVorher = new JLabel(Integer.toString(alt));
		pVorher.setSize(95,20);
		pVorher.setLocation(100,220);
		pVorher.setFont(new Font("Arial", Font.PLAIN, 14));
		pVorher.setHorizontalAlignment(JLabel.RIGHT);
		this.add(pVorher);
		
		JLabel lPunktestand = new JLabel("Punktestand");
		lPunktestand.setSize(190,30);
		lPunktestand.setLocation(5, 260);
		lPunktestand.setFont(new Font("Arial", Font.BOLD, 16));
		lPunktestand.setHorizontalAlignment(JLabel.CENTER);
		this.add(lPunktestand);
		
		JLabel pPunktestand = new JLabel(Integer.toString(gesamt));
		pPunktestand.setSize(190,30);
		pPunktestand.setLocation(5,280);
		pPunktestand.setFont(new Font("Arial", Font.BOLD, 20));
		pPunktestand.setHorizontalAlignment(JLabel.CENTER);
		this.add(pPunktestand);
		
		ok = new JButton("Weiter");
		ok.setSize(90,30);
		ok.setLocation(55,315);
		ok.setFont(new Font("Arial", Font.BOLD, 16));
		ok.setForeground(new Color(0,0,0));
		ok.setHorizontalAlignment(JLabel.CENTER);
		ok.addActionListener(this);
		this.add(ok);
		
		this.setVisible(true);		
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == ok)
		{
			this.setVisible(false);
			spiel.ereignisAusführen();
		}
	}
}
