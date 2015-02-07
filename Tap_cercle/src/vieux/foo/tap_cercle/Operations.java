package vieux.foo.tap_cercle;

import java.util.Date;
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
	
	public void ajouterResultat(int freq1,int freq2,int freq3, int pourcentage2,int pourcentage3,int tempssec, Date date)
	{
		databaseHelper.ajouterResultat(new Resultat(freq1,freq2,freq3, pourcentage2, pourcentage3, tempssec, date), database);
	}
	
	public void ajouterUser(int age, char sexe)
	{
		databaseHelper.ajouterUser(new User(sexe, age), database);
	}
	
	public int getMostRecentFreq(){
		Cursor c = database.rawQuery("SELECT frequence3 date FROM resultat ORDER BY _id DESC", null);
		
		c.moveToFirst();
		int i = c.getInt(0);
		
		return i ;
	}
	// TODO modifier la requete pou f1,f2,f3
	public Vector<Vector<Integer>> getFreqs(){
		Vector <Vector<Integer>> v = new Vector<Vector<Integer>>();
		Vector <Integer> freqs = null;
		
		Cursor c = database.rawQuery("SELECT frequence1, frequence2,frequence3 FROM resultat", null);

			while(c.moveToNext())
			{
				freqs = new Vector<Integer>();
				for(int i = 0; i < 3; i++){					
					freqs.add(c.getInt(i));
				}
				v.add(freqs);
			}
		
		return v;
	}
	
	public Vector<String> getDates(){
		Vector <String> dates = new Vector<String>();
		
		Cursor c = database.rawQuery("SELECT date FROM resultat", null);
		
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
	public void saveFr2(int fr2){
		ContentValues cv = new ContentValues();
		cv.put("frequence2", fr2);
		Cursor c = database.rawQuery("SELECT MAX(_id) FROM resultat",null);
		c.moveToFirst();
		int index = c.getInt(0);
		Log.i("last_id", ""+index);
		database.update("resultat", cv, "_id = ?",new String[]{String.valueOf(index)});		
	}
	public void saveFr3(int fr3){
		ContentValues cv = new ContentValues();
		cv.put("frequence3", fr3);
		Cursor c = database.rawQuery("SELECT MAX(_id) FROM resultat",null);
		c.moveToFirst();
		int index = c.getInt(0);
		database.update("resultat", cv, "_id = ?",new String[]{String.valueOf(index)});
	}
	public void saveTemps(int tempssec){
		ContentValues cv = new ContentValues();
		cv.put("tempssec", tempssec);
		Cursor c = database.rawQuery("SELECT MAX(_id) FROM resultat",null);
		c.moveToFirst();
		int index = c.getInt(0);
		database.update("resultat", cv, "_id = ?",new String[]{String.valueOf(index)});
	}
	
	public Vector<Integer> getEntrainements(){
		Vector <Integer> i = new Vector<Integer>();
		
		Cursor c = database.rawQuery("SELECT _id FROM resultat", null);
		
		while(c.moveToNext())
		{
			i.add(c.getInt(0));
		}
		
		return i;
	}
}

