package vieux.foo.tap_cercle;

import java.util.Vector;

public class Stats {
	public static int temps = 15;

	public Stats() {
		// TODO Auto-generated constructor stub
	}
	
	public static int freqMax(int age, char sexe){
		if(sexe == 'M'){
			return 220 - age;
		}
		else{
			return 226 - age;
		}		
	}
		
	public static int pourcentage (int pulsations, int age, char sexe){
		return (pulsations * temps * 100 )/ freqMax(age, sexe);
	}
	
	public static int moyenne(Vector<Integer> freqs){
		int moy = 0;
		
		for(int i:freqs){
			moy += i;
		}
		
		moy /= freqs.size();
		
		return moy;
	}
}
