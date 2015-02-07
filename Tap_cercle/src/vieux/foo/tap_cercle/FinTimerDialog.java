package vieux.foo.tap_cercle;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class FinTimerDialog extends Dialog {
	
	Button buttonOk, buttonAnnuler;
	TimerActivity t;
	
	public FinTimerDialog(Context context, TimerActivity t) {
		super(context);
		this.t = t;	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fin_timer_dialog);
		
		buttonOk = (Button)findViewById(R.id.buttonOkDialog);
		buttonAnnuler = (Button)findViewById(R.id.buttonAnnulerDialog);
		
		
		//Ecouteur
		Ecouteur ec = new Ecouteur();
		buttonOk.setOnClickListener(ec);
		buttonAnnuler.setOnClickListener(ec);
	}
	
	private class Ecouteur implements android.view.View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.buttonOkDialog)
			{
				Log.i("test", "lol_1");			
			}
			dismiss();
			t.apresDialogue();
		}
		
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
