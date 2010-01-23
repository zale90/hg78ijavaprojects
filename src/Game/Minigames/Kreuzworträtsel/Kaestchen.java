package Game.Minigames.Kreuzworträtsel;

import javax.swing.*;
import java.awt.*;

public class Kaestchen extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Frage frage;
	private Kaestchen[] antw;
	
	public Kaestchen(Frage frage, Kaestchen[] antwKaest) 
	{
		this.frage = frage;
		antw = antwKaest;
		
		this.setSize(50,50);
        this.setFont(new Font("Arial", 1, 50));
        this.setHorizontalAlignment(JTextField.CENTER);     
	}
	
	public Frage gibFrage()
	{
		return frage;
	}
	
	public Kaestchen[] gibAntw()
	{
		return antw;
	}
	
	public void setzeFrage(Frage frage)
	{
		this.frage = frage; 
	}
	
	public String toString()
	{
		String ant = "";
		
		for(int i = 0; i < antw.length; i++)
		{
			ant = ant + antw[i].getText().toLowerCase();
		}
		
		return ant;
	}
	
	public void markAnt(Color c)
	{
		for(int i = 0; i < antw.length; i++)
		{
			antw[i].setBackground(c);
		}
	}
}
