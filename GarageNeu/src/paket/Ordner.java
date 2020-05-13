package paket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Ordner {

	/*
	 * HIER WERDEN ORDNER UND TEXTDATEIEN ERSTELLT, UM GELÖSCHTE DATEN ZU SPEICHERN
	 */

	static File ordner;
	static File fileGarage, fileKunde;
	static Scanner scanner;

	static ArrayList<Garage> deletedGarage = new ArrayList<Garage>();
	static ArrayList<Kunde> deletedKunde = new ArrayList<Kunde>();
	
	private static String pathOrdner = "C:\\Users\\johnd\\Downloads\\Garagenvermietung\\Deleted Data";

	public Ordner() {

	}

	// Dateien erstellen für die gelöschten Garagen
	public static void erstelleOrdner() {
		

		ordner = new File(pathOrdner);
		if (!ordner.exists() && !ordner.isDirectory()) {
			ordner.mkdirs();
		}

		fileGarage = new File(ordner.getPath() + "//Garage.txt");
		fileKunde = new File(ordner.getPath() + "//Kunde.txt");

		if (!fileGarage.exists()) {
			try {
				fileGarage.createNewFile();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!fileKunde.exists()) {
			try {
				fileKunde.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setTextinTextdatei() {

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM - HH:mm:ss");
		String zeit = format.format(date);

		String nameGarage;
		int groesse, preis;

		FileWriter filewriter = null;
		try {
			filewriter = new FileWriter(fileGarage);

			filewriter.write("Diese Garage/n wurde/n gelöscht:" + "\n" + "\n");

			for (int i = 0; i < deletedGarage.size(); i++) {
				nameGarage = deletedGarage.get(i).getName();
				groesse = deletedGarage.get(i).getGroesse();
				preis = deletedGarage.get(i).getPreis();

				filewriter.write(zeit + " ---- " + nameGarage + ", " + groesse + " m²" + ", " + preis + "€" + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (filewriter != null) {
				try {
					filewriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	// Dateien erstellen für die gelöschten Kunden
	public static void setTextinKundenDatei() {

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM - HH:mm:ss");
		String zeit = format.format(date);

		String nameKunde;
		int alterKunde;
		String gpKunde;
		int monatKunde;
		int bezahlenKunde;

		FileWriter filewriterKunde = null;
		try {
			filewriterKunde = new FileWriter(fileKunde);

			filewriterKunde.write("Diese/r Kunde/n wurde/n gelöscht:" + "\n" + "\n");

			for (int i = 0; i < deletedKunde.size(); i++) {
				nameKunde = deletedKunde.get(i).getName();
				alterKunde = deletedKunde.get(i).getAlter();
				gpKunde = deletedKunde.get(i).getGarage();
				monatKunde = deletedKunde.get(i).getMonat();
				bezahlenKunde = deletedKunde.get(i).getBezahlen();

				filewriterKunde.write(zeit + " ---- " + nameKunde + ", " + alterKunde + ", " + gpKunde + ", "
						+ monatKunde + " Monat/e gemietet" + ", " + bezahlenKunde + "€" + "\n");

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (filewriterKunde != null) {
				try {
					filewriterKunde.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
