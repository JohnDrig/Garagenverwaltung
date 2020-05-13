package paket;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class LabelKunde extends JLabel {

	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (ActionHandlerKunde.tempKundeErstellen == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.drawString("Name:", 25, 90);

			g.drawString("Alter:", 25, 203);

			String tempSliderAlterString = String.valueOf(ActionHandlerKunde.tempSliderAlter);
			g.drawString(tempSliderAlterString, 433, 205);

			g.drawString("Garage buchen:", 25, 320);

			g.drawString("Monat:", 25, 430);
		}

		if (ActionHandlerKunde.tempKundeBezahlen == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.drawString("Kunde:", 25, 100);

			if (ActionHandlerKunde.tempRichtigKunde == 1) {
				String tempsliderBezahlenString = String.valueOf(ActionHandlerKunde.tempsliderBezahlen);
				g.setFont(new Font("Arial", Font.BOLD, 29));
				g.drawString(tempsliderBezahlenString + " €", 340, 284);
			}

		}

		repaint();
	}

}

