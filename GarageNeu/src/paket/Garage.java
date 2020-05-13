package paket;

import java.io.Serializable;

public class Garage implements Serializable {

	private static final long serialVersionUID = 1L;

	// Variablen
	private String name;
	private int groesse;
	private boolean sperren;
	private int preis;
	private boolean gebucht;

	/*
	 * Konstruktor
	 */
	
	public Garage(String name, int groesse, boolean sperren, int preis, boolean gebucht) {
		setName(name);
		setGroesse(groesse);
		setSperren(sperren);
		setPreis(preis);
		setGebucht(gebucht);
	}

	//Setter und Getter
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public int getGroesse() {
		return this.groesse;
	}

	public void setSperren(boolean sperren) {
		this.sperren = sperren;
	}

	public boolean getSperren() {
		return this.sperren;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public int getPreis() {
		return this.preis;
	}

	public void setGebucht(boolean gebucht) {
		this.gebucht = gebucht;
	}

	public boolean getGebucht() {
		return this.gebucht;
	}

	// Methode
	public void removeGarage(int index) {

	}

}
