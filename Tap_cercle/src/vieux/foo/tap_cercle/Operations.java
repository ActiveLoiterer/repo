package vieux.foo.tap_cercle;

import java.util.Hashtable;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Operations {
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;
	
	public Operations(Context c)
	{
		databaseHelper = new DatabaseHelper(c);
	}
	
	public void ouvrirBD()
	{
		database = databaseHelper.getWritableDatabase();
	}
	
	public void fermerBD()
	{
		database.close();
	}
	
	public void ajouterUser(int age, char sexe)
	{
		databaseHelper.ajouterUser(new User(sexe, age), database);
	}
	
	public int getMostRecentFreq(){
		Cursor c = database.rawQuery("SELECT frequence, date FROM resultat ORDER BY date DESC", null);
		
		c.moveToFirst();
		int i = c.getInt(0);
		
		return i ;
	}
	// TODO modifier la requete pou f1,f2,f3
	public Vector<Integer> getFreqs(){
		Vector <Integer> freqs = new Vector<Integer>();
		
		Cursor c = database.rawQuery("SELECT frequence, date FROM resultat ORDER BY date ASC", null);
		try{
			while(c.moveToNext())
			{
				freqs.add(c.getInt(0));
			}
		}catch(Exception e)
		{
			Log.i("test", "lol");
		}
		
		return freqs;
	}
	
	public Vector<String> getDates(){
		Vector <String> dates = new Vector<String>();
		
		Cursor c = database.rawQuery("SELECT date FROM resultat ORDER BY date ASC", null);
		
		while(c.moveToNext())
		{
			dates.add(c.getString(0));
		}
		
		return dates;
	}
	
	public int getAge(){
		int age = 0;
		
		Cursor c = database.rawQuery("SELECT age FROM user", null);
		
		c.moveToFirst();
		
		age = c.getInt(0);
		
		return age;
	}
	
	public char getSexe(){
		char sexe = ' ';
		
		Cursor c = database.rawQuery("SELECT sexe FROM user", null);
		
		c.moveToFirst();
		
		sexe = c.getString(0).toCharArray()[0];
		
		return sexe;
	}
	
	public boolean aUser(){
		Cursor c = database.rawQuery("SELECT * FROM user", null);
		
		try{
			Log.i("test", String.valueOf(c.getCount()));
		}
		catch(Exception e){
			return false;
		}
		
		if(c.getCount() > 0)
			return true;
		else
			return false;
		
	}
	public void saveFr1(int fr1){
		ContentValues cv = new ContentValues();
		cv.put("frequence1", fr1);
		cv.put("frequence2", 0);
		cv.put("frequence3", 0);
		cv.put("pourcentage2", 0);
		cv.put("pourcentage3", 0);
		cv.put("date", "");
		database.insert("resultat", null, cv);
	}
}

