package vieux.foo.tap_cercle;

import java.util.Vector;

import android.util.Log;

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
	
	public static int moyenne(Vector<Vector<Integer>> v){
		int moy1 = 0;
		int moy2 = 0;
		
		for(Vector<Integer> freqs:v){
			moy1 = 0;
			
			for(int i: freqs){
				moy1 += i;
			}
			
			moy1 /= freqs.size();
			moy2 += moy1;
		}
		
		moy2 /= v.size();
		
		return moy2;
	}
	
	public static int moyParEntr(Vector<Integer> v){
		int moy = 0;
		
		for(Integer i:v){
			moy+=i;
		}
		
		moy /= v.size();
		
		return moy;
	}
	
	public static int pourcentage (int pulsations, int age, char sexe){
		return (pulsations * temps * 100 )/ freqMax(age, sexe);
	}
}
