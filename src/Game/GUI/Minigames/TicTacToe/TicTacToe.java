package Game.GUI.Minigames.TicTacToe;

import java.applet.*;
import java.net.*;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.font.*;

import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;

import Game.Spiel;
import Game.GUI.Minigames.Minispiel;
import Game.Information;

public class TicTacToe extends JFrame implements Minispiel, Runnable, ActionListener, MouseListener
{

  // Anfang Attribute
  private ImageIcon panelHintergrund, hintergrund, hintergrund1,  hintergrund2, schwamm;
  private JLabel hin, hin1, hin2, start, start2, ende, ende2, text, panelHin, schw, x1, x2, x3, x4, x5, x6, x7, x8, x9, o1, o2, o3, o4, o5, o6, o7, o8, o9,
  cpupl, plcpu, hauptmenu, hauptmenu2, schwierigkeit, einfach, normal, schwer, soundTaste, soundTaste2;

  private JButton taste1, taste2, taste3, taste4, taste5, taste6, taste7, taste8, taste9;
  private Thread thread;
  private int felder[] = {1 , 2 , 3, 4, 5, 6, 7, 8, 9};
  private int zufallsZahl;
  private String endText;

   private File sound;
   private AudioInputStream audioInputStream;
   private AudioFormat af;
   private int size;
   private byte[] audio;
   private DataLine.Info info;
   private Clip clip;

   private File tastenSound;
   private File tastenSound2;
   private File cpuSound;


  boolean cursor = true;
  boolean veryEasy = false;
  boolean easy = false;
  boolean hard = false;
  boolean los = false;
  boolean gewonnen = false;
  boolean lautstaerke = true;
   
  boolean pressedT1 = false;
  boolean pressedT2 = false;
  boolean pressedT3 = false;
  boolean pressedT4 = false;
  boolean pressedT5 = false;
  boolean pressedT6 = false;
  boolean pressedT7 = false;
  boolean pressedT8 = false;
  boolean pressedT9 = false;
  
  boolean cpuPlayed =false;

  boolean eins = false;
  boolean zwei = false;
  boolean drei = false;
  boolean vier = false;
  boolean fünf = false;
  boolean sechs = false;
  boolean sieben = false;
  boolean acht = false;
  boolean neun = false;
  
  boolean cpufeld1 = false;
  boolean cpufeld2 = false;
  boolean cpufeld3 = false;
  boolean cpufeld4 = false;
  boolean cpufeld5 = false;
  boolean cpufeld6 = false;
  boolean cpufeld7 = false;
  boolean cpufeld8 = false;
  boolean cpufeld9 = false;
  
  private Spiel spiel;
  // Ende Attribute


  public TicTacToe()
  {
       super();
       this.setSize(600, 400);
       this.setLocation(200, 200);
       this.setLayout(null);
       this.setUndecorated(true);
       this.setResizable(false);
       this.setAlwaysOnTop(true);
       this.setBackground(new Color(0, 10, 0));
       this.setForeground(new Color(100, 10, 5));

       schwamm = new ImageIcon("schwamm.png");
       schw = new JLabel(schwamm);
       schw.setLocation(-120,90);
       schw.setSize(schwamm.getIconWidth(), schwamm.getIconHeight());
       schw.setVisible(true);
       this.add(schw);

       ImageIcon soundIcon = new ImageIcon("data/sound.png");
       soundTaste = new JLabel(soundIcon);
       soundTaste.setSize(30, 29);
       soundTaste.setLocation(555, 360);
       soundTaste.setVisible(false);
       this.add(soundTaste);
       soundTaste.addMouseListener(this);

       ImageIcon soundIcon2 = new ImageIcon("data/sound2.png");
       soundTaste2 = new JLabel(soundIcon2);
       soundTaste2.setSize(30, 29);
       soundTaste2.setLocation(555, 360);
       soundTaste2.setVisible(true);
       this.add(soundTaste2);
       soundTaste2.addMouseListener(this);

       hintergrund = new ImageIcon("data/hintergrund.png");
       hin = new JLabel(hintergrund);
       hin.setLocation(0, 0);
       hin.setSize(hintergrund.getIconWidth(), hintergrund.getIconHeight());

       hintergrund1 = new ImageIcon("data/hintergrund1.png");
       hin1 = new JLabel(hintergrund1);
       hin1.setLocation(10, 85);
       hin1.setSize(hintergrund1.getIconWidth(), hintergrund1.getIconHeight());
       hin1.setVisible(false);
       this.add(hin1);

       hintergrund2 = new ImageIcon("data/hintergrund2.png");
       hin2 = new JLabel(hintergrund2);
       hin2.setLocation(10, 85);
       hin2.setSize(hintergrund2.getIconWidth(), hintergrund2.getIconHeight());
       hin2.setVisible(false);
       this.add(hin2);
       
       ImageIcon startButton = new ImageIcon("data/start.png");
       start = new JLabel(startButton);
       start.setLocation(400, 160);
       start.setSize(startButton.getIconWidth(), startButton.getIconHeight());
       start.setVisible(false);
       this.add(start);
       start.addMouseListener(this);
       
       ImageIcon startButton2 = new ImageIcon("data/start2.png");
       start2 = new JLabel(startButton2);
       start2.setLocation(400, 160);
       start2.setSize(startButton.getIconWidth(), startButton.getIconHeight());
       start2.setVisible(false);
       this.add(start2);
       start2.addMouseListener(this);
       
       ImageIcon endeButton = new ImageIcon("data/ende.png");
       ende = new JLabel(endeButton);
       ende.setLocation(380, 250);
       ende.setSize(endeButton.getIconWidth(), endeButton.getIconHeight());
       ende.setVisible(false);
       this.add(ende);
       ende.addMouseListener(this);
       
       ImageIcon endeButton2 = new ImageIcon("data/ende2.png");
       ende2 = new JLabel(endeButton2);
       ende2.setLocation(380, 250);
       ende2.setSize(endeButton.getIconWidth(), endeButton.getIconHeight());
       ende2.setVisible(false);
       this.add(ende2);
       ende2.addMouseListener(this);

       text = new JLabel("DRAW!");
       text.setSize(200, 76);
       text.setLocation(67, 5);
       text.setFont(new Font("Times New Roman", Font.PLAIN, 40));
       text.setForeground(Color.WHITE);
       text.setVisible(false);
       hin2.add(text);
       
       plcpu = new JLabel("SPIELER - PC");
       plcpu.setSize(200, 76);
       plcpu.setLocation(400, 100);
       plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       plcpu.setForeground(Color.WHITE);
       plcpu.setVisible(false);
       this.add(plcpu);
       plcpu.addMouseListener(this);

       cpupl = new JLabel("PC - SPIELER");
       cpupl.setSize(200, 76);
       cpupl.setLocation(400, 150);
       cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       cpupl.setForeground(Color.WHITE);
       cpupl.setVisible(false);
       this.add(cpupl);
       cpupl.addMouseListener(this);
       
       hauptmenu = new JLabel("HAUPTMENÜ");
       hauptmenu.setSize(200, 76);
       hauptmenu.setLocation(380, 300);
       hauptmenu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       hauptmenu.setForeground(Color.WHITE);
       hauptmenu.setVisible(false);
       this.add(hauptmenu);
       hauptmenu.addMouseListener(this);
       
       hauptmenu2 = new JLabel("HAUPTMENÜ");
       hauptmenu2.setSize(200, 76);
       hauptmenu2.setLocation(360, 50);                                           // hauptmenü(nur Text)
       hauptmenu2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       hauptmenu2.setForeground(Color.WHITE);
       hauptmenu2.setEnabled(false);
       hauptmenu2.setVisible(false);
       this.add(hauptmenu2);
       
       schwierigkeit = new JLabel("SCHWIERIGKEIT");
       schwierigkeit.setSize(250, 76);
       schwierigkeit.setLocation(360, 50);
       schwierigkeit.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       schwierigkeit.setForeground(Color.WHITE);
       schwierigkeit.setEnabled(false);
       schwierigkeit.setVisible(false);
       this.add(schwierigkeit);
       
       einfach = new JLabel("EINFACH");
       einfach.setSize(200, 76);
       einfach.setLocation(415, 125);
       einfach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       einfach.setForeground(Color.WHITE);
       einfach.setVisible(false);
       this.add(einfach);
       einfach.addMouseListener(this);

       normal = new JLabel("NORMAL");
       normal.setSize(200, 76);
       normal.setLocation(415, 175);
       normal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       normal.setForeground(Color.WHITE);
       normal.setVisible(false);
       this.add(normal);
       normal.addMouseListener(this);
       
       schwer = new JLabel("SCHWER");
       schwer.setSize(200, 76);
       schwer.setLocation(415, 225);
       schwer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       schwer.setForeground(Color.WHITE);
       schwer.setVisible(false);
       this.add(schwer);
       schwer.addMouseListener(this);

       x1 = new JLabel("");
       x1.setSize(100, 100);
       x1.setLocation(30, 60);
       x1.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x1.setForeground(Color.WHITE);
       x1.setVisible(true);
       this.add(x1);

       x2 = new JLabel("");
       x2.setSize(100, 100);
       x2.setLocation(135, 40);
       x2.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x2.setForeground(Color.WHITE);
       x2.setVisible(true);
       this.add(x2);

       x3 = new JLabel("");
       x3.setSize(100, 100);
       x3.setLocation(235, 25);
       x3.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x3.setForeground(Color.WHITE);
       x3.setVisible(true);
       this.add(x3);

       x4 = new JLabel("");
       x4.setSize(100, 100);
       x4.setLocation(50, 170);
       x4.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x4.setForeground(Color.WHITE);
       x4.setVisible(true);
       this.add(x4);

       x5 = new JLabel("");
       x5.setSize(100, 100);
       x5.setLocation(160, 150);
       x5.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x5.setForeground(Color.WHITE);
       x5.setVisible(true);
       this.add(x5);

       x6 = new JLabel("");
       x6.setSize(100, 100);
       x6.setLocation(260, 130);
       x6.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x6.setForeground(Color.WHITE);
       x6.setVisible(true);
       this.add(x6);

       x7 = new JLabel("");
       x7.setSize(100, 100);
       x7.setLocation(75, 280);
       x7.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x7.setForeground(Color.WHITE);
       x7.setVisible(true);
       this.add(x7);

       x8 = new JLabel("");
       x8.setSize(100, 100);
       x8.setLocation(180, 260);
       x8.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x8.setForeground(Color.WHITE);
       x8.setVisible(true);
       this.add(x8);

       x9 = new JLabel("");
       x9.setSize(100, 100);
       x9.setLocation(280, 240);
       x9.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       x9.setForeground(Color.WHITE);
       x9.setVisible(true);
       this.add(x9);

       o1 = new JLabel(" ");
       o1.setSize(100, 100);
       o1.setLocation(35, 60);
       o1.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o1.setForeground(Color.WHITE);
       o1.setVisible(false);
       this.add(o1);
       o1.addMouseListener(this);

       o2 = new JLabel(" ");
       o2.setSize(100, 100);
       o2.setLocation(135, 40);
       o2.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o2.setForeground(Color.WHITE);
       o2.setVisible(false);
       this.add(o2);
       o2.addMouseListener(this);

       o3 = new JLabel(" ");
       o3.setSize(100, 100);
       o3.setLocation(230, 25);
       o3.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o3.setForeground(Color.WHITE);
       o3.setVisible(false);
       this.add(o3);
       o3.addMouseListener(this);

       o4 = new JLabel(" ");
       o4.setSize(100, 100);
       o4.setLocation(55, 170);
       o4.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o4.setForeground(Color.WHITE);
       o4.setVisible(false);
       this.add(o4);
       o4.addMouseListener(this);

       o5 = new JLabel(" ");
       o5.setSize(100, 100);
       o5.setLocation(155, 150);
       o5.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o5.setForeground(Color.WHITE);
       o5.setVisible(false);
       this.add(o5);
       o5.addMouseListener(this);

       o6 = new JLabel(" ");
       o6.setSize(100, 100);
       o6.setLocation(255, 130);
       o6.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o6.setForeground(Color.WHITE);
       o6.setVisible(false);
       this.add(o6);
       o6.addMouseListener(this);

       o7 = new JLabel(" ");
       o7.setSize(100, 100);
       o7.setLocation(70, 280);
       o7.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o7.setForeground(Color.WHITE);
       o7.setVisible(false);
       this.add(o7);
       o7.addMouseListener(this);

       o8 = new JLabel(" ");
       o8.setSize(100, 100);
       o8.setLocation(178, 260);
       o8.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o8.setForeground(Color.WHITE);
       o8.setVisible(false);
       this.add(o8);
       o8.addMouseListener(this);

       o9 = new JLabel(" ");
       o9.setSize(100, 100);
       o9.setLocation(275, 240);
       o9.setFont(new Font("Segoe Print", Font.PLAIN, 90));
       o9.setForeground(Color.WHITE);
       o9.setVisible(false);
       this.add(o9);
       o9.addMouseListener(this);


       sound = new File("data/playerSound.wav");
       tastenSound = new File("data/tasten.wav");
       tastenSound2 = new File("data/tastenEnter.wav");
       cpuSound = new File("data/cpuSound.wav");

       this.add(hin);

       thread = new Thread(this);

    // Anfang Komponenten
    // Ende Komponenten
  }

  // Anfang Methoden
  
  public void start(Spiel spiel)
  {
	  
	  this.spiel = spiel;
       this.setVisible(true);
       cursorAnzeigen();
       menuLaden();
  }

   public void playSound()
  {
       try{
            if(lautstaerke)
            {
                audioInputStream = AudioSystem.getAudioInputStream(sound);
                af = audioInputStream.getFormat();
                size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
                audio = new byte[size];
                info = new DataLine.Info(Clip.class, af, size);
                audioInputStream.read(audio, 0, size);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(af, audio, 0, size);
                clip.start();
            }
            
        }catch(Exception e){ e.printStackTrace(); }
  }
  
   public void playClickSound()
  {
      try{
           if(lautstaerke)
            {
                audioInputStream = AudioSystem.getAudioInputStream(tastenSound);
                af = audioInputStream.getFormat();
                size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
                audio = new byte[size];
                info = new DataLine.Info(Clip.class, af, size);
                audioInputStream.read(audio, 0, size);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(af, audio, 0, size);
                clip.start();
            }

        }catch(Exception e){ e.printStackTrace(); }
  }
  
  public void playEnterSound()
  {
      try{
             if(lautstaerke)
            {
              audioInputStream = AudioSystem.getAudioInputStream(tastenSound2);
              af = audioInputStream.getFormat();
              size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
              audio = new byte[size];
              info = new DataLine.Info(Clip.class, af, size);
              audioInputStream.read(audio, 0, size);
              clip = (Clip) AudioSystem.getLine(info);
              clip.open(af, audio, 0, size);
              clip.start();
             }

        }catch(Exception e){ e.printStackTrace(); }
  }
  
  public void playCpuSound()
  {
      try{
            if(lautstaerke)
            {
                audioInputStream = AudioSystem.getAudioInputStream(cpuSound);
                af = audioInputStream.getFormat();
                size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
                audio = new byte[size];
                info = new DataLine.Info(Clip.class, af, size);
                audioInputStream.read(audio, 0, size);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(af, audio, 0, size);
                clip.start();
            }

        }catch(Exception e){ e.printStackTrace(); }
  }
  
  
  
  public void cursorAnzeigen()
  {
          final Image cursorImage = new ImageIcon("data/kreide.png").getImage();
          final Point hotspot = new Point(0, 0);
          final String name = "My Cursor";
          this.setCursor(getToolkit().createCustomCursor(cursorImage, hotspot, name));

  }
  
  public void menuLaden()
  {
     try
     {
//       thread.sleep(500);
//       playSound();
//       x1.setText("T");
//       x2.setText(" I");
//       x3.setText("C");
//       thread.sleep(400);
//       playCpuSound();
//       x4.setText("T");
//       x5.setText("A");
//       x6.setText("C");
//       thread.sleep(400);
//       playSound();
//       x7.setText("T");
//       x8.setText("O");
//       x9.setText("E");
//       thread.sleep(300);
       
       
       x1.setVisible(false);
       x2.setVisible(false);
       x3.setVisible(false);
       x4.setVisible(false);
       x5.setVisible(false);
       x6.setVisible(false);
       x7.setVisible(false);
       x8.setVisible(false);
       x9.setVisible(false);

       x1.setText("X");
       x2.setText("X");
       x3.setText("X");
       x4.setText("X");
       x5.setText("X");
       x6.setText("X");
       x7.setText("X");
       x8.setText("X");
       x9.setText("X");
       
       hauptmenu2.setVisible(true);
       start.setVisible(true);
       ende.setVisible(true);
     }
     
     catch(Exception e)
    {   System.out.println("FEHLER");
     }

  }
  
  
  public void cursorAusblenden()
  {
          final Image cursorImage = new ImageIcon("handd.png").getImage();
          final Point hotspot = new Point(0, 0);
          final String name = "My Cursor";
          this.setCursor(getToolkit().createCustomCursor(cursorImage, hotspot, name));
  }

  public void run()
  {
    try
    {
        if(los)
        {
           los();
         }
         

        else if(!gewonnen)
        {
           if(veryEasy)
           {
              zufallSetzten();
           }
           else if(easy)
           {
              verteidigen();
           }
           else if(hard)
           {
             cpuGewinnt();
           }
         }
         
         else
         {
           putzen();
         }
         
         

     }
     
     catch(Exception e)
    {   System.out.println("FEHLER");
     }
  }
  
  public void cpuGewinnt()
  {
    try
    {
       cpuPlayed = false;

         if(!pressedT1)
         {
           o1.setVisible(false);
         }

         if(!pressedT2)
         {
           o2.setVisible(false);
         }

         if(!pressedT3)
         {
           o3.setVisible(false);
         }

         if(!pressedT4)
         {
           o4.setVisible(false);
         }

         if(!pressedT5)
         {
           o5.setVisible(false);
         }

         if(!pressedT6)
         {
           o6.setVisible(false);
         }

         if(!pressedT7)
         {
           o7.setVisible(false);
         }

         if(!pressedT8)
         {
           o8.setVisible(false);
         }

         if(!pressedT9)
         {
           o9.setVisible(false);
         }

       if(pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5 && pressedT6 && pressedT7 && pressedT8 && pressedT9)
        {
          System.out.println("Fertig");
           stop();
           putzen();
           thread = new Thread(this);

        }

      else if(cpufeld1 && cpufeld2 && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT3 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld1 && cpufeld3 && !pressedT2 && !cpuPlayed)
      {
         thread.sleep(500);
         x2.setText("X");
         x2.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT2 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld1 && cpufeld4 && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld1 && cpufeld7 && !pressedT4 && !cpuPlayed)
      {
         thread.sleep(500);
         x4.setText("X");
         x4.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT4 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld2 && cpufeld3 && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld1 && cpufeld5 && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld1 && cpufeld9 && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld2 && cpufeld5 && !pressedT8 && !cpuPlayed)
      {
         thread.sleep(500);
         x8.setText("X");
         x8.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT8 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld2 && cpufeld8 && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld3 && cpufeld5 && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld3 && cpufeld7 && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld3 && cpufeld6 && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld3 && cpufeld9 && !pressedT6 && !cpuPlayed)
      {
         thread.sleep(500);
         x6.setText("X");
         x6.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT6 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld4 && cpufeld7 && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld4 && cpufeld5 && !pressedT6 && !cpuPlayed)
      {
         thread.sleep(500);
         x6.setText("X");
         x6.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT6 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld4 && cpufeld6 && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld5 && cpufeld6 && !pressedT4 && !cpuPlayed)
      {
         thread.sleep(500);
         x4.setText("X");
         x4.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT4 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld5 && cpufeld9 && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld5 && cpufeld8 && !pressedT2 && !cpuPlayed)
      {
         thread.sleep(500);
         x2.setText("X");
         x2.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT2 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld6 && cpufeld9 && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT3 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld7 && cpufeld8 && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld7 && cpufeld5 && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         pressedT3 = true;
         playCpuSound();
         cpuPlayed = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld7 && cpufeld9 && !pressedT8 && !cpuPlayed)
      {
         thread.sleep(500);
         x8.setText("X");
         x8.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT8 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else if(cpufeld8 && cpufeld9 && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         gewonnen = true;
         endText = "COMPUTER GEWINNT";
         putzen();
      }

      else
      {
        cpuPlayed = false;
        verteidigen();
      }
      

    }
            catch(Exception e)
            {   System.out.println("FEHLER");
             }
  }

  

  public void verteidigen()
  {
    try
    {
       cpuPlayed = false;
       
         if(!pressedT1)
         {
           o1.setVisible(false);
         }

         if(!pressedT2)
         {
           o2.setVisible(false);
         }

         if(!pressedT3)
         {
           o3.setVisible(false);
         }

         if(!pressedT4)
         {
           o4.setVisible(false);
         }

         if(!pressedT5)
         {
           o5.setVisible(false);
         }

         if(!pressedT6)
         {
           o6.setVisible(false);
         }

         if(!pressedT7)
         {
           o7.setVisible(false);
         }

         if(!pressedT8)
         {
           o8.setVisible(false);
         }

         if(!pressedT9)
         {
           o9.setVisible(false);
         }

       if(pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5 && pressedT6 && pressedT7 && pressedT8 && pressedT9)
        {
          System.out.println("Fertig");
           putzen();
           stop();
           thread = new Thread(this);

        }

      else if(eins && zwei && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         playCpuSound();
         cpufeld3 = true;
         cpuPlayed = true;
         pressedT3 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(eins && drei && !pressedT2 && !cpuPlayed)
      {
         thread.sleep(500);
         x2.setText("X");
         x2.setVisible(true);
         playCpuSound();
         cpufeld2 = true;
         cpuPlayed = true;
         pressedT2 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(eins && vier && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpufeld7 = true;
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(eins && sieben && !pressedT4 && !cpuPlayed)
      {
         thread.sleep(500);
         x4.setText("X");
         x4.setVisible(true);
         playCpuSound();
         cpufeld4 = true;
         cpuPlayed = true;
         pressedT4 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(zwei && drei && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpufeld1 = true;
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(eins && fünf && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpufeld9 = true;
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(eins && neun && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpufeld5 = true;
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(zwei && fünf && !pressedT8 && !cpuPlayed)
      {
         thread.sleep(500);
         x8.setText("X");
         x8.setVisible(true);
         playCpuSound();
         cpufeld8 = true;
         cpuPlayed = true;
         pressedT8 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(zwei && acht && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpufeld5 = true;
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(drei && fünf && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpufeld7 = true;
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(drei && sieben && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpufeld5 = true;
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(drei && sechs && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpufeld9 = true;
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(drei && neun && !pressedT6 && !cpuPlayed)
      {
         thread.sleep(500);
         x6.setText("X");
         x6.setVisible(true);
         playCpuSound();
         cpufeld6 = true;
         cpuPlayed = true;
         pressedT6 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(vier && sieben && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpufeld1 = true;
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(vier && fünf && !pressedT6 && !cpuPlayed)
      {
         thread.sleep(500);
         x6.setText("X");
         x6.setVisible(true);
         playCpuSound();
         cpufeld6 = true;
         cpuPlayed = true;
         pressedT6 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(vier && sechs && !pressedT5 && !cpuPlayed)
      {
         thread.sleep(500);
         x5.setText("X");
         x5.setVisible(true);
         playCpuSound();
         cpufeld5 = true;
         cpuPlayed = true;
         pressedT5 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(fünf && sechs && !pressedT4 && !cpuPlayed)
      {
         thread.sleep(500);
         x4.setText("X");
         x4.setVisible(true);
         playCpuSound();
         cpufeld4 = true;
         cpuPlayed = true;
         pressedT4 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(fünf && neun && !pressedT1 && !cpuPlayed)
      {
         thread.sleep(500);
         x1.setText("X");
         x1.setVisible(true);
         playCpuSound();
         cpufeld1 = true;
         cpuPlayed = true;
         pressedT1 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(fünf && acht && !pressedT2 && !cpuPlayed)
      {
         thread.sleep(500);
         x2.setText("X");
         x2.setVisible(true);
         playCpuSound();
         cpufeld2 = true;
         cpuPlayed = true;
         pressedT2 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(sechs && neun && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         playCpuSound();
         cpufeld3 = true;
         cpuPlayed = true;
         pressedT3 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(sieben && acht && !pressedT9 && !cpuPlayed)
      {
         thread.sleep(500);
         x9.setText("X");
         x9.setVisible(true);
         playCpuSound();
         cpufeld9 = true;
         cpuPlayed = true;
         pressedT9 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(sieben && fünf && !pressedT3 && !cpuPlayed)
      {
         thread.sleep(500);
         x3.setText("X");
         x3.setVisible(true);
         playCpuSound();
         cpufeld3 = true;
         cpuPlayed = true;
         pressedT3 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(sieben && neun && !pressedT8 && !cpuPlayed)
      {
         thread.sleep(500);
         x8.setText("X");
         x8.setVisible(true);
         playCpuSound();
         cpufeld8 = true;
         cpuPlayed = true;
         pressedT8 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else if(acht && neun && !pressedT7 && !cpuPlayed)
      {
         thread.sleep(500);
         x7.setText("X");
         x7.setVisible(true);
         playCpuSound();
         cpufeld7 = true;
         cpuPlayed = true;
         pressedT7 = true;
         thread.sleep(10);
         spielerDarf();
      }

      else
      {
        cpuPlayed = false;
        zufallSetzten();
      }

    }
            catch(Exception e)
            {   System.out.println("FEHLER");
             }
  }


   public void zufallSetzten()
   {
         Random zufallsgenerator;
         zufallsgenerator = new Random();

         zufallsZahl = felder[zufallsgenerator.nextInt(9)];

         System.out.println(zufallsZahl);
         zufallSpielen();
    }


    public void zufallSpielen()
    {
     try
      {
         if(!pressedT1)
         {
           o1.setVisible(false);
         }

         if(!pressedT2)
         {
           o2.setVisible(false);
         }

         if(!pressedT3)
         {
           o3.setVisible(false);
         }

         if(!pressedT4)
         {
           o4.setVisible(false);
         }

         if(!pressedT5)
         {
           o5.setVisible(false);
         }

         if(!pressedT6)
         {
           o6.setVisible(false);
         }

         if(!pressedT7)
         {
           o7.setVisible(false);
         }

         if(!pressedT8)
         {
           o8.setVisible(false);
         }

         if(!pressedT9)
         {
           o9.setVisible(false);
         }

          if(pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5 && pressedT6 && pressedT7 && pressedT8 && pressedT9)
            {
              System.out.println("Fertig");
               putzen();
               stop();
               thread = new Thread(this);

            }
            
        else
         {

            if(zufallsZahl == 1 && !pressedT1)
            {
              thread.sleep(500);
              x1.setVisible(true);
              playCpuSound();
              pressedT1 = true;
              cpufeld1 = true;
              thread.sleep(10);
              spielerDarf();
            }

            else if(zufallsZahl == 2 && !pressedT2)
            {
               thread.sleep(500);
               x2.setVisible(true);
               playCpuSound();
               pressedT2 = true;
               cpufeld2 = true;
               thread.sleep(10);
               spielerDarf();
            }

           else if(zufallsZahl== 3 && !pressedT3)
            {
               thread.sleep(500);
               x3.setVisible(true);
               playCpuSound();
               pressedT3 = true;
               cpufeld3 = true;
               thread.sleep(10);
               spielerDarf();
            }

           else if(zufallsZahl== 4 && !pressedT4)
            {
               thread.sleep(500);
               x4.setVisible(true);
               playCpuSound();
               pressedT4 = true;
               cpufeld4 = true;
               thread.sleep(10);
               spielerDarf();
            }

           else if(zufallsZahl == 5 && !pressedT5)
            {
               thread.sleep(500);
               x5.setVisible(true);
               playCpuSound();
               pressedT5 = true;
               cpufeld5 = true;
               thread.sleep(10);
               spielerDarf();

            }

            else if(zufallsZahl == 6 && !pressedT6)
            {
               thread.sleep(500);
               x6.setVisible(true);
               playCpuSound();
               pressedT6 = true;
               cpufeld6 = true;
               thread.sleep(10);
               spielerDarf();
            }

            else if(zufallsZahl == 7 && !pressedT7)
            {
               thread.sleep(500);
               x7.setVisible(true);
               playCpuSound();
               pressedT7 = true;
               cpufeld7 = true;
               thread.sleep(10);
               spielerDarf();
            }

            else if(zufallsZahl == 8 && !pressedT8)
            {
               thread.sleep(500);
               x8.setVisible(true);
               playCpuSound();
               pressedT8 = true;
               cpufeld8 = true;
               thread.sleep(10);
               spielerDarf();
            }

            else if(zufallsZahl == 9 && !pressedT9)
            {
               thread.sleep(500);
               x9.setVisible(true);
               playCpuSound();
               pressedT9 = true;
               cpufeld9 = true;
               thread.sleep(10);
               spielerDarf();
            }
            

            else
            {
               stop();
               thread = new Thread(this);
               zufallSetzten();

            }
            

                stop();
               thread = new Thread(this);
         }


      }
       catch(Exception e)
      {   System.out.println("FEHLER");
       }
    }
    
    public void werHatGewonnen()
    {

        if(eins && zwei && drei)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(eins && vier && sieben)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(eins && fünf && neun)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }

        else if(zwei && fünf && acht)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(drei && fünf && sieben)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(drei && sechs && neun)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(vier && fünf && sechs)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
        else if(sieben && acht && neun)
        {
          gewonnen = true;
          endText = "PLAYER GEWINNT";
        }
        
         else if(cpufeld1 && cpufeld2 && cpufeld3)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld1 && cpufeld4 && cpufeld7)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld1 && cpufeld5 && cpufeld9)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld2 && cpufeld5 && cpufeld8)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld3 && cpufeld5 && cpufeld7)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld3 && cpufeld6 && cpufeld9)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld4 && cpufeld5 && cpufeld6)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }

        else if(cpufeld7 && cpufeld8 && cpufeld9)
        {
          gewonnen = true;
          endText = "COMPUTER GEWINNT";
        }
        
        else
        {
          gewonnen = false;
        }
    }
    
    public void los()
    {
      try
      {
          o1.setVisible(false);
          o2.setVisible(false);
          o3.setVisible(false);
          o4.setVisible(false);
          o5.setVisible(false);
          o6.setVisible(false);
          o7.setVisible(false);
          o8.setVisible(false);
          o9.setVisible(false);

          x4.setText("L");
          x4.setVisible(true);
          thread.sleep(500);
          x5.setText("O");
          x5.setVisible(true);
          thread.sleep(500);
          x6.setText("S!");
          x6.setVisible(true);
          thread.sleep(500);
          
          x4.setVisible(false);
          x5.setVisible(false);
          x6.setVisible(false);
          x4.setText("X");
          x5.setText("X");
          x6.setText("X");
          
          o1.setVisible(true);
          o2.setVisible(true);
          o3.setVisible(true);
          o4.setVisible(true);
          o5.setVisible(true);
          o6.setVisible(true);
          o7.setVisible(true);
          o8.setVisible(true);
          o9.setVisible(true);
          
          los = false;
          stop();
          thread = new Thread(this);
      }

       catch(Exception e)
      {   System.out.println("FEHLER");
       }
    }
    
    public void spielerDarf()
    {
          werHatGewonnen();
           if(!gewonnen)
           {
                if(pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5 && pressedT6 && pressedT7 && pressedT8 && pressedT9)
                {
                  System.out.println("Fertig");

                   putzen();
                   stop();
                   thread = new Thread(this);


                }

                else
                {
                     stop();
                    thread = new Thread(this);
                    o1.setVisible(true);
                    o2.setVisible(true);
                    o3.setVisible(true);
                    o4.setVisible(true);
                    o5.setVisible(true);
                    o6.setVisible(true);
                    o7.setVisible(true);
                    o8.setVisible(true);
                    o9.setVisible(true);
                }
           }
           else
           {
             gewonnen = false;
             putzen();
           }



    }

    public synchronized void stop()
    {
      if (thread != null)
     {
        thread = null;
     }

    }

     public void putzen()
    {
       try
         {
               hauptmenu.setVisible(false);
               thread.sleep(1000);
//             thread.sleep(40);
//             o4.setVisible(false);
//             x4.setVisible(false);
//
//             int i = -100;
//             while(i < 280)
//             {
//                schw.setLocation(i, 90);
//                thread.sleep(2);
//                if(i == 30)
//                {
//
//                  o5.setVisible(false);
//                  x5.setVisible(false);
//                  hin1.setVisible(true);
//                }
//
//                if(i == 140)
//                {
//                  hin1.setVisible(false);
//                  o6.setVisible(false);
//                  x6.setVisible(false);
//                  hin2.setVisible(true);
//                  text.setVisible(true);
//                }
//
//                i++;
//             }
//             stop();
//             thread = new Thread(this);
           System.out.println(endText);
           thread.sleep(2000);
           hauptmenu.setVisible(true);
           nochmal();

         }

         catch(Exception e)
        { System.out.println("FEHLER");
         }
    }
    
    public void nochmal()
    {
       o1.setVisible(false);
       o2.setVisible(false);
       o3.setVisible(false);
       o4.setVisible(false);
       o5.setVisible(false);
       o6.setVisible(false);
       o7.setVisible(false);
       o8.setVisible(false);
       o9.setVisible(false);
       
       x1.setVisible(false);
       x2.setVisible(false);
       x3.setVisible(false);
       x4.setVisible(false);
       x5.setVisible(false);
       x6.setVisible(false);
       x7.setVisible(false);
       x8.setVisible(false);
       x9.setVisible(false);
       
       o1.setText(" ");
       o2.setText(" ");
       o3.setText(" ");
       o4.setText(" ");
       o5.setText(" ");
       o6.setText(" ");
       o7.setText(" ");
       o8.setText(" ");
       o9.setText(" ");
       
       pressedT1 = false;
       pressedT2 = false;
       pressedT3 = false;
       pressedT4 = false;
       pressedT5 = false;
       pressedT6 = false;
       pressedT7 = false;
       pressedT8 = false;
       pressedT9 = false;
       
       eins = false;
       zwei = false;
       drei = false;
       vier = false;
       fünf = false;
       sechs = false;
       sieben = false;
       acht = false;
       neun = false;

       cpufeld1 = false;
       cpufeld2 = false;
       cpufeld3 = false;
       cpufeld4 = false;
       cpufeld5 = false;
       cpufeld6 = false;
       cpufeld7 = false;
       cpufeld8 = false;
       cpufeld9 = false;
       
       gewonnen = false;

       plcpu.setVisible(true);
       cpupl.setVisible(true);
       
       stop();
       thread = new Thread(this);
    }


  public void mouseClicked(MouseEvent event)
  {
      try{

              if(event.getSource() == soundTaste)
              {
                lautstaerke = true;
                soundTaste.setVisible(false);
                soundTaste2.setVisible(true);
              }
              
               if(event.getSource() == soundTaste2)
              {
                lautstaerke = false;
                soundTaste2.setVisible(false);
                soundTaste.setVisible(true);
              }

              if(event.getSource() == einfach)
             {
                   playClickSound();
                   schwierigkeit.setVisible(false);
                   einfach.setVisible(false);
                   normal.setVisible(false);
                   schwer.setVisible(false);
                   plcpu.setVisible(true);
                   cpupl.setVisible(true);
                   veryEasy = true;
                   easy = false;
                   hard = false;

             }


              if(event.getSource() == normal)
             {
                   playClickSound();
                   schwierigkeit.setVisible(false);
                   einfach.setVisible(false);
                   normal.setVisible(false);
                   schwer.setVisible(false);
                   plcpu.setVisible(true);
                   cpupl.setVisible(true);
                   veryEasy = false;
                   easy = true;
                   hard = false;

             }
             
              if(event.getSource() == schwer)
             {
                   playClickSound();
                   schwierigkeit.setVisible(false);
                   einfach.setVisible(false);
                   normal.setVisible(false);
                   schwer.setVisible(false);
                   plcpu.setVisible(true);
                   cpupl.setVisible(true);
                   hard = true;
                   veryEasy = false;
                   easy = false;

             }
             

              if(event.getSource() == start)
             {
                   playClickSound();
                   start.setVisible(false);
                   start2.setVisible(false);
                   ende.setVisible(false);
                   ende2.setVisible(false);
                   hauptmenu2.setVisible(false);
                   schwierigkeit.setVisible(true);
                   einfach.setVisible(true);
                   normal.setVisible(true);
                   schwer.setVisible(true);
                   hauptmenu.setVisible(true);

             }
             
             if(event.getSource() == plcpu)
             {
                 playClickSound();
                 plcpu.setVisible(false);
                 cpupl.setVisible(false);
                 los = true;
                 gewonnen = false;
                 endText = "AUSGLEICH";
                 thread.start();
                 spielerDarf();
             }
             
              if(event.getSource() == cpupl)
             {
                 playClickSound();
                 plcpu.setVisible(false);
                 cpupl.setVisible(false);
                 gewonnen = false;
                 endText = "AUSGLEICH";
                 thread.start();
             }
             
             if(event.getSource() == ende)
             {
                   playClickSound();
//                   System.exit(0);
                   this.setVisible(false);
                   Information[] infos = new Information[1];
                   infos[0] = new Information(Information.AENDERN_SOZIALES, Information.ART_UM_WERT, 50);
                   spiel.minispielEnde(infos);
             }
             
             if(event.getSource() == hauptmenu)
             {
                   playClickSound();
                   nochmal();
                   schwierigkeit.setVisible(false);
                   einfach.setVisible(false);
                   normal.setVisible(false);
                   schwer.setVisible(false);
                   plcpu.setVisible(false);
                   cpupl.setVisible(false);
                   hauptmenu2.setVisible(true);
                   hauptmenu.setVisible(false);
                   start.setVisible(true);
                   ende.setVisible(true);
             }


              if(event.getSource() == o1 && !pressedT1)
            {
                     pressedT1 = true;
                     eins = true;
                     o1.setText("O");
                     playSound();
                     werHatGewonnen();
                     thread.start();
             }

               if(event.getSource() == o2 && !pressedT2)
            {
                      pressedT2 = true;
                      zwei = true;
                      o2.setText("O");
                      playSound();
                      werHatGewonnen();
                      thread.start();
             }

               if(event.getSource() == o3 && !pressedT3)
            {
                      pressedT3 = true;
                      drei = true;
                      o3.setText("O");
                      playSound();
                      werHatGewonnen();
                      thread.start();
             }

               if(event.getSource() == o4 && !pressedT4)
            {
                      pressedT4 = true;
                      vier = true;
                      o4.setText("O");
                      playSound();
                      werHatGewonnen();
                      thread.start();
             }

               if(event.getSource() == o5 && !pressedT5)
            {
                    pressedT5 = true;
                    fünf = true;
                    o5.setText("O");
                    playSound();
                    werHatGewonnen();
                    thread.start();
             }

             if(event.getSource() == o6 && !pressedT6)
            {
                    pressedT6 = true;
                    sechs = true;
                    o6.setText("O");
                    playSound();
                    werHatGewonnen();
                    thread.start();
             }

             if(event.getSource() == o7 && !pressedT7)
            {
                   pressedT7 = true;
                   sieben = true;
                   o7.setText("O");
                   playSound();
                   werHatGewonnen();
                   thread.start();
             }

             if(event.getSource() == o8 && !pressedT8)
            {
                    pressedT8 = true;
                    acht = true;
                    o8.setText("O");
                    playSound();
                    werHatGewonnen();
                    thread.start();
             }

             if(event.getSource() == o9 && !pressedT9)
            {
                   pressedT9 = true;
                   neun = true;
                   o9.setText("O");
                   playSound();
                   werHatGewonnen();
                   thread.start();
             }
        }

         catch(Exception e)
        { System.out.println("FEHLER");
         }
  }
  


  public void mousePressed(MouseEvent event)
  {

  }

  public void mouseReleased(MouseEvent event)
  {

  }

  public void mouseEntered(MouseEvent event)
  {
       if(event.getSource() == start)
       {
             playEnterSound();
             start2.setVisible(true);
       }
       
       if(event.getSource() == ende)
       {
             playEnterSound();
             ende2.setVisible(true);
       }
       
       if(event.getSource() == hauptmenu)
       {
         playEnterSound();
         hauptmenu.setLocation(370, 300);
         hauptmenu.setFont(new Font("Times New Roman", Font.PLAIN, 30));
       }
       
        if(event.getSource() == plcpu)
       {
         playEnterSound();
         plcpu.setLocation(390, 100);
         plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       }
       
        if(event.getSource() == cpupl)
       {
         playEnterSound();
         cpupl.setLocation(390, 150);
         cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       }
       
       if(event.getSource() == einfach)
        {
            playEnterSound();
            einfach.setLocation(405, 125);
            einfach.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        }

        if(event.getSource() == normal)
        {
            playEnterSound();
            normal.setLocation(405, 175);
            normal.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        }
        
        if(event.getSource() == schwer)
        {
            playEnterSound();
            schwer.setLocation(405, 225);
            schwer.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        }
       
  }


  public void mouseExited(MouseEvent event)
  {
      if(event.getSource() == start)
       {
             start2.setVisible(false);
       }
       
       if(event.getSource() == ende)
       {
             ende2.setVisible(false);
             ende.setVisible(true);
       }
       
       if(event.getSource() == hauptmenu)
       {
         hauptmenu.setLocation(380, 300);
         hauptmenu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
       }
       
        if(event.getSource() == plcpu)
       {
         plcpu.setLocation(400, 100);
         plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       }

        if(event.getSource() == cpupl)
       {
         cpupl.setLocation(400, 150);
         cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       }
       
       if(event.getSource() == einfach)
        {
            einfach.setLocation(415, 125);
            einfach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
       
       if(event.getSource() == normal)
        {
            normal.setLocation(415, 175);
            normal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }

        if(event.getSource() == schwer)
        {
            schwer.setLocation(415, 225);
            schwer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
  }

   public void actionPerformed(ActionEvent event)
   {



   }

//  public static void main(String Args[])
//    {
//         new Spiel();
//    }
  // Ende Methoden

}