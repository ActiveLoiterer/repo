package vieux.foo.tap_cercle;

import java.util.Date;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StatsActivity extends Activity {
	
	TextView moyText;
	Vector <Vector<Integer>>freqs;
	ListView listMoyEntraine;
	String [] entrainements;
	Vector<Integer> idEntraine;
	Vector<Integer> moyennes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		
		freqs = new Vector<Vector<Integer>>();
		
		idEntraine = new Vector<Integer>();
		
		moyennes = new Vector<Integer>();

		moyText = (TextView)findViewById(R.id.textMoy);
		
		listMoyEntraine =(ListView)findViewById(R.id.listeMoy);

		Operations o = new Operations(this);				

		o.ouvrirBD();
		
		/*o.ajouterUser(21, 'M');
		
		o.ajouterResultat(250,150,200,50,50, new Date());
		
		o.ajouterResultat(350,250,300,50,50, new Date());*/
		
		try{
			freqs = o.getFreqs();		
			
			for(Vector<Integer> v: freqs){
				Log.i("INFO", v + "");
				moyennes.add(Stats.moyParEntr(v));
			}
			
			idEntraine = o.getEntrainements();	
		}
		catch(Exception e)
		{
			Log.i("INFO", "PAS DE DONNÉES !1");
		}				
		
		o.fermerBD();
		
		try{
			moyText.setText(String.valueOf(Stats.moyenne(freqs)));
		}catch(Exception e)
		{
			Log.i("INFO", "PAS DE DONNÉES !2");
		}
		
		fillStringTable();
		ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, entrainements);
        listMoyEntraine.setAdapter(a);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void fillStringTable(){
		entrainements = new String [idEntraine.size()];
		
		for(int i = 0; i < entrainements.length; i++){
			String strIdEntr = idEntraine.get(i).toString(), strMoy = moyennes.get(i).toString();

			entrainements[i] = "Entrainement #" + strIdEntr + ": " + strMoy;
		}
	}
}