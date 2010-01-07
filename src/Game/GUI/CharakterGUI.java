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
	private JScrollPane beschreibungScrollbalken;
   
   public CharakterGUI(Avatar avatar, int x, int y)
   {
      this.setSize(320, 500);
      this.setLocation(x, y);
      this.setBackground(null);
      this.setLayout(null);
      
      name = new JLabel(avatar.getName());
      name.setSize(300, 50);
      name.setLocation(20, 20);
      name.setHorizontalAlignment(0);
      this.add(name);
      
      bild = new JLabel(new ImageIcon("files/avatarImages/" + avatar.getName() + ".jpg"));
      bild.setSize(200, 200);
      bild.setLocation(60, 90);
      this.add(bild);
      
      beschreibung = new JTextPane();
      beschreibung.setEditable(false);
      beschreibung.setSize(250, 100);
      beschreibung.setLocation(30, 300);
//      beschreibung.setWrapStyleWord(true);
//      beschreibung.setLineWrap(true);
      beschreibung.setText(avatar.getBeschreibung());
      this.add(beschreibung);
      
      beschreibungScrollbalken = new JScrollPane(beschreibung);
      beschreibungScrollbalken.setSize(250, 100);
      beschreibungScrollbalken.setLocation(30, 300);
      beschreibungScrollbalken.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      this.add(beschreibungScrollbalken);
      
      wahl = new JButton("Spiele " + name.getText());
      wahl.setSize(300, 50);
      wahl.setLocation(20, 420);
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