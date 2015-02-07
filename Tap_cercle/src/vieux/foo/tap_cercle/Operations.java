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
}

