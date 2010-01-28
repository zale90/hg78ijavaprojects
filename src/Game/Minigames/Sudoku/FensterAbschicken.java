package Game.Minigames.Sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FensterAbschicken extends JFrame implements ActionListener,
		MouseListener {

	private static final long serialVersionUID = 2587911838065744279L;

	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JButton btn1;

	public FensterAbschicken() {
		super("Sudoku");
		this.setSize(350, 220);
		this.setLocation(300, 100);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);

		Icon bild = new ImageIcon(
				"files/minigames/Sudoku/sudokuBildFensterAbschicken.jpg");
		lbl1 = new JLabel(bild);
		lbl1.setSize(350, 250);
		this.add(lbl1);

		lbl2 = new JLabel("Herzlichen Glückwunsch!");
		lbl2.setSize(240, 40);
		lbl2.setLocation(60, 15);
		lbl2.setFont(new Font("Comoic Sans", Font.BOLD, 18));
		lbl2.setForeground(new Color(140, 220, 225));
		lbl1.add(lbl2);

		lbl3 = new JLabel("Sie haben das Sudoku komplett richtig gelöst!!");
		lbl3.setSize(290, 20);
		lbl3.setLocation(40, 80);
		lbl3.setFont(new Font("Comoic Sans", Font.BOLD, 12));
		lbl3.setForeground(Color.white);
		lbl1.add(lbl3);

		btn1 = new JButton("OK");
		btn1.setSize(150, 80);
		btn1.setLocation(100, 110);
		btn1.setFont(new Font("Comic Sans", Font.BOLD, 30));
		btn1.setBorderPainted(false);
		btn1.setBackground(Color.lightGray);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		btn1.setOpaque(false);
		btn1.setForeground(new Color(140, 220, 225));
		lbl1.add(btn1);
		btn1.addActionListener(this);
		btn1.addMouseListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btn1) {
			new Senden(null, null);
			this.dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btn1) {
			btn1.setForeground(new Color(20, 180, 225));
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btn1) {
			btn1.setForeground(new Color(140, 220, 225));
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}