package paket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Speicherung {

	static String pathUmsatz = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\saveData\\saveUmsatz.txt";
	static String pathGarage = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\saveData\\saveGaragen.txt";
	static String pathKunde = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\saveData\\saveKunden.txt";
	private String pathSaveUmsatz = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\saveData\\saveUmsatz.txt";
	static String pathMain = "C:\\Users\\johnd\\Downloads\\Garagenvermietung";
	private String pathOrdner = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\saveData";

	/*
	 * 
	 * Speicherung der Daten
	 * 
	 */

	// den Umsatz (im 2ten Tab in der Gui) speichern
	public void saveUmsatz(String umsatz) {
		File file, ordner;
		FileWriter writeFile = null;

		try {
			// neuen Ordner erstellen, falls es den noch nicht gibt
			ordner = new File(pathOrdner);

			if (!ordner.exists()) {
				ordner.mkdirs();
			}

			file = new File(pathSaveUmsatz);

			if (!file.exists()) {
				file.createNewFile();
			}

			writeFile = new FileWriter(pathSaveUmsatz);

			writeFile.write(umsatz);

			// den Gesamtumsatz in die Textdatei reinschreiben, und somit abspeichern

		} catch (Exception e) {
			System.out.println("geht nicht");
			e.printStackTrace();
		} finally {
			if (writeFile != null) {
				try {
					writeFile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// lädt und liest den gespeicherten Umsatz
	public String loadUmsatz() {

		File file = new File(pathSaveUmsatz);
		Scanner scanner = null;
		String umsatzString = null;

		if (file.exists()) {
			try {
				scanner = new Scanner(file);

				while (scanner.hasNext()) {
					umsatzString = scanner.next();
				}

				return umsatzString;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (scanner != null) {
					scanner.close();
				}
			}
		} else {
			return "0";
		}

		return umsatzString;

	}

	public void saveKunde(ArrayList<Kunde> kunden) {
		File file, ordner;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			// neuen Ordner erstellen, falls es den noch nicht gibt xD
			ordner = new File(pathOrdner);

			if (!ordner.exists()) {
				ordner.mkdirs();
			}

			file = new File(pathKunde);

			if (!file.exists()) {
				file.createNewFile();
			}

			fos = new FileOutputStream(pathKunde);
			oos = new ObjectOutputStream(fos);

			// ganze ArrayList in die Textdatei reinschreiben, und somit abspeichern
			oos.writeObject(kunden);

		} catch (Exception e) {
			System.out.println("das geht leider nicht");
			e.printStackTrace();
		} finally {
			if (fos != null && oos != null) {
				try {
					fos.close();
					oos.close();
				} catch (Exception e) {
					System.out.println("Geht immernoch nicht");
					e.printStackTrace();
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Kunde> loadKunde() {

		File file = new File(pathKunde);

		if (file.exists()) {
			ArrayList<Kunde> kunde = new ArrayList<Kunde>();

			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis = new FileInputStream(pathKunde);
				ois = new ObjectInputStream(fis);

				kunde = (ArrayList<Kunde>) ois.readObject();

			} catch (Exception e) {
				System.out.println("geht nicht");
				e.printStackTrace();
			} finally {
				if (fis != null && ois != null) {
					try {
						fis.close();
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

			return kunde;
		} else {
			return null;
		}
	}

	public void saveGarage(ArrayList<Garage> garage) {
		File file, ordner;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			// neuen Ordner erstellen, falls es den noch nicht gibt xD
			ordner = new File(pathOrdner);

			if (!ordner.exists()) {
				ordner.mkdirs();
			}

			// neue Textdatei erstellen
			file = new File(pathGarage);

			if (!file.exists()) {
				file.createNewFile();
			}

			fos = new FileOutputStream(pathGarage);
			oos = new ObjectOutputStream(fos);

			// ganze ArrayList in die Textdatei reinschreiben, und somit abspeichern
			oos.writeObject(garage);

		} catch (Exception e) {
			System.out.println("Geht nicht");
			e.printStackTrace();
		} finally {
			if (fos != null && oos != null) {
				try {
					fos.close();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Garage> loadGarage() {

		File file = new File(pathGarage);

		if (file.exists()) {
			ArrayList<Garage> garage = new ArrayList<Garage>();

			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis = new FileInputStream(pathGarage);
				ois = new ObjectInputStream(fis);

				garage = (ArrayList<Garage>) ois.readObject();

			} catch (Exception e) {
				System.out.println("geht leider nicht xD");
				e.printStackTrace();
			} finally {
				if (fis != null && ois != null) {
					try {
						fis.close();
						ois.close();
					} catch (Exception e) {
						System.out.println("geht auch nicht. Schade John :D");
						e.printStackTrace();
					}

				}
			}

			return garage;
		} else {
			return null;
		}

	}

}
