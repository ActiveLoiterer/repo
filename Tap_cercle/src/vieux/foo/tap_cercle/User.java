package vieux.foo.tap_cercle;

public class User {
	
	char sexe;
	int age, freq;
	
	public User(char sexe, int age, int freq) {
		this.sexe = sexe;
		this.age = age;
		this.freq = freq;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
}

