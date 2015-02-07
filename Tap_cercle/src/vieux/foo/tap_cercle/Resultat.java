package vieux.foo.tap_cercle;

import java.util.Date;

public class Resultat {
	int frequence, pourcentage;
	Date date;
	
	public Resultat(int frequence, int pourcentage, Date date)
	{
		this.frequence = frequence;
		this.pourcentage = pourcentage;
		this.date = date;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
