package paket;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class LabelGarage extends JLabel {

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD, 26));
		g.drawString("Name:", 25, 90);
		
		g.drawString("Größe:", 25, 200);
		
		String tempSliderGroesseString = String.valueOf(ActionHandler.tempSliderGroesse);
		g.drawString(tempSliderGroesseString, 433, 205);
		
		g.drawString("Preis:", 25, 320);
		
		String tempSliderPreisString = String.valueOf(ActionHandler.tempSliderPreis);
		g.drawString(tempSliderPreisString + " €", 424, 320);
		
		repaint();
	}

}

