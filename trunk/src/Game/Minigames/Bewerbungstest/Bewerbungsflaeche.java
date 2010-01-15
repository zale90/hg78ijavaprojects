package Game.Minigames.Bewerbungstest;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import Game.*;
import Game.Minigames.*;

public class Bewerbungsflaeche extends JDialog implements ActionListener, MouseListener, Minispiel
{
	private static final long serialVersionUID = 4243815262315495694L;
//Anfang Attribute
   private JLabel lbl1;
   private JLabel lbl2;
   private JLabel lbl3;
   private JLabel lbl4;
   private JLabel lbl5;
   private JLabel lbl6;
   private JLabel lbl7;
   private JLabel area;
   private JRadioButton btn1;
   private JRadioButton btn2;
   private JRadioButton btn3;
   private JRadioButton btn4;
   private JRadioButton temp = null;
   private ButtonGroup group;
   private Fragensammlung fragen;
   private JButton btn;
   private int richtig;
   private Frage frage;
   private int index = 1;
   private int faktor;
   private boolean bestanden;
   private boolean erfolgreich;
   private Spiel game;
   private final String folder = "files/minigames/Bewerbungstest/";
   //Ende Attribute
   
   public Bewerbungsflaeche(int Bewerbungsfaktor){
      //Superklasse
      this.setLocation(300,200);
      this.setSize(550,300);
      this.setLayout(null);
      this.setResizable(false);
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      this.setUndecorated(true);
      this.setAlwaysOnTop(true);
      
      //Faktor für Wahrscheinlichkeit des Vorstellungsgespräches
      faktor = Bewerbungsfaktor;

      /**
      /* Neue Liste mit Fragen wird
      /* durch die Erzeugung eines Fragensammlungsobjektes
      /* Fragensammlungsobjektes herrgestellt
      **/
      fragen = new Fragensammlung();
      frage = fragen.gibFragenobjekt();
      richtig = 0;
      /**
      /* Grundoberflaeche wird mit Buttons 
      /* und Labels befüllt.
      **/
      
      lbl1 = new JLabel(frage.gibFrage());
      lbl1.setLocation(20,40);
      lbl1.setSize(530,20);
      lbl1.setForeground(Color.BLUE);
      lbl1.addMouseListener(this);
      this.add(lbl1);
      
      btn1 = new JRadioButton(frage.gibA());
      btn1.setLocation(20,70);
      btn1.setSize(460,20);
      this.add(btn1);
      
      btn2 = new JRadioButton(frage.gibB());
      btn2.setLocation(20,100);
      btn2.setSize(460,20);
      this.add(btn2);
      
      btn3 = new JRadioButton(frage.gibC());
      btn3.setLocation(20,130);
      btn3.setSize(460,20);
      this.add(btn3);
      
      btn4 = new JRadioButton(frage.gibD());
      btn4.setLocation(20,160);
      btn4.setSize(460,20);
      this.add(btn4);
      
      
      group = new ButtonGroup();
      group.add(btn1);
      group.add(btn2);
      group.add(btn3);
      group.add(btn4);
      
      //lbl2 gibt an, bei welcher Frage sich der Spieler befindet.
      lbl2 = new JLabel("Frage " + index + "/10");
      lbl2.setSize(70,20);
      lbl2.setLocation(450,10);
      lbl2.setForeground(Color.ORANGE);
      this.add(lbl2);
      
      //lbl3 dient zur Mitteilung des Ergebnisses in Prozent
      lbl3 = new JLabel();
      lbl3.setSize(275,20);
      lbl3.setLocation(20,240);
      this.add(lbl3);
      
      //lbl4 lässt Kreuz oder Hacken erscheinen.
      lbl4 = new JLabel();
      lbl4.setSize(40,40);
      lbl4.setLocation(490,btn1.getHeight()+20);
      lbl4.setVisible(false);
      this.add(lbl4);
      
      // Lässt Animation am Ende erscheinen
      lbl5 = new JLabel(new ImageIcon(folder + "feuerwerk_test.gif"));
      lbl5.setSize(100,100);
      lbl5.setLocation(125,50);
      lbl5.setVisible(false);
      this.add(lbl5);
      
      lbl7 = new JLabel(new ImageIcon(folder + "Feuerwerk603.gif"));
      lbl7.setSize(100,100);
      lbl7.setLocation(225,50);
      lbl7.setVisible(false);
      this.add(lbl7);
      
      // gibt Ergebnis am Ende an.
      lbl6 = new JLabel("Herzlichen Glückwunsch! Sie haben den Job bekommen!");
      lbl6.setSize(500,20);
      lbl6.setLocation(25,170);
      lbl6.setVisible(false);
      lbl6.setForeground(new Color(0,122,0));
      lbl6.addMouseListener(this);
      this.add(lbl6);
      
      btn = new JButton("Antwort");
      btn.setSize(100,30);
      btn.setLocation(175,210);
      btn.setForeground(Color.BLACK);
      btn.setLayout(null);
      btn.setContentAreaFilled(false);
      btn.setFocusPainted(false);
      btn.setEnabled(false);
      btn.setBorder(new LineBorder(Color.BLACK, 2));
      this.add(btn);
      
      // Buttons erhalten Farben
      btn.addActionListener(this);
      btn1.addActionListener(this);
      btn2.addActionListener(this);
      btn3.addActionListener(this);
      btn4.addActionListener(this);
      
      btn1.setForeground(Color.BLACK);
      btn2.setForeground(Color.BLACK);
      btn3.setForeground(Color.BLACK);
      btn4.setForeground(Color.BLACK);
      
      btn1.setFocusPainted(false);
      btn2.setFocusPainted(false);
      btn3.setFocusPainted(false);
      btn4.setFocusPainted(false);

      btn1.setContentAreaFilled(false);
      btn2.setContentAreaFilled(false);
      btn3.setContentAreaFilled(false);
      btn4.setContentAreaFilled(false);
      
      btn1.addMouseListener(this);
      btn2.addMouseListener(this);
      btn3.addMouseListener(this);
      btn4.addMouseListener(this);
      
      btn1.setFont(new Font("Arial",1,12));
      btn2.setFont(new Font("Arial",1,12));
      btn3.setFont(new Font("Arial",1,12));
      btn4.setFont(new Font("Arial",1,12));
      lbl1.setFont(new Font("Arial",1,12));
      
      
      
      bestanden = true;
      erfolgreich = true;
      
      //Hintergrund
      area = new JLabel();
      area.setEnabled(false);
      area.setIcon(new ImageIcon(folder + "hintergrund.jpg"));
      area.setSize(this.getSize());
      area.setLocation(0,0);
      this.add(area);
}

      public void actionPerformed(ActionEvent evt){

      int antwort = 0;

      if(evt.getSource() == btn && btn.getText().equals("Antwort")){
         if(btn1.isSelected()){
            antwort = 1;
            btn1.setForeground(Color.RED);
            temp = btn1;
         }
         else if(btn2.isSelected()){
          antwort = 2;
          btn2.setForeground(Color.RED);
          temp = btn2;
         }
         else if(btn3.isSelected()){
         antwort = 3;
         btn3.setForeground(Color.RED);
         temp = btn3;
         }
         else if(btn4.isSelected()){
         antwort = 4;
         btn4.setForeground(Color.RED);
         temp = btn4;
         }

         lbl4.setLocation(490,temp.getY());

         if(frage.gibAntwort() == antwort){
            richtig++;
            temp.setForeground(Color.GREEN);
            lbl4.setIcon(new ImageIcon(folder + "gruener_haken.png"));
            }
            
         else{
             if(frage.gibAntwort() == 1)
             btn1.setForeground(Color.GREEN);
             else if(frage.gibAntwort() == 2)
             btn2.setForeground(Color.GREEN);
             else if(frage.gibAntwort() == 3)
             btn3.setForeground(Color.GREEN);
             else if(frage.gibAntwort() == 4)
             btn4.setForeground(Color.GREEN);
             lbl4.setIcon(new ImageIcon(folder + "40px-Red_x.svg.png"));
             bestanden = false;
             }
             
             
            
         lbl4.setVisible(true);
         btn.setText("Weiter");
         if(index == 10)
         btn.setText("Ergebnis");
      }
      else if(evt.getSource() == btn && index < 10){
         lbl4.setVisible(false);
         btn1.setForeground(Color.BLACK);
         btn2.setForeground(Color.BLACK);
         btn3.setForeground(Color.BLACK);
         btn4.setForeground(Color.BLACK);
         frage = fragen.gibFragenobjekt();
         lbl1.setText(frage.gibFrage());
         btn1.setText(frage.gibA());
         btn2.setText(frage.gibB());
         btn3.setText(frage.gibC());
         btn4.setText(frage.gibD());
         btn.setText("Antwort");
         index++;
         lbl2.setText("Frage " + index + "/10");
         btn.setEnabled(false);
         group.clearSelection();
      }
      else if(evt.getSource() == btn && btn.getText().equals("Ergebnis"))
      {
         btn1.setVisible(false);
         btn2.setVisible(false);
         btn3.setVisible(false);
         btn4.setVisible(false);
         lbl1.setVisible(false);
         lbl2.setVisible(false);
         lbl4.setVisible(false);
         lbl3.setText("Sie haben " + richtig*10 + "% der Fragen korrekt beantwortet.");
         btn.setText("Beenden");
         
         Bedürfnis[] dürfnisse = game.getBedürfnisse();
         Bedürfnis gesund = dürfnisse[1];
         if(gesund.getWert() < 30)
         bestanden = false;
         
         if(!bestanden){
            lbl5.setSize(128,96);
            lbl5.setLocation(225-128,50);
            lbl5.setIcon(new ImageIcon(folder + "alien.gif"));
            lbl6.setForeground(Color.YELLOW);
            lbl6.setText("Schade. Sie haben es nicht geschafft.");
            if(gesund.getWert() < 30)
            lbl6.setText("Aufgrund fehlender Hygiene wurden Sie nicht genommen.");
            lbl7.setIcon(new ImageIcon(folder + "Gesicht102.gif"));
            lbl7.setSize(128,96);
            lbl7.setLocation(225,50);
            erfolgreich = false;
         }
         else if(!job()){
         lbl6.setText("Sie haben den Test bestanden. Jedoch ist Ihr Vorstellungsgespräch daneben gegangen.");
         lbl7.setIcon(new ImageIcon(folder + "Gesicht102.gif"));
         lbl7.setSize(128,96);
         lbl7.setLocation(225,50);
         erfolgreich = false;
         }
         
         lbl5.setVisible(true);
         lbl6.setVisible(true);
         lbl7.setVisible(true);
      }
      else if(evt.getSource()== btn){
      game.minispielEnde(erfolgreich(),lbl6.getText());
      this.setVisible(false);
      }

      }
      
      private boolean job(){
      Random zufallsgenerator = new Random();
      if(zufallsgenerator.nextInt(faktor)+1 == 1)
      return true;
      return false;
      }
      
      public void mouseEntered(MouseEvent evt){
      if(evt.getSource() == btn1)
      btn1.setFont(new Font("Arial",1,15));
      else if(evt.getSource() == btn2)
      btn2.setFont(new Font("Arial",1,15));
      else if(evt.getSource() == btn3)
      btn3.setFont(new Font("Arial",1,15));
      else if(evt.getSource() == btn4)
      btn4.setFont(new Font("Arial",1,15));
      else if(evt.getSource() == lbl1)
      lbl1.setFont(new Font("Arial",1,15));
      else if(evt.getSource() == lbl6)
      lbl6.setFont(new Font("Arial",1,15));
      }
      
      public void mouseExited(MouseEvent evt){
      if(evt.getSource() == btn1)
      btn1.setFont(new Font("Arial",1,12));
      else if(evt.getSource() == btn2)
      btn2.setFont(new Font("Arial",1,12));
      else if(evt.getSource() == btn3)
      btn3.setFont(new Font("Arial",1,12));
      else if(evt.getSource() == btn4)
      btn4.setFont(new Font("Arial",1,12));
      else if(evt.getSource() == lbl1)
      lbl1.setFont(new Font("Arial",1,12));
      else if(evt.getSource() == lbl6)
      lbl6.setFont(new Font("Arial",1,12));

      }
      
      public void mouseReleased(MouseEvent evt){

      }
      
      public void mousePressed(MouseEvent evt){

      }
      
      public void mouseClicked(MouseEvent evt){
      if(evt.getSource() == btn1||evt.getSource() == btn2||evt.getSource() == btn3||evt.getSource() == btn4)
      btn.setEnabled(true);

      }
      
      public Information[] erfolgreich(){
      Information[] info;
      if(erfolgreich){
  	    info = new Information[3];
            info[0] = new Information(14,2,400);
            info[1] = new Information(12,2,-3);
            info[2] = new Information(7,2,15);
      }
      else{
  	     info = new Information[1];
         info[0] = new Information(5,2,-5);
      }
      return info;

      }

      public void start(Spiel spiel){
        game = spiel;
        this.setVisible(true);
      }
      
//      public static void main(String[] args)
//      {
//         new Bewerbungsflaeche(100);
//      }
      
}