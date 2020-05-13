package paket;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.Scanner;

import javax.swing.JLabel;

public class Label extends JLabel {

	private String anzahlKunden, anzahlGaragen;
	private Gui gui;

	private static final long serialVersionUID = 1L;

	// Konstruktor
	public Label(Gui gui) {
		this.gui = gui;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (Gui.saveIndexTab == 0 || Gui.saveIndexTab == 1 || Gui.saveIndexTab == 2) {

			// soll auf allen Panels gemalt werden:
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.WHITE);
			g.drawString("Garagenvermietung", 300, 70);

			g.setColor(Color.ORANGE);

			gd.setStroke(new BasicStroke(4));
			gd.draw(new Line2D.Float(30, 90, 950, 90));

			// g.drawLine(30, 90, 950, 90);

		}

		// diese soll nur auf bestimmten Panels gemalt werden:
		if (Gui.saveIndexTab == 0) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("Garagen:", 360, 170);

			g.drawString("Kunden:", 360, 490);

			g.setColor(Color.ORANGE);
			gd.setStroke(new BasicStroke(1));
			g.drawLine(40, 435, 960, 435);
		}

		if (Gui.saveIndexTab == 1) {
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.setColor(Color.WHITE);

			// hier wird der Gesamtumsatz geladen, wenn es schon einen gibt, daher muss die
			// Datei gelesen werden, wo der schon errungende Umsatz hingeschrieben wird
			File file = new File(Speicherung.pathUmsatz);
			Speicherung speicherung = new Speicherung();

			if (file.exists()) {
				Scanner scanner = null;
				String umsatzSaveString = null;
				try {
					scanner = new Scanner(file);

					while (scanner.hasNext()) {
						umsatzSaveString = scanner.next();
					}

					int umsatzSaveInt = Integer.parseInt(umsatzSaveString);

					Gui.umsatz = 0;
					
					Gui.umsatz += umsatzSaveInt;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

			}

			if (file.exists()) {
				g.drawString("Gesamtumsatz: " + speicherung.loadUmsatz() + " €", 280, 210);
			} else {
				g.drawString("Gesamtumsatz: " + Gui.umsatz + " €", 280, 210);
			}

			int intanzahlKunden = gui.getKunde().size();

			anzahlKunden = String.valueOf(intanzahlKunden);
			g.drawString("Anzahl an Kunden: " + anzahlKunden, 260, 436);

			int intanzahlGaragen = gui.getGarage().size();

			anzahlGaragen = String.valueOf(intanzahlGaragen);
			g.drawString("Anzahl an Garagen: " + anzahlGaragen, 255, 680);

		}

		repaint();
	}
}
