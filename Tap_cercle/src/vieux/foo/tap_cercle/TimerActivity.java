package vieux.foo.tap_cercle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class TimerActivity extends Activity {
	
	Chronometer chronometre;
	Button buttonResetT, buttonArretT;
	ToggleButton toggleButtonStopPlay;
	Operations op;
	
	//Pour le chronomètre
	long pauseTime = -1; //pas encore de pause
	boolean reset = true;
	boolean okDialogue = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		chronometre = (Chronometer)findViewById(R.id.chronometerT);
		buttonResetT = (Button)findViewById(R.id.buttonResetT);
		buttonArretT = (Button)findViewById(R.id.buttonArretT);
		toggleButtonStopPlay = (ToggleButton)findViewById(R.id.toggleButtonStopPlayT);

		op = new Operations(this);
		//Ecouteur
		Ecouteur ec = new Ecouteur();
		toggleButtonStopPlay.setOnCheckedChangeListener(ec);
		buttonResetT.setOnClickListener(ec);
		buttonArretT.setOnClickListener(ec);
		
		toggleButtonStopPlay.setChecked(true);
		
		chronometre.setBase(SystemClock.elapsedRealtime());
		chronometre.start();
	}

	public void apresDialogue()
	{
		Log.i("test", "lol1");
		//SAVE
		op.ouvrirBD();
		Long tempssec = (SystemClock.elapsedRealtime() - chronometre.getBase())/1000;
		Log.i("test", "lol2");
		op.saveTemps(tempssec.intValue());
		op.fermerBD();
		Log.i("test", "lol3");
		//Intent
		Intent i = new Intent(TimerActivity.this, TapActivity.class);
		i.putExtra("source", "Terminer");
		Log.i("test", "lol4");
		finish();
		Log.i("test", "lol5");
		startActivity(i);
	}
	
	private class Ecouteur implements OnCheckedChangeListener, OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(R.id.buttonArretT == v.getId())
			{
				pause();
				
				//Dialog pour la fin de l'entrainement
				final FinTimerDialog dialog = new FinTimerDialog(TimerActivity.this, TimerActivity.this);
				dialog.setContentView(R.layout.activity_fin_timer_dialog);
				dialog.setTitle("Fin de l'entrainement");

				dialog.show();
				Log.i("test", "lol0");
				
				
			
			}
			else if(R.id.buttonResetT == v.getId()) //reset chronomètre
			{
				reset = true;
				chronometre.setText("00:00");
				toggleButtonStopPlay.setChecked(false);
			}
		}
		

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if(toggleButtonStopPlay.isChecked())
			{
				if(reset)//Faire un reset au chronomètre
				{
					chronometre.setBase(SystemClock.elapsedRealtime());
					reset = false;
				}else if(pauseTime != -1) //Remettre le chronomètre au bon temps après une pause
				{
					chronometre.setBase(SystemClock.elapsedRealtime()-pauseTime);
					pauseTime = -1;
				}
				
				chronometre.start();
			}
			else
			{
				pause();
			}
		}
		
	}
	
	public void pause(){
		//Mettre le chronomètre sur pause
		toggleButtonStopPlay.setChecked(false);
		chronometre.stop();
		pauseTime = (SystemClock.elapsedRealtime() - chronometre.getBase());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timer, menu);
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
