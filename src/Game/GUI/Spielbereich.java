package Game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Spielbereich extends JPanel implements MouseListener
{

	private JLabel lblbackGround;
	
	
	public Spielbereich() {
		lblbackGround = new JLabel(new ImageIcon("files/gameImages/bg.jpg"));
		lblbackGround.setSize(800, 500);
		lblbackGround.setLocation(0,0);
		this.add(lblbackGround);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
