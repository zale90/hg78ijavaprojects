package Game.GUI;

import javax.swing.*;
import java.awt.event.*;
import Game.*;

public class CharakterGUI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5;
   private Avatar avatar;
   private JLabel name, bild;
   private JTextArea beschreibung;
   private JButton wahl;
   
   public CharakterGUI(Avatar avatar, int x, int y)
   {
      this.setSize(320, 500);
      this.setLocation(x, y);
      this.setLayout(null);
      
      name = new JLabel("Horst Terarno");
      name.setSize(300, 50);
      name.setLocation(20, 20);
      name.setHorizontalAlignment(0);
      
      bild = new JLabel();
      bild.setSize(200, 200);
      bild.setLocation(60, 90);
      bild.setIcon(new ImageIcon("test"));
      
      beschreibung = new JTextArea();
      beschreibung.setSize(250, 100);
      beschreibung.setLocation(30, 300);
      
      wahl = new JButton("Spiele " + name.getText());
      wahl.setSize(300, 50);
      wahl.setLocation(20, 420);
      wahl.setHorizontalAlignment(0);
      wahl.addActionListener(this);
      
      this.setAvatar(avatar);
   }
   public void actionPerformed(ActionEvent e)
   {

   }
public void setAvatar(Avatar avatar) {
	this.avatar = avatar;
}
public Avatar getAvatar() {
	return avatar;
}
}