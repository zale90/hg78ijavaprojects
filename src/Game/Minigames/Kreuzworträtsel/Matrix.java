package Game.Minigames.Kreuzworträtsel;

import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class Matrix
{
       private Kaestchen[] kaestchenens;
       
       private Frage[] f1, f2, f3, f4, f5, fragen;
       private Frage[][] f;
       private Random rnd;
       private int x, y;
       private MouseListener mouseList;
       private KeyListener keyList;
       
       public Matrix(int xAnz, int yAnz, MouseListener msL, KeyListener keyL)
       {
    	   x = xAnz;
    	   y = yAnz;
    	   kaestchenens = new Kaestchen[x*y];
    	   
    	   mouseList = msL;
    	   keyList = keyL;
    	   
    	   f1 = new Frage[12];
    	   f2 = new Frage[12];
    	   f3 = new Frage[12];
    	   f4 = new Frage[12];
    	   f5 = new Frage[12];
    	   f = new Frage[5][];
    	   
    	   //true	= horizontal
    	   //false  = vertikal 
		  	f1[0] = new Frage("Zentrales Organ des Stoffwechsels", "Leber", false, 2, 1); 
			f1[1] = new Frage("Herren Oberbekleidung", "Hemd", true, 3, 2);
			f1[2] = new Frage("Sohn meines Vaters", "Bruder", false, 1, 3);
			f1[3] = new Frage("Behältnis für Zahnpasta", "Tube", true, 3, 1);
			f1[4] = new Frage("Spaltbar zur Energiegewinnung", "Atom", false, 8, 2);
			f1[5] = new Frage("KFZ-Bielefeld", "BI", false, 2, 7);
			f1[6] = new Frage("Rohstoff für Wein", "Traube", true, 3, 9);
			f1[7] = new Frage("Rechtlich strittiger Bereich", "Grauzone", false, 9, 1);
			f1[8] = new Frage("Gegenteil Tod", "Leben", true, 4, 8);
			f1[9] = new Frage("Frau des Bruders meines Vaters", "Tante", false, 3, 3);
			f1[10] = new Frage("Türkischer Trödelmarkt", "Basar", true, 4, 3);
			f1[11] = new Frage("Sexuelle Ausrichtung", "BI", false, 4, 4);

			f2[0] = new Frage("Himmelsratte", "Taube", false, 2, 1); 
			f2[1] = new Frage("Bekleidung für Damen", "Rock", true, 3, 2);
			f2[2] = new Frage("Gunst des Schicksals", "Chance", false, 1, 3);
			f2[3] = new Frage("Asiatische Bevölkerungsgruppe", "Thai", true, 3, 1);
			f2[4] = new Frage("Verbrechen", "Mord", false, 8, 2);
			f2[5] = new Frage("KFZ-Bayreuth", "BT", false, 2, 7);
			f2[6] = new Frage("Sinnflutartiger Regen", "Taifun", true, 3, 9);
			f2[7] = new Frage("Franz. Kaiser", "Napoleon", false, 9, 1);
			f2[8] = new Frage("Kosten für Post", "Porto", true, 4, 8);
			f2[9] = new Frage("Öffnung im Erdreich", "Spalt", false, 3, 3);
			f2[10] = new Frage("Inhalt oder Grund einer Sache", "Thema", true, 4, 3);
			f2[11] = new Frage("Auszeichnung oder Titel", "Dr", false, 4, 4);

			f3[0] = new Frage("Gegenteil Differenz", "Summe", false, 2, 1); 
			f3[1] = new Frage("Schöpfer im Christentum", "Gott", true, 3, 2);
			f3[2] = new Frage("Zeiteinheit", "Minute", false, 1, 3);
			f3[3] = new Frage("öffentliches Verkehrsmittel", "Bahn", true, 3, 1);
			f3[4] = new Frage("Erfolgreicher Bekämpfer des Bösen", "Held", false, 8, 2);
			f3[5] = new Frage("KFZ Dresden", "DD", false, 2, 7);
			f3[6] = new Frage("Tote Sprache", "Latein", true, 3, 9);
			f3[7] = new Frage("Täuschung", "Illusion", false, 9, 1);
			f3[8] = new Frage("Pizza Thunfisch", "Tonno", true, 4, 8);
			f3[9] = new Frage("Fadenförmiges Geflecht in Kerze", "Docht", false, 3, 3);
			f3[10] = new Frage("extrem harte Metalllegierung", "Stahl", true, 4, 3);
			f3[11] = new Frage("engl. : Künstiliche Intelligenz", "AI", false, 4, 4);

			f4[0] = new Frage("schwarz- weißes Tier", "zebra", false, 2, 1); 
			f4[1] = new Frage("Nordsee Insel", "Sylt", true, 3, 2);
			f4[2] = new Frage("Hauptstadt Russland", "Moskau", false, 1, 3);
			f4[3] = new Frage("Weltfußballverband", "Fifa", true, 3, 1);
			f4[4] = new Frage("Rythmische Musikeinheit", "Takt", false, 8, 2);
			f4[5] = new Frage("KFZ Erfurt", "EF", false, 2, 7);
			f4[6] = new Frage("Müll", "Abfall", true, 3, 9);
			f4[7] = new Frage("Alkoholhaltiges Fruchtgetränk", "Cocktail", false, 9, 1);
			f4[8] = new Frage("Österreichische Kaiserin", "Sissi", true, 4, 8);
			f4[9] = new Frage("Mann der Schwester meiner Mutter", "Onkel", false, 3, 3);
			f4[10] = new Frage("Brutto - MwSt. = ?", "Netto", true, 4, 3);
			f4[11] = new Frage("Fehlerschutzschalter", "FI", false, 4, 4);

			f5[0] = new Frage("Tanz - Schuppen", "Disco", false, 2, 1); 
			f5[1] = new Frage("Kletterpflanze", "Efeu", true, 3, 2);
			f5[2] = new Frage("Gotteshaus", "Kirche", false, 1, 3);
			f5[3] = new Frage("KFZ", "Auto", true, 3, 1);
			f5[4] = new Frage("Alkoholhaltiges Kaltgetränk", "Bier", false, 8, 2);
			f5[5] = new Frage("KFZ Kassel", "KL", false, 2, 7);
			f5[6] = new Frage("Gerichtsdiener", "Anwalt", true, 3, 9);
			f5[7] = new Frage("\"Bauer\"", "Landwirt", false, 9, 1);
			f5[8] = new Frage("Chemisches Element Cl2", "Chlor", true, 4, 8);
			f5[9] = new Frage("Befestigungshilfe für Schiffe", "Anker", false, 3, 3);
			f5[10] = new Frage("Brasilianischer Paartanz", "Samba", true, 4, 3);
			f5[11] = new Frage("Britische Rock-Band", "U2", false, 4, 4);

			f[0] = f1;
			f[1] = f2;
			f[2] = f3;
			f[3] = f4;
			f[4] = f5;
    	   
			rnd = new Random();
    	   
			fragenZusammenstellen();
			matrixErstellen();
			fragenMatrix();
       }
       
       public void erschaffeMatrix()
       {
    	   for(int i = 0; i < y; i++)
    	   {
    		   for(int j = 0; j < x; j++)
    		   {
    			   kaestchenens[j+(i*y)] = new Kaestchen(null, null);
    		   }
    	   }
       }    
       
       public void fragenZusammenstellen()
       {
    		  fragen = f[rnd.nextInt(5)];
       }
       
       public void matrixErstellen()
       {
    	   for(int i = 0; i < kaestchenens.length; i++)
    	   {
    		  kaestchenens[i] = new Kaestchen(null, null);
    		  kaestchenens[i].setOpaque(true);
    		  kaestchenens[i].addKeyListener(keyList);
    		  kaestchenens[i].addMouseListener(mouseList);
    	   }
       }
       
       public void fragenMatrix()
       {
    	   Kaestchen[] antw;
    	   int multiplikator = 1;
    	   
    	   for(int i = 0; i < fragen.length; i++ )
    	   {
    		   	Frage a = fragen[i];
    		   	
    		   	antw = new Kaestchen[a.gibAntwort().length()];
    		   	
    		   	if(!a.gibAusrichtung())
    		   		multiplikator = 9;
    		   	else
    		   		multiplikator = 1;
    		   
    		   	for(int j = 0; j < antw.length; j++)
    		   	{
    		   		antw[j] = kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)+(multiplikator*(j+1))];
    		   		kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)+(multiplikator*(j+1))].setzeFrage(a);
    		   		kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)+(multiplikator*(j+1))].setEditable(false);
    		   		kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)+(multiplikator*(j+1))].setBackground(new Color(255,255,255));
    		   		kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)+(multiplikator*(j+1))].setFont(new Font("Andy", Font.BOLD, 50));  		
    		   	}
    		   
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)] = new Kaestchen(a, antw);
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)].setText("?");
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)].setForeground(new Color(0,188,18));
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)].setBackground(new Color(138,70,0));
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)].setEditable(false);
    		   	kaestchenens[((y*(a.gibY()-1)+a.gibX())-1)].setEnabled(false);
    	   }
       } 
       
       public void matrixVerbildschirmen(int xStart, int yStart)
       {
    	   int scX = xStart;
    	   int scY = yStart;
    	   
    	   for(int i = 0; i < y; i++)
    	   { 
    		   for(int j = 0; j < x; j++)
    		   {
    			   kaestchenens[j+(i*y)].setLocation(scX+(j*50),scY);
    		   }
    		   scY = scY+50;
    	   }
       }
       
       public void matrixFaerben(int xS, int yS, Color c, boolean enabled)
       {
    	   kaestchenens[(((x*(xS-1))+yS)-1)].setBackground(c);
    	   kaestchenens[(((x*(xS-1))+yS)-1)].setEnabled(enabled);
       }
       
       public void addActionListener(ActionListener a)
       {
    	   for(int i = 0; i < kaestchenens.length; i++)
    	   {
    		   kaestchenens[i].addActionListener(a);
    	   }
       }
       
       public void addMouseListener(MouseListener m)
       {
    	   for(int i = 0; i < kaestchenens.length; i++)
    	   {
    		   kaestchenens[i].addMouseListener(m);
    	   }
       }
       
       public int gibGroesse()
       {
    	   return x*y;
       }
       
       public int gibX()
       {
    	   return x;
       }
       
       public int gibY()
       {
    	   return y;
       }
       
       public Kaestchen[] gibKaest()
       {
    	   return kaestchenens;
       }
       
       public Frage[] gibFragen()
       {
    	   return fragen;
       }
}