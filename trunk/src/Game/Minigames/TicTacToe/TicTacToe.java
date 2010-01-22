package Game.Minigames.TicTacToe;

import javax.sound.sampled.*;
import java.io.*;
import java.awt.*;
import Game.Minigames.Minispiel;
import Game.Spiel;
import Game.Information;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class TicTacToe extends JFrame implements Runnable, ActionListener,
      MouseListener, Minispiel {

   private static final long serialVersionUID = 8548600932891426745L;
   private ImageIcon hintergrund, hintergrund1,
         hintergrund2, schwamm;
   private JLabel hin, hin1, hin2, start, start2, ende, ende2, text, schw, x1, x2, x3, x4, x5, x6, x7, x8, x9, o1, o2, o3, o4, o5, o6,
         o7, o8, o9, cpupl, plcpu, hauptmenu, hauptmenu2, schwierigkeit,
         einfach, normal, schwer, soundTaste, soundTaste2, statusText,
         strichSenk, strichWag, strich1_9, strich3_7, level, cpu, pl, draw;

   private Thread thread;
   private int felder[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
   private int fünfFelder[] = { 1, 3, 5, 7, 9 };
   private int zufallsZahl;
   private String endText;
   private String X = "X";
   private String O = "O";
   private int gewinn = 0;
   private int verlust = 0;
   private int ausgleich = 0;
   private int versuche = 0;
   private Random zufallsgenerator;

   private Spiel spiel;
   private String name;

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

   boolean cpuPlayed = false;

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

   public TicTacToe() {
      super("Tic Tac Toe");
      this.setSize(600, 400);
      this.setLocation(400, 200);
      this.setLayout(null);
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      this.setAlwaysOnTop(true);
      this.setUndecorated(true);

      this.setResizable(false);
      this.setBackground(new Color(0, 10, 0));
      this.setForeground(new Color(100, 10, 5));

      ImageIcon soundIcon = new ImageIcon("files/minigames/TicTacToe/sound.png");
      soundTaste = new JLabel(soundIcon);
      soundTaste.setSize(30, 29);
      soundTaste.setLocation(555, 360);
      soundTaste.setVisible(false);
      this.add(soundTaste);
      soundTaste.addMouseListener(this);

      ImageIcon soundIcon2 = new ImageIcon("files/minigames/TicTacToe/sound2.png");
      soundTaste2 = new JLabel(soundIcon2);
      soundTaste2.setSize(30, 29);
      soundTaste2.setLocation(555, 360);
      soundTaste2.setVisible(true);
      this.add(soundTaste2);
      soundTaste2.addMouseListener(this);

      hintergrund = new ImageIcon("files/minigames/TicTacToe/hintergrund.png");
      hin = new JLabel(hintergrund);
      hin.setLocation(0, 0);
      hin.setSize(hintergrund.getIconWidth(), hintergrund.getIconHeight());

      this.setIconImage(hintergrund.getImage());

      schwamm = new ImageIcon("files/minigames/TicTacToe/schwamm.png");
      schw = new JLabel(schwamm);
      schw.setLocation(-120, 160);
      schw.setSize(schwamm.getIconWidth(), schwamm.getIconHeight());
      schw.setVisible(true);
      this.add(schw);

      text = new JLabel(" ");
      text.setSize(200, 76);
      text.setLocation(70, 175); // zeigt wer am ende gewonnen hat!!
      text.setFont(new Font("Times New Roman", Font.PLAIN, 12));
      text.setForeground(Color.WHITE);
      text.setVisible(false);
      this.add(text);

      level = new JLabel("");
      level.setSize(200, 76);
      level.setLocation(415, 25);
      level.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      level.setForeground(Color.WHITE);
      level.setEnabled(false);
      level.setVisible(false);
      this.add(level);

      hintergrund1 = new ImageIcon("files/minigames/TicTacToe/wisch1.png");
      hin1 = new JLabel(hintergrund1);
      hin1.setLocation(1, 122);
      hin1.setSize(hintergrund1.getIconWidth(), hintergrund1.getIconHeight());
      hin1.setVisible(false);
      this.add(hin1);

      hintergrund2 = new ImageIcon("files/minigames/TicTacToe/wisch2.png");
      hin2 = new JLabel(hintergrund2);
      hin2.setLocation(1, 122);
      hin2.setSize(hintergrund2.getIconWidth(), hintergrund2.getIconHeight());
      hin2.setVisible(false);
      this.add(hin2);

      ImageIcon startButton = new ImageIcon("files/minigames/TicTacToe/start.png");
      start = new JLabel(startButton);
      start.setLocation(400, 160);
      start.setSize(startButton.getIconWidth(), startButton.getIconHeight());
      start.setVisible(false);
      this.add(start);
      start.addMouseListener(this);

      ImageIcon startButton2 = new ImageIcon("files/minigames/TicTacToe/start2.png");
      start2 = new JLabel(startButton2);
      start2.setLocation(400, 160);
      start2.setSize(startButton.getIconWidth(), startButton.getIconHeight());
      start2.setVisible(false);
      this.add(start2);
      start2.addMouseListener(this);

      ImageIcon endeButton = new ImageIcon("files/minigames/TicTacToe/ende.png");
      ende = new JLabel(endeButton);
      ende.setLocation(380, 250);
      ende.setSize(endeButton.getIconWidth(), endeButton.getIconHeight());
      ende.setVisible(false);
      this.add(ende);
      ende.addMouseListener(this);

      ImageIcon endeButton2 = new ImageIcon("files/minigames/TicTacToe/ende2.png");
      ende2 = new JLabel(endeButton2);
      ende2.setLocation(380, 250);
      ende2.setSize(endeButton.getIconWidth(), endeButton.getIconHeight());
      ende2.setVisible(false);
      this.add(ende2);
      ende2.addMouseListener(this);

      ImageIcon strichSeknrecht = new ImageIcon("files/minigames/TicTacToe/strichSenk.png");
      strichSenk = new JLabel(strichSeknrecht);
      strichSenk.setLocation(190, 40);
      strichSenk.setSize(strichSeknrecht.getIconWidth(), strichSeknrecht.getIconHeight());
      strichSenk.setVisible(false);
      this.add(strichSenk);

      ImageIcon strichWaagerecht = new ImageIcon("files/minigames/TicTacToe/strichWag.png");
      strichWag = new JLabel(strichWaagerecht);
      strichWag.setLocation(80, 265);
      strichWag.setSize(strichWaagerecht.getIconWidth(), strichWaagerecht.getIconHeight());
      strichWag.setVisible(false);
      this.add(strichWag);

      ImageIcon strichEinsBisNeun = new ImageIcon("files/minigames/TicTacToe/strich1-9.png");
      strich1_9 = new JLabel(strichEinsBisNeun);
      strich1_9.setLocation(-80, -100);
      strich1_9.setSize(strichEinsBisNeun.getIconWidth(), strichEinsBisNeun.getIconHeight());
      strich1_9.setVisible(false);
      this.add(strich1_9);

      ImageIcon strichDreiBisSieben = new ImageIcon("files/minigames/TicTacToe/strich3-7.png");
      strich3_7 = new JLabel(strichDreiBisSieben);
      strich3_7.setLocation(-135, -85);
      strich3_7.setSize(strichDreiBisSieben.getIconWidth(),strichDreiBisSieben.getIconHeight());
      strich3_7.setVisible(false);
      this.add(strich3_7);

      plcpu = new JLabel(name + " - FREUND");
      plcpu.setSize(200, 76);
      plcpu.setLocation(400, 100);
      plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      plcpu.setForeground(Color.WHITE);
      plcpu.setVisible(false);
      this.add(plcpu);
      plcpu.addMouseListener(this);

      cpupl = new JLabel("FREUND - " + name);
      cpupl.setSize(200, 76);
      cpupl.setLocation(400, 150);
      cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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
      hauptmenu2.setLocation(360, 50); // hauptmenü(nur Text)
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

      schwer = new JLabel("  ULTRA");
      schwer.setSize(200, 76);
      schwer.setLocation(415, 225);
      schwer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      schwer.setForeground(Color.WHITE);
      schwer.setVisible(false);
      this.add(schwer);
      schwer.addMouseListener(this);

      statusText = new JLabel(" ");
      statusText.setSize(200, 70);
      statusText.setLocation(125, -10);
      statusText.setFont(new Font("Times New Roman", Font.PLAIN, 19));
      statusText.setForeground(Color.WHITE);
      statusText.setVisible(true);
      this.add(statusText);

      cpu = new JLabel("FREUND: " + verlust);
      cpu.setSize(200, 50);
      cpu.setLocation(400, 100);
      cpu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      cpu.setForeground(Color.WHITE);
      cpu.setEnabled(false);
      cpu.setVisible(false);
      this.add(cpu);

      pl = new JLabel(name + ": " + gewinn);
      pl.setSize(200, 50);
      pl.setLocation(400, 75);
      pl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      pl.setForeground(Color.WHITE);
      pl.setEnabled(false);
      pl.setVisible(false);
      this.add(pl);

      draw = new JLabel("UNENTSCHIEDEN: " + ausgleich);
      draw.setSize(200, 50);
      draw.setLocation(400, 125);
      draw.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      draw.setForeground(Color.WHITE);
      draw.setEnabled(false);
      draw.setVisible(false);
      this.add(draw);

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
      o3.setLocation(235, 25);
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

      sound = new File("files/minigames/TicTacToe/playerSound.wav");
      tastenSound = new File("files/minigames/TicTacToe/tasten.wav");
      tastenSound2 = new File("files/minigames/TicTacToe/tastenEnter.wav");
      cpuSound = new File("files/minigames/TicTacToe/cpuSound.wav");

      this.add(hin);

      thread = new Thread(this);
      zufallsgenerator = new Random();

   }

   public void start(Spiel spiel) {
      this.spiel = spiel;
      name = spiel.getAvatarName();
      plcpu.setText(name + " - FREUND");
      cpupl.setText("FREUND - " + name);
      cpu.setText("FREUND: " + verlust);
      pl.setText(name + ": " + gewinn);
      this.setVisible(true);
      cursorAnzeigen();
      menuLaden();
   }

   public void playSound() {
      try {
         if (lautstaerke) {
            audioInputStream = AudioSystem.getAudioInputStream(sound);
            af = audioInputStream.getFormat();
            size = (int) (af.getFrameSize() * audioInputStream
                  .getFrameLength());
            audio = new byte[size];
            info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void playClickSound() {
      try {
         if (lautstaerke) {
            audioInputStream = AudioSystem.getAudioInputStream(tastenSound);
            af = audioInputStream.getFormat();
            size = (int) (af.getFrameSize() * audioInputStream
                  .getFrameLength());
            audio = new byte[size];
            info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void playEnterSound() {
      try {
         if (lautstaerke) {
            audioInputStream = AudioSystem
                  .getAudioInputStream(tastenSound2);
            af = audioInputStream.getFormat();
            size = (int) (af.getFrameSize() * audioInputStream
                  .getFrameLength());
            audio = new byte[size];
            info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void playCpuSound() {
      try {
         if (lautstaerke) {
            audioInputStream = AudioSystem.getAudioInputStream(cpuSound);
            af = audioInputStream.getFormat();
            size = (int) (af.getFrameSize() * audioInputStream
                  .getFrameLength());
            audio = new byte[size];
            info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            clip.start();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void cursorAnzeigen() {
      final Image cursorImage = new ImageIcon(
            "files/minigames/TicTacToe/kreide.png").getImage();
      final Point hotspot = new Point(0, 0);
      final String name = "My Cursor";
      this.setCursor(getToolkit().createCustomCursor(cursorImage, hotspot,
            name));
   }

   public void menuLaden() {
      try {
         Thread.sleep(500);
         playSound();
         x1.setText("T");
         x2.setText(" I");
         x3.setText("C");
         Thread.sleep(400);
         playCpuSound();
         x4.setText("T");
         x5.setText("A");
         x6.setText("C");
         Thread.sleep(400);
         playSound();
         x7.setText("T");
         x8.setText("O");
         x9.setText("E");
         Thread.sleep(300);

         x1.setVisible(false);
         x2.setVisible(false);
         x3.setVisible(false);
         x4.setVisible(false);
         x5.setVisible(false);
         x6.setVisible(false);
         x7.setVisible(false);
         x8.setVisible(false);
         x9.setVisible(false);

         x1.setText(O);
         x2.setText(O);
         x3.setText(O);
         x4.setText(O);
         x5.setText(O);
         x6.setText(O);
         x7.setText(O);
         x8.setText(O);
         x9.setText(O);

         hauptmenu2.setVisible(true);
         start.setVisible(true);
         ende.setVisible(true);
      }

      catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void cursorAusblenden() {
      final Image cursorImage = new ImageIcon("handd.png").getImage();
      final Point hotspot = new Point(0, 0);
      final String name = "My Cursor";
      this.setCursor(getToolkit().createCustomCursor(cursorImage, hotspot,
            name));
   }

   public void run() {
      hauptmenu.setVisible(false);
      try {
         zufallsZahl = fünfFelder[zufallsgenerator.nextInt(5)];

         if (los) {
            statusText.setText("");
            los();
            statusText.setText(name + " (" + O + ")");
         }

         else if (!gewonnen) {
            statusText.setText("FREUND (" + X + ")");
            Thread.sleep(250);
            if (veryEasy) {
               if (zufallsZahl == 1 || zufallsZahl == 5) {
                  cpuGewinnt();
               } else {
                  verteidigen();
               }

            } else if (easy) {
               cpuGewinnt();
            } else if (hard) {
               cpuGewinnt();
            }
         }

         else {
            statusText.setText("");
            putzen();
         }

      }

      catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void cpuGewinnt() {
      try {
         cpuPlayed = false;

         if (!pressedT1) {
            o1.setVisible(false);
         }

         if (!pressedT2) {
            o2.setVisible(false);
         }

         if (!pressedT3) {
            o3.setVisible(false);
         }

         if (!pressedT4) {
            o4.setVisible(false);
         }

         if (!pressedT5) {
            o5.setVisible(false);
         }

         if (!pressedT6) {
            o6.setVisible(false);
         }

         if (!pressedT7) {
            o7.setVisible(false);
         }

         if (!pressedT8) {
            o8.setVisible(false);
         }

         if (!pressedT9) {
            o9.setVisible(false);
         }

         if (pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5
               && pressedT6 && pressedT7 && pressedT8 && pressedT9) {
            statusText.setText("");
            stop();
            putzen();
            thread = new Thread(this);
         }

         else if (cpufeld1 && cpufeld2 && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld3 = true;
            pressedT3 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld1 && cpufeld3 && !pressedT2 && !cpuPlayed) {
            Thread.sleep(500);
            x2.setText(X);
            x2.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld2 = true;
            pressedT2 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld1 && cpufeld4 && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld7 = true;
            pressedT7 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld1 && cpufeld7 && !pressedT4 && !cpuPlayed) {
            Thread.sleep(500);
            x4.setText(X);
            x4.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld4 = true;
            pressedT4 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld2 && cpufeld3 && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld1 = true;
            pressedT1 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld1 && cpufeld5 && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld9 = true;
            pressedT9 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld1 && cpufeld9 && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld5 = true;
            pressedT5 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld2 && cpufeld5 && !pressedT8 && !cpuPlayed) {
            Thread.sleep(500);
            x8.setText(X);
            x8.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld8 = true;
            pressedT8 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld2 && cpufeld8 && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld5 = true;
            pressedT5 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld3 && cpufeld5 && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld7 = true;
            pressedT7 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld3 && cpufeld7 && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld5 = true;
            pressedT5 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld3 && cpufeld6 && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld9 = true;
            pressedT9 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld3 && cpufeld9 && !pressedT6 && !cpuPlayed) {
            Thread.sleep(500);
            x6.setText(X);
            x6.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld6 = true;
            pressedT6 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld4 && cpufeld7 && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld1 = true;
            pressedT1 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld4 && cpufeld5 && !pressedT6 && !cpuPlayed) {
            Thread.sleep(500);
            x6.setText(X);
            x6.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld6 = true;
            pressedT6 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld4 && cpufeld6 && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld5 = true;
            pressedT5 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld5 && cpufeld6 && !pressedT4 && !cpuPlayed) {
            Thread.sleep(500);
            x4.setText(X);
            x4.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld4 = true;
            pressedT4 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld5 && cpufeld9 && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld1 = true;
            pressedT1 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld5 && cpufeld8 && !pressedT2 && !cpuPlayed) {
            Thread.sleep(500);
            x2.setText(X);
            x2.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld2 = true;
            pressedT2 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld6 && cpufeld9 && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld3 = true;
            pressedT3 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld7 && cpufeld8 && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld9 = true;
            pressedT9 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld7 && cpufeld5 && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            pressedT3 = true;
            cpufeld3 = true;
            playCpuSound();
            cpuPlayed = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld7 && cpufeld9 && !pressedT8 && !cpuPlayed) {
            Thread.sleep(500);
            x8.setText(X);
            x8.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld8 = true;
            pressedT8 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else if (cpufeld8 && cpufeld9 && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpuPlayed = true;
            cpufeld7 = true;
            pressedT7 = true;
            Thread.sleep(10);
            gewonnen = true;
            endText = "FREUND GEWINNT";
            verlust++;
            putzen();
         }

         else {
            cpuPlayed = false;
            if (veryEasy) {
               zufallSetzten();
            }

            else {
               verteidigen();
            }

         }

      } catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void verteidigen() {
      try {
         cpuPlayed = false;

         if (!pressedT1) {
            o1.setVisible(false);
         }

         if (!pressedT2) {
            o2.setVisible(false);
         }

         if (!pressedT3) {
            o3.setVisible(false);
         }

         if (!pressedT4) {
            o4.setVisible(false);
         }

         if (!pressedT5) {
            o5.setVisible(false);
         }

         if (!pressedT6) {
            o6.setVisible(false);
         }

         if (!pressedT7) {
            o7.setVisible(false);
         }

         if (!pressedT8) {
            o8.setVisible(false);
         }

         if (!pressedT9) {
            o9.setVisible(false);
         }

         if (pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5
               && pressedT6 && pressedT7 && pressedT8 && pressedT9) {
            statusText.setText("");
            putzen();
            stop();
            thread = new Thread(this);
         }

         else if (eins && zwei && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            playCpuSound();
            cpufeld3 = true;
            cpuPlayed = true;
            pressedT3 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (eins && drei && !pressedT2 && !cpuPlayed) {
            Thread.sleep(500);
            x2.setText(X);
            x2.setVisible(true);
            playCpuSound();
            cpufeld2 = true;
            cpuPlayed = true;
            pressedT2 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (eins && vier && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpufeld7 = true;
            cpuPlayed = true;
            pressedT7 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (eins && sieben && !pressedT4 && !cpuPlayed) {
            Thread.sleep(500);
            x4.setText(X);
            x4.setVisible(true);
            playCpuSound();
            cpufeld4 = true;
            cpuPlayed = true;
            pressedT4 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (zwei && drei && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpufeld1 = true;
            cpuPlayed = true;
            pressedT1 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (eins && fünf && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpufeld9 = true;
            cpuPlayed = true;
            pressedT9 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (eins && neun && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpufeld5 = true;
            cpuPlayed = true;
            pressedT5 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (zwei && fünf && !pressedT8 && !cpuPlayed) {
            Thread.sleep(500);
            x8.setText(X);
            x8.setVisible(true);
            playCpuSound();
            cpufeld8 = true;
            cpuPlayed = true;
            pressedT8 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (zwei && acht && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpufeld5 = true;
            cpuPlayed = true;
            pressedT5 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (drei && fünf && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpufeld7 = true;
            cpuPlayed = true;
            pressedT7 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (drei && sieben && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpufeld5 = true;
            cpuPlayed = true;
            pressedT5 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (drei && sechs && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpufeld9 = true;
            cpuPlayed = true;
            pressedT9 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (drei && neun && !pressedT6 && !cpuPlayed) {
            Thread.sleep(500);
            x6.setText(X);
            x6.setVisible(true);
            playCpuSound();
            cpufeld6 = true;
            cpuPlayed = true;
            pressedT6 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (vier && sieben && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpufeld1 = true;
            cpuPlayed = true;
            pressedT1 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (vier && fünf && !pressedT6 && !cpuPlayed) {
            Thread.sleep(500);
            x6.setText(X);
            x6.setVisible(true);
            playCpuSound();
            cpufeld6 = true;
            cpuPlayed = true;
            pressedT6 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (vier && sechs && !pressedT5 && !cpuPlayed) {
            Thread.sleep(500);
            x5.setText(X);
            x5.setVisible(true);
            playCpuSound();
            cpufeld5 = true;
            cpuPlayed = true;
            pressedT5 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (fünf && sechs && !pressedT4 && !cpuPlayed) {
            Thread.sleep(500);
            x4.setText(X);
            x4.setVisible(true);
            playCpuSound();
            cpufeld4 = true;
            cpuPlayed = true;
            pressedT4 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (fünf && neun && !pressedT1 && !cpuPlayed) {
            Thread.sleep(500);
            x1.setText(X);
            x1.setVisible(true);
            playCpuSound();
            cpufeld1 = true;
            cpuPlayed = true;
            pressedT1 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (fünf && acht && !pressedT2 && !cpuPlayed) {
            Thread.sleep(500);
            x2.setText(X);
            x2.setVisible(true);
            playCpuSound();
            cpufeld2 = true;
            cpuPlayed = true;
            pressedT2 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (sechs && neun && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            playCpuSound();
            cpufeld3 = true;
            cpuPlayed = true;
            pressedT3 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (sieben && acht && !pressedT9 && !cpuPlayed) {
            Thread.sleep(500);
            x9.setText(X);
            x9.setVisible(true);
            playCpuSound();
            cpufeld9 = true;
            cpuPlayed = true;
            pressedT9 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (sieben && fünf && !pressedT3 && !cpuPlayed) {
            Thread.sleep(500);
            x3.setText(X);
            x3.setVisible(true);
            playCpuSound();
            cpufeld3 = true;
            cpuPlayed = true;
            pressedT3 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (sieben && neun && !pressedT8 && !cpuPlayed) {
            Thread.sleep(500);
            x8.setText(X);
            x8.setVisible(true);
            playCpuSound();
            cpufeld8 = true;
            cpuPlayed = true;
            pressedT8 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else if (acht && neun && !pressedT7 && !cpuPlayed) {
            Thread.sleep(500);
            x7.setText(X);
            x7.setVisible(true);
            playCpuSound();
            cpufeld7 = true;
            cpuPlayed = true;
            pressedT7 = true;
            Thread.sleep(10);
            spielerDarf();
         }

         else {
            cpuPlayed = false;
            zufallSetzten();
         }

      } catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void zufallSetzten() {
      zufallsZahl = felder[zufallsgenerator.nextInt(9)];
      zufallSpielen();
   }

   public void zufallSpielen() {
      try {
         if (!pressedT1) {
            o1.setVisible(false);
         }

         if (!pressedT2) {
            o2.setVisible(false);
         }

         if (!pressedT3) {
            o3.setVisible(false);
         }

         if (!pressedT4) {
            o4.setVisible(false);
         }

         if (!pressedT5) {
            o5.setVisible(false);
         }

         if (!pressedT6) {
            o6.setVisible(false);
         }

         if (!pressedT7) {
            o7.setVisible(false);
         }

         if (!pressedT8) {
            o8.setVisible(false);
         }

         if (!pressedT9) {
            o9.setVisible(false);
         }

         if (pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5
               && pressedT6 && pressedT7 && pressedT8 && pressedT9) {
            statusText.setText("");
            putzen();
            stop();
            thread = new Thread(this);

         }

         else {
            if (!pressedT1 && !pressedT2 && !pressedT3 && !pressedT4
                  && !pressedT5 && !pressedT6 && !pressedT7 && !pressedT8
                  && !pressedT9 && hard) {
               zufallsZahl = fünfFelder[zufallsgenerator.nextInt(5)];
            }

            if (zufallsZahl == 1 && !pressedT1) {
               Thread.sleep(500);
               x1.setText(X);
               x1.setVisible(true);
               playCpuSound();
               pressedT1 = true;
               cpufeld1 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 2 && !pressedT2) {
               Thread.sleep(500);
               x2.setText(X);
               x2.setVisible(true);
               playCpuSound();
               pressedT2 = true;
               cpufeld2 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 3 && !pressedT3) {
               Thread.sleep(500);
               x3.setText(X);
               x3.setVisible(true);
               playCpuSound();
               pressedT3 = true;
               cpufeld3 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 4 && !pressedT4) {
               Thread.sleep(500);
               x4.setText(X);
               x4.setVisible(true);
               playCpuSound();
               pressedT4 = true;
               cpufeld4 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 5 && !pressedT5) {
               Thread.sleep(500);
               x5.setText(X);
               x5.setVisible(true);
               playCpuSound();
               pressedT5 = true;
               cpufeld5 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 6 && !pressedT6) {
               Thread.sleep(500);
               x6.setText(X);
               x6.setVisible(true);
               playCpuSound();
               pressedT6 = true;
               cpufeld6 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 7 && !pressedT7) {
               Thread.sleep(500);
               x7.setText(X);
               x7.setVisible(true);
               playCpuSound();
               pressedT7 = true;
               cpufeld7 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 8 && !pressedT8) {
               Thread.sleep(500);
               x8.setText(X);
               x8.setVisible(true);
               playCpuSound();
               pressedT8 = true;
               cpufeld8 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else if (zufallsZahl == 9 && !pressedT9) {
               Thread.sleep(500);
               x9.setText(X);
               x9.setVisible(true);
               playCpuSound();
               pressedT9 = true;
               cpufeld9 = true;
               Thread.sleep(10);
               spielerDarf();
            }

            else {
               stop();
               thread = new Thread(this);
               zufallSetzten();
            }
            stop();
            thread = new Thread(this);
         }

      } catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void werHatGewonnen() {

      if (eins && zwei && drei) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (eins && vier && sieben) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (eins && fünf && neun) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (zwei && fünf && acht) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (drei && fünf && sieben) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (drei && sechs && neun) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (vier && fünf && sechs) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (sieben && acht && neun) {
         gewonnen = true;
         endText = name + " GEWINNT";
         gewinn++;
      }

      else if (cpufeld1 && cpufeld2 && cpufeld3) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld1 && cpufeld4 && cpufeld7) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld1 && cpufeld5 && cpufeld9) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld2 && cpufeld5 && cpufeld8) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld3 && cpufeld5 && cpufeld7) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld3 && cpufeld6 && cpufeld9) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld4 && cpufeld5 && cpufeld6) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (cpufeld7 && cpufeld8 && cpufeld9) {
         gewonnen = true;
         endText = "FREUND GEWINNT";
         verlust++;
      }

      else if (pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5
            && pressedT6 && pressedT7 && pressedT8 && pressedT9
            && !gewonnen) {
         ausgleich++;
      }

      else {
         gewonnen = false;
      }
   }

   public void los() {
      try {
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
         Thread.sleep(100);
         x5.setText("O");
         x5.setVisible(true);
         Thread.sleep(100);
         x6.setText("S!");
         x6.setVisible(true);
         Thread.sleep(250);

         x4.setVisible(false);
         x5.setVisible(false);
         x6.setVisible(false);
         x4.setText(X);
         x5.setText(X);
         x6.setText(X);

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

      catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void spielerDarf() {
      hauptmenu.setVisible(false);
      werHatGewonnen();
      if (!gewonnen) {
         if (pressedT1 && pressedT2 && pressedT3 && pressedT4 && pressedT5
               && pressedT6 && pressedT7 && pressedT8 && pressedT9) {
            putzen();
            stop();
            thread = new Thread(this);
         }

         else {
            stop();
            thread = new Thread(this);
            statusText.setText(name + " (" + O + ")");

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
      } else {
         gewonnen = false;
         putzen();
      }

   }

   public synchronized void stop() {
      if (thread != null) {
         thread = null;
      }
   }

   public void putzen() {
      try {
         statusText.setText("");
         text.setText("");
         hauptmenu.setVisible(false);
         Thread.sleep(500);
         strichZiehen();
         Thread.sleep(500);
         schw.setVisible(true);

         int i = -100;
         while (i < 280) {
            schw.setLocation(i, 184);
            Thread.sleep(1);
            if (i == 50) {
               hin1.setVisible(true);
            }

            if (i == 160) {
               hin2.setVisible(true);
               text.setVisible(true);
            }

            i++;
         }
         text.setText(endText);
         Thread.sleep(1);
         text.setVisible(true);
         Thread.sleep(1500);
         strichSenk.setVisible(false);
         strichWag.setVisible(false);
         strich1_9.setVisible(false);
         strich3_7.setVisible(false);
         text.setVisible(false);
         hin1.setVisible(false);
         hin2.setVisible(false);
         schw.setVisible(false);
         hauptmenu.setVisible(true);
         nochmal();
      }

      catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void strichZiehen() {
      if ((eins && vier && sieben) || (cpufeld1 && cpufeld4 && cpufeld7)) {
         strichSenk.setLocation(-10, 60);
         strichSenk.setVisible(true);
      }

      if ((eins && fünf && neun) || (cpufeld1 && cpufeld5 && cpufeld9)) {
         strich1_9.setLocation(-80, -100);
         strich1_9.setVisible(true);
      }

      else if ((eins && zwei && drei) || (cpufeld1 && cpufeld2 && cpufeld3)) {
         strichWag.setLocation(35, 55);
         strichWag.setVisible(true);
      }

      if ((zwei && fünf && acht) || (cpufeld2 && cpufeld5 && cpufeld8)) {
         strichSenk.setLocation(95, 50);
         strichSenk.setVisible(true);
      }

      else if ((drei && sechs && neun) || (cpufeld3 && cpufeld6 && cpufeld9)) {
         strichSenk.setLocation(190, 40);
         strichSenk.setVisible(true);
      }

      if ((drei && fünf && sieben) || (cpufeld3 && cpufeld5 && cpufeld7)) {
         strich3_7.setLocation(-135, -85);
         strich3_7.setVisible(true);
      }

      if ((vier && fünf && sechs) || (cpufeld4 && cpufeld5 && cpufeld6)) {
         strichWag.setLocation(60, 160);
         strichWag.setVisible(true);
      }

      if ((sieben && acht && neun) || (cpufeld7 && cpufeld8 && cpufeld9)) {
         strichWag.setLocation(80, 265);
         strichWag.setVisible(true);
      }
   }

   public void neuStarten() {
      ende.setVisible(false);
      start.setVisible(false);
      start2.setVisible(false);

      gewinn = 0;
      verlust = 0;
      ausgleich = 0;
      versuche = 0;

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

   }

   public void nochmal() {
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

      level.setVisible(false);
      cpu.setVisible(false);
      pl.setVisible(false);
      draw.setVisible(false);

      cpu.setText("FREUND: " + verlust);
      pl.setText("SPIELER: " + gewinn);
      draw.setText("UNENTSCHIEDEN: " + ausgleich);

      plcpu.setVisible(true);
      cpupl.setVisible(true);

      stop();
      thread = new Thread(this);
   }

   public void mouseClicked(MouseEvent event) {
      try {

         if (event.getSource() == soundTaste) {
            lautstaerke = true;
            soundTaste.setVisible(false);
            soundTaste2.setVisible(true);
         }

         if (event.getSource() == soundTaste2) {
            lautstaerke = false;
            soundTaste2.setVisible(false);
            soundTaste.setVisible(true);
         }

         if (event.getSource() == einfach) {
            playClickSound();
            schwierigkeit.setVisible(false);
            einfach.setVisible(false);
            normal.setVisible(false);
            schwer.setVisible(false);
            level.setText("EINFACH");
            plcpu.setVisible(true);
            cpupl.setVisible(true);
            veryEasy = true;
            easy = false;
            hard = false;
         }

         if (event.getSource() == normal) {
            playClickSound();
            schwierigkeit.setVisible(false);
            einfach.setVisible(false);
            normal.setVisible(false);
            schwer.setVisible(false);
            level.setText("NORMAL");
            plcpu.setVisible(true);
            cpupl.setVisible(true);
            veryEasy = false;
            easy = true;
            hard = false;
         }

         if (event.getSource() == schwer) {
            playClickSound();
            schwierigkeit.setVisible(false);
            einfach.setVisible(false);
            normal.setVisible(false);
            schwer.setVisible(false);
            level.setText("ULTRA");
            plcpu.setVisible(true);
            cpupl.setVisible(true);
            hard = true;
            veryEasy = false;
            easy = false;
         }

         if (event.getSource() == start) {
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

         if (event.getSource() == plcpu) {
            playClickSound();
            O = "X";
            X = "O";
            versuche++;
            plcpu.setVisible(false);
            cpupl.setVisible(false);
            level.setVisible(true);
            cpu.setVisible(true);
            pl.setVisible(true);
            draw.setVisible(true);
            los = true;
            gewonnen = false;
            endText = "UNENTSCHIEDEN";
            thread.start();
            spielerDarf();
         }

         if (event.getSource() == cpupl) {
            playClickSound();
            O = "O";
            X = "X";
            versuche++;
            plcpu.setVisible(false);
            level.setVisible(true);
            cpupl.setVisible(false);
            cpu.setVisible(true);
            pl.setVisible(true);
            draw.setVisible(true);
            gewonnen = false;
            endText = "UNENTSCHIEDEN";
            thread.start();
         }

         if (event.getSource() == ende) {
            playClickSound();
            Information[] infos = new Information[versuche];
            for (int i = 0; i < infos.length; i++)
               infos[i] = new Information(Information.AENDERN_SOZIALES,
                     Information.ART_UM_WERT, 5);
            spiel.minispielEnde(infos);
            neuStarten();
            this.setVisible(false);
         }

         if (event.getSource() == hauptmenu) {
            playClickSound();
            nochmal();
            statusText.setText("");
            schwierigkeit.setVisible(false);
            einfach.setVisible(false);
            normal.setVisible(false);
            schwer.setVisible(false);
            plcpu.setVisible(false);
            cpupl.setVisible(false);
            level.setVisible(false);
            hauptmenu2.setVisible(true);
            hauptmenu.setVisible(false);
            start.setVisible(true);
            ende.setVisible(true);
         }

         if (event.getSource() == o1 && !pressedT1) {
            pressedT1 = true;
            eins = true;
            o1.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o2 && !pressedT2) {
            pressedT2 = true;
            zwei = true;
            o2.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o3 && !pressedT3) {
            pressedT3 = true;
            drei = true;
            o3.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o4 && !pressedT4) {
            pressedT4 = true;
            vier = true;
            o4.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o5 && !pressedT5) {
            pressedT5 = true;
            fünf = true;
            o5.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o6 && !pressedT6) {
            pressedT6 = true;
            sechs = true;
            o6.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o7 && !pressedT7) {
            pressedT7 = true;
            sieben = true;
            o7.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o8 && !pressedT8) {
            pressedT8 = true;
            acht = true;
            o8.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }

         if (event.getSource() == o9 && !pressedT9) {
            pressedT9 = true;
            neun = true;
            o9.setText(O);
            playSound();
            werHatGewonnen();
            thread.start();
         }
      }

      catch (Exception e) {
         System.out.println("FEHLER");
      }
   }

   public void mousePressed(MouseEvent event) {

   }

   public void mouseReleased(MouseEvent event) {

   }

   public void mouseEntered(MouseEvent event) {
      if (event.getSource() == start) {
         playEnterSound();
         start2.setVisible(true);
      }

      if (event.getSource() == ende) {
         playEnterSound();
         ende2.setVisible(true);
      }

      if (event.getSource() == hauptmenu) {
         playEnterSound();
         hauptmenu.setLocation(370, 300);
         hauptmenu.setFont(new Font("Times New Roman", Font.PLAIN, 30));
      }

      if (event.getSource() == plcpu) {
         playEnterSound();
         plcpu.setLocation(390, 100);
         plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      }

      if (event.getSource() == cpupl) {
         playEnterSound();
         cpupl.setLocation(390, 150);
         cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
      }

      if (event.getSource() == einfach) {
         playEnterSound();
         einfach.setLocation(405, 125);
         einfach.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      }

      if (event.getSource() == normal) {
         playEnterSound();
         normal.setLocation(405, 175);
         normal.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      }

      if (event.getSource() == schwer) {
         playEnterSound();
         schwer.setLocation(405, 225);
         schwer.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      }

   }

   public void mouseExited(MouseEvent event) {
      if (event.getSource() == start) {
         start2.setVisible(false);
      }

      if (event.getSource() == ende) {
         ende2.setVisible(false);
         ende.setVisible(true);
      }

      if (event.getSource() == hauptmenu) {
         hauptmenu.setLocation(380, 300);
         hauptmenu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      }

      if (event.getSource() == plcpu) {
         plcpu.setLocation(400, 100);
         plcpu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
      }

      if (event.getSource() == cpupl) {
         cpupl.setLocation(400, 150);
         cpupl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
      }

      if (event.getSource() == einfach) {
         einfach.setLocation(415, 125);
         einfach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      }

      if (event.getSource() == normal) {
         normal.setLocation(415, 175);
         normal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      }

      if (event.getSource() == schwer) {
         schwer.setLocation(415, 225);
         schwer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      }
   }

   public void actionPerformed(ActionEvent event) {

   }

   public static void main(String Args[]) {
      new TicTacToe();
   }

}