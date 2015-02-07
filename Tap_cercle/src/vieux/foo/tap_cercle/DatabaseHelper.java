package vieux.foo.tap_cercle;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	public DatabaseHelper(Context context) {
		super(context, "db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT, age INTEGER, sexe TEXT);");
		db.execSQL("CREATE TABLE resultat (_id INTEGER PRIMARY KEY AUTOINCREMENT, frequence1 INTEGER, frequence2 INTEGER,frequence3 INTEGER, pourcentage2 INTEGER,pourcentage3 INTEGER, date DATE);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS user");
		db.execSQL("DROP TABLE IF EXISTS resultat");
		onCreate(db);
	}
	
	public void ajouterUser(User u, SQLiteDatabase db)
	{
		ContentValues cv = new ContentValues();
		cv.put("age", u.getAge());
		cv.put("sexe", String.valueOf(u.getSexe()));
		
		db.insert("user", null, cv);
	}
	
	public void ajouterResultat(Resultat r, SQLiteDatabase db)
	{
		ContentValues cv = new ContentValues();
		cv.put("frequence1", r.getFrequence1());
		cv.put("frequence2", r.getFrequence2());
		cv.put("frequence3", r.getFrequence3());
		cv.put("pourcentage2", r.getPourcentage2());
		cv.put("pourcentage3", r.getPourcentage3());
		cv.put("date", r.getDate().toString());
		
		db.insert("resultat", null, cv);
	}
}
