package Game.GUI;

import javax.swing.*;

import java.awt.event.*;
import Game.*;

public class CharakterGUI extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 5;
	private Avatar avatar;
	private JLabel name, bild;
	private JTextPane beschreibung;
	private JButton wahl;
   
   public CharakterGUI(Avatar avatar, int x, int y)
   {
      this.setSize(320, 500);
      this.setLocation(x, y);
      this.setBackground(null);
      this.setLayout(null);
      
      name = new JLabel(avatar.getName());
      name.setSize(300, 50);
      name.setLocation(20, 0);
      name.setHorizontalAlignment(0);
      this.add(name);
      
      bild = new JLabel(new ImageIcon("files/avatarImages/" + avatar.getName() + ".jpg"));
      bild.setSize(200, 200);
      bild.setLocation(60, 50);
      this.add(bild);
      
      beschreibung = new JTextPane();
      beschreibung.setBackground(null);
      beschreibung.setText(avatar.getBeschreibung());
      beschreibung.setSize(250, 150);
      beschreibung.setLocation(35, 270);
      beschreibung.setEditable(false);
      beschreibung.setCaretPosition(0);
      this.add(beschreibung);
      
      wahl = new JButton("Spiele " + name.getText());
      wahl.setSize(300, 50);
      wahl.setLocation(15, 450);
      wahl.setHorizontalAlignment(0);
      wahl.addActionListener(this);
      this.add(wahl);
      
      this.avatar = avatar;
      
      this.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e)   {
	   if(e.getSource() == wahl) {
		   SpielAnwendung.starteSpiel(getAvatar());
	   }
   }
   
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
}