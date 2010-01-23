package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Write a description of class FensterAbschicken here.
 *
 * @version 22.01.2010
 */
public class FensterFehler extends JFrame implements ActionListener,MouseListener
{
private JLabel lbl1;
private JLabel lbl2;
private JLabel lbl3;
private JButton btn1;
private JLabel lblVersuch;
private static int i;
private int a;






 public FensterFehler(String test)
   {
      super("Sudoku") ;
      this.setSize(350,220);
      this.setLocation(330,150);
      this.setLayout(null);
      this.setAlwaysOnTop(true);

      Icon bild = new ImageIcon("files/minigames/Sudoku/sudokuBildFensterAbschicken.jpg");
      lbl1 = new JLabel(bild);
      lbl1.setSize(350,250);
      this.add(lbl1);


      lbl2 = new JLabel("Das Sudoku wurde nicht richtig gelöst!");
      lbl2.setSize(240,40);
      lbl2.setLocation(60,15);
      lbl2.setFont(new Font("Comoic Sans", Font.BOLD,12));
      lbl2.setForeground(new Color(140,220,225));
      lbl1.add(lbl2);

      lbl3 = new JLabel("Tipp: Überprüfen Sie doch zunächst einmal Feld" + " " + test);
      lbl3.setSize(290,20);
      lbl3.setLocation(40,80);
      lbl3.setFont(new Font("Comic Sans", Font.BOLD,12));
      lbl3.setForeground(Color.white);
      lbl1.add(lbl3);

      runterzählen();

      lblVersuch = new JLabel("Verbleibende Versuche:" + " " + a);
      lblVersuch.setSize(290,20);
      lblVersuch.setLocation(40,100);
      lblVersuch.setFont(new Font("Comic Sans", Font.ITALIC,12));
      lblVersuch.setForeground(Color.white);
      lbl1.add(lblVersuch);

      btn1 = new JButton("OK");
      btn1.setSize(150,80);
      btn1.setLocation(100,110);
      btn1.setFont(new Font("Comic Sans", Font.BOLD,30));
      btn1.setBorderPainted(false);
      btn1.setBackground(Color.lightGray);
      btn1.setOpaque(false);
      btn1.setContentAreaFilled(false);
      btn1.setFocusPainted(false);
      btn1.setForeground(new Color(140,220,225));
      lbl1.add(btn1);
      btn1.addActionListener(this);
      btn1.addMouseListener(this);





      this.setVisible(true);
    }




       public void actionPerformed(ActionEvent evt)
       {
         if(evt.getSource() == btn1)
        {
            if(a != 0)
            {
                i++;
                this.dispose();
            }
            else
            {
               this.dispose();
               new FensterVerloren();
            }
        }

       }

       private void runterzählen()
       {
           a = 0;
           a=5-i;
       }


        public void mouseClicked(MouseEvent e)
     {
          //Invoked when the mouse button has been clicked (pressed and released) on a component.
     }

     public void mouseEntered(MouseEvent e)
     {
          //Invoked when the mouse enters a component.
          if(e.getSource() == btn1)
        {
             btn1.setForeground(new Color(20,180,225));
        }


     }

     public void mouseExited(MouseEvent e)
     {
     //     Invoked when the mouse exits a component.
          if(e.getSource() == btn1)
          {
             btn1.setForeground(new Color(140,220,225));
          }

        }

        public void mousePressed(MouseEvent e)
        {
            //Invoked when a mouse button has been pressed on a component.
        }

        public void mouseReleased(MouseEvent e)
        {
             //Invoked when a mouse button has been released on a component.
        }

}
