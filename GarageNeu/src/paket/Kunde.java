package paket;

import java.io.Serializable;

public class Kunde implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private int alter;
	private String gp;
	private int monat;
	private int bezahlen;
	private int indexGarage;

	public Kunde(String name, int alter, String gp, int monat, int bezahlen, int indexGarage) {
		setName(name);
		setAlter(alter);
		setGarage(gp);
		setMonat(monat);
		setBezahlen(bezahlen);
		setIndexGarage(indexGarage);
	}

	public int getIndexGarage() {
		return indexGarage;
	}

	public void setIndexGarage(int indexGarage) {
		this.indexGarage = indexGarage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public int getAlter() {
		return this.alter;
	}

	private void setGarage(String gp) {
		this.gp = gp;
	}

	public String getGarage() {
		return this.gp;
	}

	private void setMonat(int monat) {
		this.monat = monat;
	}

	public int getMonat() {
		return this.monat;
	}

	public void setBezahlen(int bezahlen) {
		this.bezahlen = bezahlen;
	}

	public int getBezahlen() {
		return this.bezahlen;
	}

}
