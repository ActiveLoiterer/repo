package vieux.foo.tap_cercle;

import java.util.Date;

public class Resultat {
	int frequence1,frequence2,frequence3, pourcentage2,pourcentage3, tempssec;
	Date date;
	
	public Resultat(int frequence1,int frequence2,int pourcentage2,int pourcentage3, int tempssec, Date date)
	{
		this.frequence1 = frequence1;
		this.frequence2 = frequence2;
		this.pourcentage2 = pourcentage2;
		this.pourcentage3 = pourcentage3;
		this.tempssec = tempssec;
		this.date = date;
	}

	public int getFrequence1() {
		return frequence1;
	}

	public void setFrequence1(int frequence1) {
		this.frequence1 = frequence1;
	}
	public int getFrequence2() {
		return frequence2;
	}

	public void setFrequence2(int frequence2) {
		this.frequence2 = frequence2;
	}
	public int getFrequence3() {
		return frequence3;
	}

	public int getPourcentage2() {
		return pourcentage2;
	}

	public void setPourcentage2(int pourcentage2) {
		this.pourcentage2 = pourcentage2;
	}
	public int getPourcentage3() {
		return pourcentage3;
	}

	public void setPourcentage3(int pourcentage3) {
		this.pourcentage3 = pourcentage3;
	}
	public int getTempssec() {
		return tempssec;
	}

	public void setTempssec(int tempssec) {
		this.tempssec = tempssec;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}