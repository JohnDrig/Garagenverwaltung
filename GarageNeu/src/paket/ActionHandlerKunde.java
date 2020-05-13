package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionHandlerKunde implements ActionListener {

	private Gui gui;

	/*
	 * Konstruktor
	 */
	public ActionHandlerKunde(Gui gui) {
		this.gui = gui;
	}

	// Variablen für kundenErstellenListener
	private JFrame frame;
	private LabelKunde labelKunde;
	private JPanel panelFrame;
	private int x, y;
	private JLabel titel, labelSchliessen;
	private ImageIcon schliessen;
	private JTextField feldName;
	private JSlider sliderAlter;
	static int tempSliderAlter;
	private JComboBox<String> garageBox;
	private JSpinner spinnerMonat;
	private JLabel endelabel;
	private ImageIcon endeicon;

	// Variablen für kundenEntfernenListener
	private JComboBox<String> boxKundeEntfernen;
	private JLabel labelRichtig;
	private ImageIcon iconRichtig;

	// Variablen für kundeBezahlenListener
	private JFrame frameBezahlen;
	private LabelKunde labelBezahlen;
	private JPanel panelBezahlen;
	private JLabel titelBezahlen, labelBezahlenSchliessen;
	private int xBezahlen, yBezahlen;
	private JComboBox<String> boxBezahlen;
	private JLabel labelRichtigKunde, labelKundeEnde;
	private ImageIcon iconRichtigKunde, iconKundeEnde;
	private JLabel nochZahlen;
	private JSlider sliderBezahlen;
	static int tempsliderBezahlen;

	// temps
	private int indexGarage;
	static int tempKundeErstellen = 0;
	static int tempKundeBezahlen = 0;
	static int tempRichtigKunde = 0;

	@Override
	public void actionPerformed(ActionEvent e) {

		// ruft die entsprechenden Methoden auf, wenn man den bestimmten Button drückt,
		// wo der ActionListener implementiert wurde
		if (e.getSource() == Gui.kundenErstellen) {
			tempKundeErstellen = 1;
			// man kann nur einen Kunden erstellen, wenn es wenigstens eine Garage gibt!
			if (gui.getGarage().size() >= 1) {
				kundeErstellenListener();
			}
		} else if (e.getSource() == Gui.kundeEntfernen) {
			// man kann nur einen Kunden entfernen, wenn es wenigstens einen Kunden gibt!
			if (gui.getKunde().size() >= 1) {
				kundenEntfernenListener();
			}

		} else if (e.getSource() == Gui.kundeBezahlen) {
			// ein Kunde kann erst bezahlen, wenn es logischerweise erst einen Kunden gibt
			if (gui.getKunde().size() >= 1) {
				// die Varible tempKundeBezahlen ist für das Label verantwortlich, damit man
				// weiss, wann was gezeichnet wird
				tempKundeBezahlen = 1;
				kundeBezahlenMethod();
			}
		}

	}

	private void kundeBezahlenMethod() {
		frameBezahlen = new JFrame();
		frameBezahlen.setLayout(null);
		frameBezahlen.setLocation(740, 330);
		frameBezahlen.setSize(500, 380);
		frameBezahlen.setUndecorated(true);
		frameBezahlen.getContentPane().setBackground(new Color(72, 116, 148));

		labelBezahlen = new LabelKunde();
		labelBezahlen.setBounds(0, 0, 500, 400);
		labelBezahlen.setVisible(true);
		labelBezahlen.setLayout(null);
		frameBezahlen.add(labelBezahlen);

		panelBezahlen = new JPanel();
		panelBezahlen.setLayout(null);
		panelBezahlen.setBounds(0, 0, 500, 45);
		panelBezahlen.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				xBezahlen = arg0.getX();
				yBezahlen = arg0.getY();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		panelBezahlen.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// damit man das Fenster verschieben kann
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frameBezahlen.setLocation(xx - xBezahlen, yy - yBezahlen);

			}
		});

		titelBezahlen = new JLabel("Bezahlung");
		titelBezahlen.setFont(new Font("Arial", Font.BOLD, 22));
		titelBezahlen.setVisible(true);
		titelBezahlen.setForeground(new Color(230, 153, 0));
		titelBezahlen.setBounds(170, 7, 200, 30);
		panelBezahlen.add(titelBezahlen);

		panelBezahlen.setBackground(new Color(20, 51, 74));

		// Schliessen-Icon for JPanel
		labelBezahlenSchliessen = new JLabel();
		schliessen = new ImageIcon("images/schliessen.png");
		labelBezahlenSchliessen.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frameBezahlen.setVisible(false);
				frameBezahlen = null;

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		labelBezahlenSchliessen.setIcon(schliessen);
		labelBezahlenSchliessen.setBounds(440, -2, 55, 50);
		frameBezahlen.add(labelBezahlenSchliessen);

		// Komponenten adden fürs frameBezahlen
		boxBezahlen = new JComboBox<String>();
		for (int i = 0; i < gui.getKunde().size(); i++) {
			boxBezahlen.addItem((i + 1) + ". " + gui.getKunde().get(i).getName());
		}

		boxBezahlen.setVisible(true);
		boxBezahlen.setBounds(180, 75, 200, 40);
		boxBezahlen.setFont(new Font("Arial", Font.BOLD, 16));
		frameBezahlen.add(boxBezahlen);

		labelRichtigKunde = new JLabel();
		iconRichtigKunde = new ImageIcon("images/richtigKunde.png");
		labelRichtigKunde.setIcon(iconRichtigKunde);
		labelRichtigKunde.setBounds(420, 73, 40, 40);
		labelRichtigKunde.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				tempRichtigKunde = 1;

				// den ganzen String in ein char Array packen, da das Format so aussieht : 1.
				// Kunde1, wo ich nur den Index haben will, sprich den ersten Platz davon.
				// Damit ich den ersten Platz aufrufen kann, wird ein char Array erstellt
				String indexKunde2 = (String) boxBezahlen.getSelectedItem();
				System.out.println(indexKunde2);
				char indexKunde1 = indexKunde2.charAt(0);
				int indexKunde = Character.getNumericValue(indexKunde1) - 1; // -1, damit ich den richtigen Index angebe

				int preisZsmKunde = gui.getKunde().get(indexKunde).getBezahlen();

				nochZahlen = new JLabel();
				nochZahlen.setText("Der Kunde muss noch " + preisZsmKunde + "€ zahlen");
				nochZahlen.setVisible(true);
				nochZahlen.setForeground(Color.WHITE);
				nochZahlen.setFont(new Font("Arial", Font.BOLD, 23));
				nochZahlen.setBounds(25, 170, 400, 30);
				frameBezahlen.add(nochZahlen);

				sliderBezahlen = new JSlider();
				sliderBezahlen.setMinimum(1);
				sliderBezahlen.setMaximum(preisZsmKunde);
				sliderBezahlen.setMajorTickSpacing(10);
				sliderBezahlen.setMinorTickSpacing(1);
				sliderBezahlen.setPaintTicks(true);
				sliderBezahlen.setValue(1);
				sliderBezahlen.setVisible(true);
				sliderBezahlen.setInverted(false);
				sliderBezahlen.setBorder(BorderFactory.createLineBorder(new Color(230, 153, 0), 3));
				sliderBezahlen.setBounds(25, 250, 280, 50);
				sliderBezahlen.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent arg0) {
						tempsliderBezahlen = sliderBezahlen.getValue();
					}

				});
				frameBezahlen.add(sliderBezahlen);

				labelKundeEnde = new JLabel();
				iconKundeEnde = new ImageIcon("images/richtigGarage.png");
				labelKundeEnde.setIcon(iconKundeEnde);
				labelKundeEnde.setVisible(true);
				labelKundeEnde.setBounds(410, 304, 64, 64);
				labelKundeEnde.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						int sliderBezahlenEnde = sliderBezahlen.getValue();

						Gui.umsatz += sliderBezahlenEnde;

						int kundePreisende = preisZsmKunde - tempsliderBezahlen;

						gui.getKunde().get(indexKunde).setBezahlen(kundePreisende);
						gui.createKundeTabelle();

						// alle temps zurücksetzen und Icons löschen
						tempRichtigKunde = 0;
						tempKundeBezahlen = 0;

						frameBezahlen.setVisible(false);
						frameBezahlen = null;
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
					}

				});
				frameBezahlen.add(labelKundeEnde);

				labelRichtigKunde.setVisible(false);
				labelRichtigKunde = null;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		frameBezahlen.add(labelRichtigKunde);

		// panel aufs JFrame adden
		frameBezahlen.add(panelBezahlen);
		frameBezahlen.setVisible(true);
	}

	private void kundenEntfernenListener() {
		boxKundeEntfernen = new JComboBox<String>();
		for (int i = 0; i < gui.getKunde().size(); i++) {
			boxKundeEntfernen.addItem((i + 1) + ". " + gui.getKunde().get(i).getName());
		}

		boxKundeEntfernen.setVisible(true);
		boxKundeEntfernen.setBounds(30, 545, 200, 40);
		boxKundeEntfernen.setFont(new Font("Arial", Font.BOLD, 16));
		Gui.panel.add(boxKundeEntfernen);
		Gui.panel.repaint();

		labelRichtig = new JLabel();
		iconRichtig = new ImageIcon("images/richtigEntfernen.png");
		labelRichtig.setIcon(iconRichtig);
		labelRichtig.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String indexKunde2 = (String) boxKundeEntfernen.getSelectedItem();

				char indexKunde1 = indexKunde2.charAt(0);

				int indexKunde = Character.getNumericValue(indexKunde1) - 1;

				// Abfrage, ob es den Index gibt
				if (indexKunde >= 0 && indexKunde < gui.getGarage().size()) {

					int indexneuGarage = gui.getKunde().get(indexKunde).getIndexGarage();
					gui.getGarage().get(indexneuGarage).setGebucht(false);

					// Ordner-Klasse aufrufen
					JOptionPane.showMessageDialog(null, "Gelöschte Daten werden gespeichert!");
					Ordner.erstelleOrdner();
					Ordner.deletedKunde.add(gui.getKunde().get(indexKunde));
					Ordner.setTextinKundenDatei();

					gui.getKunde().remove(indexKunde);

					gui.createKundeTabelle();
					gui.createGarageTabelle();
				}

				// Komponenten löschen
				boxKundeEntfernen.setVisible(false);
				boxKundeEntfernen = null;

				labelRichtig.setVisible(false);
				labelRichtig = null;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		labelRichtig.setBounds(250, 545, 48, 48);
		Gui.panel.add(labelRichtig);
		Gui.panel.repaint();
	}

	private void kundeErstellenListener() {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setLocation(740, 330);
		frame.setSize(500, 560);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(72, 116, 148));

		labelKunde = new LabelKunde();
		labelKunde.setBounds(0, 0, 500, 560);
		labelKunde.setVisible(true);
		labelKunde.setLayout(null);
		frame.add(labelKunde);

		panelFrame = new JPanel();
		panelFrame.setLayout(null);
		panelFrame.setBounds(0, 0, 500, 45);
		panelFrame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				x = arg0.getX();
				y = arg0.getY();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		panelFrame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frame.setLocation(xx - x, yy - y);

			}
		});

		titel = new JLabel("Kunde erstellen");
		titel.setFont(new Font("Arial", Font.BOLD, 22));
		titel.setVisible(true);
		titel.setForeground(new Color(230, 153, 0));
		titel.setBounds(160, 7, 200, 30);
		panelFrame.add(titel);

		panelFrame.setBackground(new Color(20, 51, 74));

		// Schliessen-Icon for JPanel
		labelSchliessen = new JLabel();
		schliessen = new ImageIcon("images/schliessen.png");
		labelSchliessen.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				frame = null;

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		labelSchliessen.setIcon(schliessen);
		labelSchliessen.setBounds(440, -2, 55, 50);
		panelFrame.add(labelSchliessen);

		frame.validate();

		frame.add(panelFrame);

		// Komponenten für KundenErstellen-Listener
		feldName = new JTextField();
		feldName.setVisible(true);
		feldName.setFont(new Font("Arial", Font.BOLD, 18));
		feldName.setBounds(130, 60, 300, 40);
		feldName.setForeground(Color.BLACK);
		feldName.setUI(new RoundedCornerTextFieldUI());
		frame.add(feldName);

		sliderAlter = new JSlider();
		sliderAlter.setMinimum(1);
		sliderAlter.setMaximum(120);
		sliderAlter.setMajorTickSpacing(10);
		sliderAlter.setMinorTickSpacing(1);
		sliderAlter.setPaintTicks(true);
		sliderAlter.setValue(1);
		sliderAlter.setVisible(true);
		sliderAlter.setInverted(false);
		sliderAlter.setBorder(BorderFactory.createLineBorder(new Color(230, 153, 0), 3));
		sliderAlter.setBounds(160, 175, 230, 45);
		sliderAlter.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				tempSliderAlter = sliderAlter.getValue();
			}

		});
		frame.add(sliderAlter);

		garageBox = new JComboBox<String>();

		for (int i = 0; i < gui.getGarage().size(); i++) {
			garageBox.addItem((i + 1) + ". " + gui.getGarage().get(i).getName());
		}

		garageBox.setVisible(true);
		garageBox.setBounds(260, 295, 200, 40);
		garageBox.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(garageBox);

		SpinnerNumberModel monat = new SpinnerNumberModel();

		monat.setMinimum(1);
		monat.setMaximum(12);
		monat.setStepSize(1);
		monat.setValue(1);
		spinnerMonat = new JSpinner(monat);

		spinnerMonat.setVisible(true);
		spinnerMonat.setBounds(135, 400, 100, 40);
		spinnerMonat.setFont(new Font("Arial", Font.BOLD, 20));
		frame.add(spinnerMonat);

		endelabel = new JLabel();
		endeicon = new ImageIcon("images/richtigGarage.png");
		endelabel.setIcon(endeicon);
		endelabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				tempKundeErstellen = 0;

				// Datenverarbeitung
				String getNameKunde = feldName.getText();
				int getAlterKunde = sliderAlter.getValue();

				String nameGarage = (String) garageBox.getSelectedItem();

				// Indexverarbeitung für die Bezahlung
				String indexGarage2 = (String) garageBox.getSelectedItem();

				char indexGarage1 = indexGarage2.charAt(0);
				indexGarage = Character.getNumericValue(indexGarage1) - 1;
				int preisKunde = gui.getGarage().get(indexGarage).getPreis();

				int getMonatKunde = (int) spinnerMonat.getValue();

				int preisZsm = preisKunde * getMonatKunde;

				/*
				 * Objekterstellung
				 */
				if (gui.getGarage().get(indexGarage).getGebucht() == false) {
					if (gui.getGarage().get(indexGarage).getSperren() == false) {
						Kunde newKunde = new Kunde(getNameKunde, getAlterKunde, nameGarage, getMonatKunde, preisZsm,
								indexGarage);
						gui.getKunde().add(newKunde);

						// die gebuchte Garage wird auf setGebucht(true) gesetzt
						gui.getGarage().get(indexGarage).setGebucht(true);
						gui.createKundeTabelle();
						gui.createGarageTabelle();
					}
				}

				frame.setVisible(false);
				frame = null;

				resetAll();

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		endelabel.setBounds(400, 480, 65, 65);
		frame.add(endelabel);

		frame.setVisible(true);

	}

	private void resetAll() {
		feldName.setText("");
		sliderAlter.setValue(1);
		garageBox.setSelectedItem(null);
		spinnerMonat.setValue(0);
	}

}
