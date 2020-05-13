package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionHandler extends JTextField implements ActionListener {

	private static final long serialVersionUID = 1L;
	// Variablen für GarageErstellenFrame
	private JFrame frame;
	private JPanel panelFrame;
	private JLabel labelSchliessen, labelSaveDataGarage;
	private ImageIcon schliessen, saveDataGarage;
	private JLabel titel;
	private JTextField feldName;
	private LabelGarage labelGarage;
	private JSlider sliderGroesse, sliderPreis;
	private String saveName;
	private int saveGroesse, savePreis;
	private int x, y;
	static int tempSliderGroesse, tempSliderPreis;

	// Variablen für GarageEntfernen-Listener
	private JComboBox<String> box;
	private ImageIcon iconRichtig;
	private JLabel labelRichtig;

	// Variablen für GarageSperren-Listener
	private JComboBox<String> sperrenbox;
	private ImageIcon iconRichtig2;
	private JLabel labelRichtig2;

	// Variablen für GarageFreigeben-Listener
	private JComboBox<String> freigebenbox;
	private ImageIcon iconRichtig3;
	private JLabel labelRichtig3;

	private Gui gui;

	public ActionHandler(Gui gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ruft die entsprechenden Methoden auf, wenn man den bestimmten Button drückt,
		// wo der ActionListener implementiert wurde
		if (e.getSource() == Gui.garageErstellen) {
			garageErstellenMethod();
		} else if (e.getSource() == Gui.garageEntfernen) {
			if (gui.getGarage().size() >= 1) {
				garageEntfernenMethod();
			}
		} else if (e.getSource() == Gui.garageSperren) {
			garageSperrenMethod();
		} else if (e.getSource() == Gui.garageFreigeben) {
			garageFreigebenMethod();
		}
	}

	private void garageFreigebenMethod() {
		freigebenbox = new JComboBox<String>();

		// alle Garagen sollen der JComboBox hinzugefügt werden
		for (int i = 0; i < gui.getGarage().size(); i++) {
			freigebenbox.addItem((i + 1) + ". " + gui.getGarage().get(i).getName());
		}
		freigebenbox.setVisible(true);
		freigebenbox.setBounds(30, 355, 200, 40);
		freigebenbox.setFont(new Font("Arial", Font.BOLD, 16));
		Gui.panel.add(freigebenbox);
		Gui.panel.repaint();

		labelRichtig3 = new JLabel();
		iconRichtig3 = new ImageIcon("images/richtigEntfernen.png");
		labelRichtig3.setIcon(iconRichtig3);
		labelRichtig3.setVisible(true);
		labelRichtig3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String indexGarage2 = (String) freigebenbox.getSelectedItem();

				// den ganzen String in ein char Array packen, da das Format so aussieht : 1.
				// Garage1, wo ich nur den Index haben will, sprich den ersten Platz davon.
				// Damit ich den ersten Platz aufrufen kann, wird ein char Array erstellt
				char indexGarage1 = indexGarage2.charAt(0);

				int indexGarage = Character.getNumericValue(indexGarage1) - 1;

				// Abfrage, ob es den Index gibt
				if (indexGarage >= 0 && indexGarage < gui.getGarage().size()) {
					gui.getGarage().get(indexGarage).setSperren(false);
					gui.createGarageTabelle();
				}

				// Komponenten löschen
				freigebenbox.setVisible(false);
				freigebenbox = null;

				labelRichtig3.setVisible(false);
				labelRichtig3 = null;
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
		labelRichtig3.setBounds(250, 350, 48, 48);
		Gui.panel.add(labelRichtig3);
	}

	private void garageSperrenMethod() {
		sperrenbox = new JComboBox<String>();
		for (int i = 0; i < gui.getGarage().size(); i++) {
			sperrenbox.addItem((i + 1) + ". " + gui.getGarage().get(i).getName());
		}
		sperrenbox.setVisible(true);
		sperrenbox.setBounds(30, 355, 200, 40);
		sperrenbox.setFont(new Font("Arial", Font.BOLD, 16));
		Gui.panel.add(sperrenbox);
		Gui.panel.repaint();

		labelRichtig2 = new JLabel();
		iconRichtig2 = new ImageIcon("images/richtigEntfernen.png");
		labelRichtig2.setIcon(iconRichtig2);
		labelRichtig2.setVisible(true);
		labelRichtig2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String indexGarage2 = (String) sperrenbox.getSelectedItem();

				char indexGarage1 = indexGarage2.charAt(0);

				int indexGarage = Character.getNumericValue(indexGarage1) - 1;

				if (indexGarage >= 0 && indexGarage < gui.getGarage().size()) {
					gui.getGarage().get(indexGarage).setSperren(true);
					gui.createGarageTabelle();
				}

				// Komponenten löschen
				sperrenbox.setVisible(false);
				sperrenbox = null;

				labelRichtig2.setVisible(false);
				labelRichtig2 = null;
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
		labelRichtig2.setBounds(250, 350, 48, 48);
		Gui.panel.add(labelRichtig2);

	}

	private void garageEntfernenMethod() {

		box = new JComboBox<String>();
		for (int i = 0; i < gui.getGarage().size(); i++) {
			box.addItem((i + 1) + ". " + gui.getGarage().get(i).getName());
		}

		box.setVisible(true);
		box.setBounds(30, 185, 200, 40);
		box.setFont(new Font("Arial", Font.BOLD, 16));
		Gui.panel.add(box);
		Gui.panel.repaint();

		labelRichtig = new JLabel();
		iconRichtig = new ImageIcon("images/richtigEntfernen.png");
		labelRichtig.setIcon(iconRichtig);
		labelRichtig.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String indexGarage2 = (String) box.getSelectedItem();

				char indexGarage1 = indexGarage2.charAt(0);

				int indexGarage = Character.getNumericValue(indexGarage1) - 1;

				if (indexGarage >= 0 && indexGarage < gui.getGarage().size()) {

					JOptionPane.showMessageDialog(null, "Gelöschte Daten werden gespeichert!");
					// Ordner-Klasse aufrufen
					Ordner.erstelleOrdner();
					Ordner.deletedGarage.add(gui.getGarage().get(indexGarage));
					Ordner.setTextinTextdatei();

					gui.getGarage().remove(indexGarage);
					gui.createGarageTabelle();
				}

				// Komponenten löschen
				box.setVisible(false);
				box = null;

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
		labelRichtig.setBounds(250, 180, 48, 48);
		Gui.panel.add(labelRichtig);
		Gui.panel.repaint();
	}

	private void garageErstellenMethod() {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setLocation(740, 330);
		frame.setSize(500, 425);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(72, 116, 148));

		labelGarage = new LabelGarage();
		labelGarage.setBounds(0, 0, 500, 400);
		labelGarage.setVisible(true);
		labelGarage.setLayout(null);
		frame.add(labelGarage);

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
				//gehört zum MouseMotionListener
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
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				//damit man das Fenster verschieben kann
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frame.setLocation(xx - x, yy - y);

			}
		});
		
		titel = new JLabel("Garage erstellen");
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
		labelSchliessen.setIcon(schliessen);
		labelSchliessen.setBounds(440, -2, 55, 50);
		panelFrame.add(labelSchliessen);

		// Komponenten für GarageErstellenFrame
		feldName = new JTextField();
		feldName.setVisible(true);
		feldName.setFont(new Font("Arial", Font.BOLD, 18));
		feldName.setBounds(130, 60, 300, 40);
		feldName.setForeground(Color.BLACK);
		feldName.setUI(new RoundedCornerTextFieldUI());
		frame.add(feldName);

		sliderGroesse = new JSlider();
		sliderGroesse.setMinimum(10);
		sliderGroesse.setMaximum(200);
		sliderGroesse.setMajorTickSpacing(5);
		sliderGroesse.setMinorTickSpacing(1);
		sliderGroesse.setPaintTicks(true);
		sliderGroesse.setValue(10);
		sliderGroesse.setVisible(true);
		sliderGroesse.setInverted(false);
		sliderGroesse.setBorder(BorderFactory.createLineBorder(new Color(230, 153, 0), 3));
		sliderGroesse.setBounds(160, 175, 230, 45);
		sliderGroesse.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				tempSliderGroesse = sliderGroesse.getValue();
			}

		});
		frame.add(sliderGroesse);

		sliderPreis = new JSlider();
		sliderPreis.setMinimum(1);
		sliderPreis.setMaximum(300);
		sliderPreis.setMajorTickSpacing(10);
		sliderPreis.setMinorTickSpacing(1);
		sliderPreis.setPaintTicks(true);
		sliderPreis.setValue(10);
		sliderPreis.setVisible(true);
		sliderPreis.setInverted(false);
		sliderPreis.setBorder(BorderFactory.createLineBorder(new Color(230, 153, 0), 3));
		sliderPreis.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				tempSliderPreis = sliderPreis.getValue();
			}

		});
		sliderPreis.setBounds(160, 290, 240, 45);
		frame.add(sliderPreis);

		labelSaveDataGarage = new JLabel();
		saveDataGarage = new ImageIcon("images/richtigGarage.png");
		labelSaveDataGarage.setIcon(saveDataGarage);
		labelSaveDataGarage.setBounds(400, 350, 65, 65);
		labelSaveDataGarage.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * End-Variablen für GarageErstellenFrame
				 */
				saveName = feldName.getText();
				saveGroesse = sliderGroesse.getValue();
				savePreis = sliderPreis.getValue();

				// neue Garage erstellen
				Garage garage = new Garage(saveName, saveGroesse, false, savePreis, false);
				gui.getGarage().add(garage);

				gui.createGarageTabelle();

				frame.setVisible(false);
				frame = null;

				resetAll();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
		frame.add(labelSaveDataGarage);

		frame.validate();

		frame.add(panelFrame);

		frame.setVisible(true);

	}

	public void resetAll() {
		sliderGroesse.setValue(0);
		sliderPreis.setValue(0);
		feldName.setText("");
	}

}
