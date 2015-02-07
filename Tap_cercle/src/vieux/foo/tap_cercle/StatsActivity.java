package vieux.foo.tap_cercle;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StatsActivity extends Activity {
	
	TextView moyText;
	Vector <Integer>freqs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		freqs = new Vector<Integer>();
		Log.i("test", "PAS DE DONNÉES !1");
		moyText = (TextView)findViewById(R.id.textMoy);
		Log.i("test", "PAS DE DONNÉES !2");
		Operations o = new Operations(this);
		Log.i("test", "PAS DE DONNÉES !3");
		o.ouvrirBD();
		Log.i("test", "PAS DE DONNÉES !4");
		try{
			freqs = o.getFreqs();
		}
		catch(Exception e)
		{
			Log.i("test", "PAS DE DONNÉES !");
		}
		o.fermerBD();
		Log.i("test", "PAS DE DONNÉES !5");
		try{
			moyText.setText(String.valueOf(Stats.moyenne(freqs)));
		}catch(Exception e)
		{
			Log.i("test", "PAS DE DONNÉES !");
		}
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
}