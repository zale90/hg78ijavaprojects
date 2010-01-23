package Game.Minigames.Kreuzworträtsel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Game.*;
import Game.Minigames.Minispiel;

public class KreuzGUI extends JFrame implements ActionListener, KeyListener, MouseListener, Minispiel{

       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Matrix m;
       private JButton exit;
       private Kaestchen[] gelb, fZzt;
       private int pos, punkte;
       private MausLabel lblMaus;
       private Thread threadlblMaus;
       private Spiel spiel;
       
       public KreuzGUI()
       {
              super();
              this.setSize(505, 605);
              this.setLocation(350, 150);
              this.setLayout(null);
              //this.setDefaultCloseOperation();
              this.setAlwaysOnTop(true);
              this.setUndecorated(true);
              
              try{
            	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
              }
              catch(Exception e) {
            	  }
              
              exit = new JButton("Abschicken");
              exit.setSize(250,60);
              exit.setLocation(127, 495);
              exit.setOpaque(true);
              exit.addActionListener(this);
              this.add(exit);
              
              lblMaus = new MausLabel(this);          
              this.add(lblMaus);
              threadlblMaus = new Thread(lblMaus);
              
              m = new Matrix(9, 9, this, this);
              matrixEinfuegen(25, 25);
              m.addActionListener(this);
              m.addMouseListener(this);
              
              pos = 0;
              
              m.matrixFaerben(1, 1, Color.BLACK, false);
              m.matrixFaerben(1, 8, Color.BLACK, false);
              m.matrixFaerben(2, 1, Color.BLACK, false);
              m.matrixFaerben(4, 5, Color.BLACK, false);
              m.matrixFaerben(4, 6, Color.BLACK, false);
              m.matrixFaerben(4, 7, Color.BLACK, false);
              m.matrixFaerben(5, 5, Color.BLACK, false);
              m.matrixFaerben(5, 6, Color.BLACK, false);
              m.matrixFaerben(5, 7, Color.BLACK, false);
              m.matrixFaerben(6, 5, Color.BLACK, false);
              m.matrixFaerben(6, 6, Color.BLACK, false);
              m.matrixFaerben(6, 7, Color.BLACK, false);
              m.matrixFaerben(7, 4, Color.BLACK, false);
              m.matrixFaerben(7, 5, Color.BLACK, false);
              m.matrixFaerben(7, 6, Color.BLACK, false);
              m.matrixFaerben(7, 7, Color.BLACK, false);
              m.matrixFaerben(7, 8, Color.BLACK, false);
              
              this.setVisible(false);
        }
       
        
   public void matrixEinfuegen(int x, int y)
        {
        	m.matrixVerbildschirmen(x, y);
        	
        	for(int i = 0; i< m.gibGroesse(); i++)
        	{
        		this.add(m.gibKaest()[i]);
        	}
         }
        
   public int antwortenAuslesen()
   {
      	punkte = 0;
       	
       	for(int i = 0; i < m.gibFragen().length; i++)
       	{
       		Kaestchen ant = m.gibKaest()[((m.gibY()*(m.gibFragen()[i].gibY()-1)+m.gibFragen()[i].gibX())-1)];
       		String hAnt = ant.toString();
        		
       		if(hAnt.equalsIgnoreCase(m.gibFragen()[i].gibAntwort()))
       			punkte++;
       		else
       			ant.markAnt(new Color(255,0,0));
       	}
       	return punkte;
   }     
   
   public void start(Spiel spiel)
   {
	   this.spiel = spiel;
	   this.setVisible(true);
   }

   public void actionPerformed(ActionEvent e)
   {
	   if(e.getSource() == exit)
	   {
		     
		   JOptionPane.showMessageDialog(this, "Du hast " + antwortenAuslesen() + " Punkte von 12 möglichen Punkten erreicht.");
		   if(antwortenAuslesen() == 12)
		   {
			   Information[] infos = {
					   new Information(Information.AENDERN_LUXUS, Information.ART_UM_WERT, 15),
					   new Information(Information.AENDERN_SOZIALES, Information.ART_UM_WERT, 10)
			   };
			   
			   spiel.minispielEnde(infos, "Du hast eine Tagesfahrt nach Bad Münster Eifel gewonnen!");
		   }
		   else
		   {
			   spiel.minispielEnde(null, "Leider hat deine Leistung im Kreuzworträtsel nicht gereicht.");
		   }
		   this.setVisible(false);
	   }

		    
   }
   
   public void keyPressed(KeyEvent e)
   {

   }
   
   public void keyReleased(KeyEvent e)
   {

   }
   
   public void keyTyped(KeyEvent e)
   {
	   try
	   {	   
		   if(pos <= gelb.length)
			   gelb[pos].setText(String.valueOf(Character.toUpperCase(e.getKeyChar())));
		 
		   pos++;   
		   
		   gelb[pos+1].requestFocus();
	   }
	   catch(Exception exp){
		   }
   }
   
   public void mouseClicked(MouseEvent e)
   {
	   try
	   {
		   if(gelb != null)
		   {
			   for(int i = 0; i <  gelb.length; i++)
				   gelb[i].setBackground(new Color(255,255,255));

		   }
		   
		   Kaestchen src = (Kaestchen)e.getSource();
		   Frage q = src.gibFrage();
		   fZzt = m.gibKaest()[((m.gibY()*(q.gibY()-1)+q.gibX())-1)].gibAntw();
		   pos = 0;
		   
		    for(int i = 0; i <  fZzt.length; i++)
		   	{
		   		fZzt[i].setBackground(new Color(255,246,133));	
		   		
				   if(!fZzt[i].getText().equals(""))
					   fZzt[i].setText("");
		   	}    
		    gelb = fZzt;
	   }
	   catch(Exception exp){
		   }
   }
   
   public void mouseEntered(MouseEvent e)
   {
	   try
	   {
		   Kaestchen src = (Kaestchen)e.getSource();	 
	   
		   lblMaus.setText(src.gibFrage().gibFrage());
		   lblMaus.setVisible(true);

		   try 
		   {
			   threadlblMaus = new Thread(lblMaus);
			   threadlblMaus.start();
		   } 
		   catch (Exception ex) {           
           		}
	   }
	   catch(Exception exp){
		   }
   }
   
   public void mouseExited(MouseEvent e)
   {
	   lblMaus.setVisible(false);
   }
   
   public void mousePressed(MouseEvent e)
   {
	   
   }
   
   public void mouseReleased(MouseEvent e)
   {

   }
   
   public void starten(int x, int y)
   {
	   //KreuzGUI guiKreuz = new KreuzGUI();
	   this.setVisible(true);
   }
}
