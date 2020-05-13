package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class Gui {

	private JFrame frame;
	static JPanel panel;
	private JPanel panelInfo;
	static Label label;
	private JTabbedPane tab;
	static JButton kundenErstellen, kundeEntfernen, garageErstellen, garageEntfernen, kundeBezahlen;
	static JButton garageSperren, garageFreigeben;
	private ArrayList<Garage> garage;
	private ArrayList<Kunde> kunde;
	static int umsatz = 0;
	private JLabel[] strich;
	private Speicherung speicherung = new Speicherung();

	// Variablen für die Garagen-Tabelle
	private Object[] descriptions;
	private Object[][] data;
	private JTable tableGarage;
	private JScrollPane spGarage;
	private DefaultTableModel modelGarage;

	// Variablen für die Kunde-Tabelle
	private Object[] descriptionsKunde;
	private Object[][] dataKunde;
	private JTable tableKunde;
	private JScrollPane spKunde;
	private DefaultTableModel modelKunde;

	// Variablen für die Datenbank
	private JTextField searchField;
	private JTextField kundesearchField;

	static final int breite = 1000, hoehe = 800;
	static int saveIndexTab;

	public Gui() {
		tableGarage = new JTable();
		spGarage = new JScrollPane(tableGarage);

		tableKunde = new JTable();
		spKunde = new JScrollPane(tableKunde);
	}

	public void fillArrayList() {
		garage = new ArrayList<>();
		kunde = new ArrayList<>();

		// es wird geprüft, ob das Programm sozusagen schon mal geöffnet wurde, denn
		// dann sollen die schon erstellten Garagen gespeichert und eingefügt werden

		File fileGarage = new File(Speicherung.pathGarage);

		if (fileGarage.exists()) {
			for (Garage g : speicherung.loadGarage()) {
				garage.add(new Garage(g.getName(), g.getGroesse(), g.getSperren(), g.getPreis(), g.getGebucht()));
			}
		}

		File fileKunde = new File(Speicherung.pathKunde);

		if (fileKunde.exists()) {
			for (Kunde k : speicherung.loadKunde()) {
				kunde.add(new Kunde(k.getName(), k.getAlter(), k.getGarage(), k.getMonat(), k.getBezahlen(),
						k.getIndexGarage()));
			}
		}

	}

	public void feldGarageDatenBank() {
		searchField = new JTextField("Suchen");
		searchField.setVisible(true);
		searchField.setBounds(770, 115, 150, 35);
		searchField.setFont(new Font("Arial", Font.BOLD, 14));
		searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value = searchField.getText();

				for (int row = 0; row <= tableGarage.getRowCount() - 1; row++) {
					for (int col = 0; col <= tableGarage.getColumnCount() - 1; col++) {
						if (value.equals(tableGarage.getValueAt(row, col))) {
							tableGarage.scrollRectToVisible(tableGarage.getCellRect(row, 0, true));

							tableGarage.setRowSelectionInterval(row, row);
							searchField.setText("Suchen");
							for (int i = 0; i <= tableGarage.getColumnCount() - 1; i++) {
								tableGarage.getColumnModel().getColumn(i).setCellRenderer(new HighLightRenderer());
							}
						}
					}
				}
			}

		});
		panel.add(searchField);
	}

	public void feldKundeDatenBank() {
		kundesearchField = new JTextField("Suchen");
		kundesearchField.setVisible(true);
		kundesearchField.setBounds(770, 435, 150, 35);
		kundesearchField.setFont(new Font("Arial", Font.BOLD, 14));
		kundesearchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value = kundesearchField.getText();

				int valueint = 0;

				try {
					valueint = Integer.parseInt(value);
				} catch (Exception e) {

				}

				for (int row = 0; row <= tableKunde.getRowCount() - 1; row++) {
					for (int col = 0; col <= tableKunde.getColumnCount() - 1; col++) {
						if (value.equals(tableKunde.getValueAt(row, col)) || valueint == row && valueint == col) {
							tableKunde.scrollRectToVisible(tableKunde.getCellRect(row, 0, true));

							tableKunde.setRowSelectionInterval(row, row);
							kundesearchField.setText("Suchen");
							for (int i = 0; i <= tableKunde.getColumnCount() - 1; i++) {
								tableKunde.getColumnModel().getColumn(i).setCellRenderer(new HighLightRenderer());
							}
						}
					}
				}
			}

		});
		panel.add(kundesearchField);
	}

	public void malStricheaufInfoPanel() {
		strich = new JLabel[2];
		int temp = 0;
		for (int i = 0; i < strich.length; i++) {
			temp += 255;
			strich[i] = new JLabel();
			strich[i].setText(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------");
			strich[i].setVisible(true);
			strich[i].setForeground(Color.ORANGE);
			strich[i].setFont(new Font("Arial", Font.BOLD, 17));
			strich[i].setBounds(35, 0 + temp, 1000, 30);
			panelInfo.add(strich[i]);

		}
	}

	public void garageVermietungOrdner() {

		File file = new File(Speicherung.pathMain);

		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public void createWindow() {
		frame = new JFrame("Garagenvermietung");
		frame.setResizable(true);
		frame.setSize(breite, hoehe);
		frame.setLayout(null);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// die save Methode soll aufgerufen werden, wenn man das Fenster schliesst,
				// damit alle Daten gespeichert werden
				speicherung.saveGarage(getGarage());
				speicherung.saveKunde(getKunde());
				String umsatzString = String.valueOf(Gui.umsatz);
				speicherung.saveUmsatz(umsatzString);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

		});
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		label = new Label(this);
		label.setBounds(0, 0, breite, hoehe);
		label.setVisible(true);
		frame.add(label);

		panel = new JPanel();
		panel.setBounds(0, 0, breite, hoehe);
		panel.setVisible(true);
		panel.setLayout(null);
		panel.setBackground(new Color(43, 63, 97));

		panelInfo = new JPanel();
		panelInfo.setBounds(0, 0, breite, hoehe);
		panelInfo.setVisible(true);
		panelInfo.setLayout(null);
		panelInfo.setBackground(new Color(43, 63, 97));

		// JTabbedPane erstellen
		tab = new JTabbedPane();
		tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				saveIndexTab = tab.getSelectedIndex();
			}
		});
		tab.setBounds(0, 0, breite, hoehe);
		tab.add("          Main             ", panel);
		tab.add("        Informationen         ", panelInfo);

		frame.add(tab);

		frame.setVisible(true);

	}

	public void createButtons() {
		// Garage-Erstellen-Button
		garageErstellen = new JButton();
		garageErstellen.setSize(115, 54);
		ImageIcon garageErstellenIcon = new ImageIcon("images/garage.png");
		garageErstellen.setIcon(garageErstellenIcon);
		garageErstellen.setBounds(30, 100, 115, 54);
		garageErstellen.setBackground(new Color(230, 153, 0));
		garageErstellen.addActionListener(new ActionHandler(this));
		garageErstellen.setVisible(true);
		panel.add(garageErstellen);

		// Garage-Entfernen-Button
		garageEntfernen = new JButton();
		garageEntfernen.setSize(115, 54);
		ImageIcon garageMinusFoto = new ImageIcon("images/garage-.png");
		garageEntfernen.setIcon(garageMinusFoto);
		garageEntfernen.setBounds(160, 100, 115, 54);
		garageEntfernen.setBackground(new Color(230, 153, 0));
		garageEntfernen.addActionListener(new ActionHandler(this));
		garageEntfernen.setVisible(true);
		panel.add(garageEntfernen);

		// Garage-Sperren-Button
		garageSperren = new JButton();
		garageSperren.setSize(115, 54);
		ImageIcon icon = new ImageIcon("images/garage3.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(115, 54, java.awt.Image.SCALE_SMOOTH);
		garageSperren.setIcon(icon);
		garageSperren.setBounds(30, 270, 115, 54);
		garageSperren.setBackground(new Color(230, 153, 0));
		garageSperren.addActionListener(new ActionHandler(this));
		garageSperren.setVisible(true);
		panel.add(garageSperren);

		// Garage-Freigeben-Button
		garageFreigeben = new JButton();
		garageFreigeben.setSize(115, 54);
		ImageIcon garageFreigebenIcon = new ImageIcon("images/garage2.png");
		Image img2 = garageFreigebenIcon.getImage();
		Image newimg2 = img.getScaledInstance(115, 54, java.awt.Image.SCALE_SMOOTH);
		garageFreigeben.setIcon(garageFreigebenIcon);
		garageFreigeben.setBounds(160, 270, 115, 54);
		garageFreigeben.setBackground(new Color(230, 153, 0));
		garageFreigeben.addActionListener(new ActionHandler(this));
		garageFreigeben.setVisible(true);
		panel.add(garageFreigeben);

		// Kunden-Erstellen-Button
		kundenErstellen = new JButton();
		kundenErstellen.setSize(40, 45);
		ImageIcon kundeFoto = new ImageIcon("images/kundenIcon.png");
		kundenErstellen.setIcon(kundeFoto);
		kundenErstellen.setBounds(30, 460, 115, 54);
		kundenErstellen.setBackground(new Color(230, 153, 0));
		kundenErstellen.addActionListener(new ActionHandlerKunde(this));
		kundenErstellen.setVisible(true);
		panel.add(kundenErstellen);

		// Kunden-Entfernen-Button
		kundeEntfernen = new JButton();
		kundeEntfernen.setSize(115, 54);
		ImageIcon kundeEntfernenFoto = new ImageIcon("images/kunde-.png");
		kundeEntfernen.setIcon(kundeEntfernenFoto);
		kundeEntfernen.setBounds(160, 460, 115, 54);
		kundeEntfernen.setBackground(new Color(230, 153, 0));
		kundeEntfernen.addActionListener(new ActionHandlerKunde(this));
		kundeEntfernen.setVisible(true);
		panel.add(kundeEntfernen);

		kundeBezahlen = new JButton();
		kundeBezahlen.setSize(115, 54);
		ImageIcon garageGeldIcon = new ImageIcon("images/geld.png");
		kundeBezahlen.setIcon(garageGeldIcon);
		kundeBezahlen.setBounds(30, 640, 115, 54);
		kundeBezahlen.setBackground(new Color(230, 153, 0));
		kundeBezahlen.addActionListener(new ActionHandlerKunde(this));
		kundeBezahlen.setVisible(true);
		panel.add(kundeBezahlen);

	}

	public void createGarageTabelle() {
		descriptions = new Object[] { "Name", "Größe", "Gesperrt", "Preis", "Gebucht" };

		data = new Object[getGarage().size()][];

		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[] { (i + 1) + ". " + this.getGarage().get(i).getName(),
					this.getGarage().get(i).getGroesse() + " m²", this.getGarage().get(i).getSperren(),
					this.getGarage().get(i).getPreis() + " €", this.getGarage().get(i).getGebucht() };
		}

		tableGarage.getTableHeader().setBackground(new Color(230, 153, 0));
		tableGarage.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableGarage.setRowHeight(25);
		tableGarage.setRowSelectionAllowed(true);
		tableGarage.setFont(new Font("Arial", Font.BOLD, 14));

		modelGarage = new DefaultTableModel(data, descriptions);
		tableGarage.setModel(modelGarage);
		modelGarage.fireTableDataChanged();
		spGarage.setBounds(350, 160, 600, 200);
		spGarage.setBackground(Color.BLACK);

		panel.add(spGarage);
		frame.repaint();
		panel.repaint();
		label.repaint();
	}

	public void createKundeTabelle() {
		descriptionsKunde = new Object[] { "Name", "Alter", "Garagenplatz", "Monat", "Bezahlen" };

		dataKunde = new Object[getKunde().size()][];

		for (int i = 0; i < dataKunde.length; i++) {
			dataKunde[i] = new Object[] { (i + 1) + ". " + this.getKunde().get(i).getName(),
					this.getKunde().get(i).getAlter(), this.getKunde().get(i).getGarage(),
					this.getKunde().get(i).getMonat(), this.getKunde().get(i).getBezahlen() + " €" };
		}

		tableKunde.getTableHeader().setBackground(new Color(230, 153, 0));
		tableKunde.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableKunde.setRowHeight(25);
		tableKunde.setRowSelectionAllowed(true);
		tableKunde.setFont(new Font("Arial", Font.BOLD, 14));

		modelKunde = new DefaultTableModel(dataKunde, descriptionsKunde);
		tableKunde.setModel(modelKunde);
		modelKunde.fireTableDataChanged();
		spKunde.setBounds(350, 480, 600, 200);
		spKunde.setBackground(Color.BLACK);

		panel.add(spKunde);
		frame.repaint();
		panel.repaint();
		label.repaint();
	}

	// Setter und Getter
	public ArrayList<Garage> getGarage() {
		return this.garage;
	}

	public ArrayList<Kunde> getKunde() {
		return this.kunde;
	}

}
